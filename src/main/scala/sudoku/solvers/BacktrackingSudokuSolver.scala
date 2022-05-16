package sudoku.solvers

import sudoku.models.Sudoku.SudokuField
import sudoku.models.{Position, Sudoku}

object BacktrackingSudokuSolver extends SudokuSolver {
  override def solve(sudoku: Sudoku): Option[Sudoku] = {
    if (sudoku.isValid) Some(sudoku)
    else solve(sudoku, 0)
  }

  private def solve(sudoku: Sudoku, cell: Int): Option[Sudoku] = {
    Position(cell / sudoku.grid.length, cell % sudoku.grid.length) match {
      case Position(x, _) if x == sudoku.grid.length => Some(sudoku)
      case Position(x, y) if sudoku.grid(x)(y).number.isDefined || !sudoku.grid(x)(y).isActive =>
        solve(sudoku, cell + 1)
      case Position(x, y) =>
        val pos = Position(x, y)
        val usedNumbers =
          (sudoku.rowsAndCols ++ sudoku.fieldGroups)
            .filter(fg => fg.contains(pos))
            .flatten
            .map(pos => sudoku.grid(pos.x)(pos.y))
            .filter(field => field.isActive && field.number.isDefined)
            .map(field => field.number.get)

        def guess(n: Int) =
          solve(sudoku.copy(sudoku.grid.updated(x, sudoku.grid(x).updated(y, SudokuField(Some(n))))), cell + 1)

        (1 to sudoku.rowsAndCols.head.length).diff(usedNumbers).collectFirst(Function.unlift(guess))
    }
  }
}
