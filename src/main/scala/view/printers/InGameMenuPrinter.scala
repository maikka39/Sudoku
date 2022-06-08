package view.printers

import sudoku.models.Sudoku
import view.display.Display
import view.display.Display.{Color, DisplayPosition, TextStyle, createColorPair}
import view.states.InGameState
import view.utils.{Action, Config}

object InGameMenuPrinter {
  private val listStyleColor = createColorPair(Color.Yellow, Color.Black)

  def print(state: InGameState, sudoku: Sudoku): Unit = {
    val position = DisplayPosition(Config.sudokuPosition.y + 3, Config.sudokuPosition.x + sudoku.grid.length * 4 + 5)
    Display.moveCursor(position)

    Display.setTextStyle(TextStyle.Bold)
    Display.print("Menu:")
    Display.removeTextStyle(TextStyle.Bold)

    Display.moveCursor(position.y + 2, position.x)

    state.actions.filter(!_.hidden).foreach(printListItem)
  }

  private def printListItem(action: Action): Unit = {
    val curPos = Display.cursorPosition
    Display.setColor(listStyleColor)
    Display.print(action.keybind.toString)
    Display.removeColor(listStyleColor)
    Display.print(s" - ${action.label}")
    Display.moveCursor(curPos.y + 1, curPos.x)
  }
}
