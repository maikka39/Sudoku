package sudoku.view.printers

import sudoku.view.Actions
import sudoku.view.Actions.Action
import sudoku.view.display.Display
import sudoku.view.display.Display.{Color, Position, createColorPair}

object InGameMenuPrinter {
  private val listStyleColor = createColorPair(Color.Yellow, Color.Black)

  def print(position: Position): Unit = {
    Display.moveCursor(position)

    Display.print("Menu:")
    Display.moveCursor(position.y + 2, Display.cursorPosition.x)

    Actions.actions.foreach(printListItem)
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
