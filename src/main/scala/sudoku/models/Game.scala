package sudoku.models

import sudoku.errors.SudokuError

class Game {
  var sudoku: Option[Sudoku] = None

  def executeAction(action: Action): Option[SudokuError] = {
    action.execute(sudoku) match {
      case Left(error) => Some(error)
      case Right(sudoku) =>
        this.sudoku = sudoku
        None
    }
  }
}
