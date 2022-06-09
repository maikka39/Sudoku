package sudoku.solvers

import sudoku.models.Sudoku.SudokuField
import sudoku.models.{Position, Sudoku}

object BacktrackingSudokuSolver extends SudokuSolver {
  override def solve(sudoku: Sudoku): Option[Sudoku] = {
    if (sudoku.isValid) Some(sudoku)
    else solve(sudoku, 0)
  }

  private def solve(sudoku: Sudoku, cell: Int): Option[Sudoku] = {
    Position(cell % sudoku.grid.length, cell / sudoku.grid.length) match {
      case Position(_, x) if x == sudoku.grid.length => if (sudoku.isValid) Some(sudoku) else None
      case Position(y, x) if sudoku.grid(y)(x).number.isDefined || !sudoku.grid(y)(x).isActive =>
        solve(sudoku, cell + 1)
      case pos: Position =>
        val usedNumbers =
          (sudoku.rowsAndCols ++ sudoku.fieldGroups)
            .filter(fg => fg.contains(pos))
            .flatten
            .map(pos => sudoku.grid(pos.y)(pos.x))
            .filter(field => field.isActive)
            .flatMap(field => field.number)

        def guess(n: Int) =
          solve(
            sudoku.copy(grid = sudoku.grid.updated(pos.y, sudoku.grid(pos.y).updated(pos.x, SudokuField(Some(n))))),
            cell + 1
          )

        (1 to sudoku.rowsAndCols.head.length).diff(usedNumbers).collectFirst(Function.unlift(guess))
    }
  }
}
