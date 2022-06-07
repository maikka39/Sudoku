package view.printers

import view.Actions
import view.Actions.Action
import view.display.Display
import view.display.Display.{Color, DisplayPosition, createColorPair}

object InGameMenuPrinter {
  private val listStyleColor = createColorPair(Color.Yellow, Color.Black)

  def print(position: DisplayPosition): Unit = {
    Display.moveCursor(position)

    Display.print("Menu:")
    Display.moveCursor(position.y + 2, position.x)

    Actions.actions.filter(!_.hidden).foreach(printListItem)
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
