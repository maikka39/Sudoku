package view.printers

import sudoku.models.Sudoku
import view.State
import view.config.Config
import view.display.Display.DisplayPosition

object InGamePrinter {
  def print(state: State, sudoku: Sudoku): Unit = {
    InGameSudokuPrinter.print(sudoku, state)
    InGameMenuPrinter.print(
      DisplayPosition(Config.sudokuPosition.y + 3, Config.sudokuPosition.x + sudoku.grid.length * 4 + 5)
    )
  }
}
