package sudoku.models

import sudoku.errors.SudokuError
import sudoku.solvers.BacktrackingSudokuSolver

class Game {
  var sudoku: Option[Sudoku] = None

  def solution: Option[Sudoku] = {
    SolveAction(BacktrackingSudokuSolver).execute(sudoku).toOption.flatten
  }

  def executeAction(action: Action): Option[SudokuError] = {
    action.execute(sudoku) match {
      case Left(sudokuError) => Some(sudokuError)
      case Right(sudoku) =>
        this.sudoku = sudoku
        None
    }
  }
}
