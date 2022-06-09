package sudoku.solvers

import sudoku.models.Sudoku

trait SudokuSolver {
  def solve(sudoku: Sudoku): Option[Sudoku]
}

object SudokuSolver {
  implicit def defaultSolver: SudokuSolver = BacktrackingSudokuSolver
}
