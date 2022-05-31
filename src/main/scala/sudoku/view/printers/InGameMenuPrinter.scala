package sudoku.view.printers

import sudoku.view.display.Display
import sudoku.view.display.Display.{Color, Position, createColorPair}

object InGameMenuPrinter {
  private val listStyleColor = createColorPair(Color.YELLOW, Color.BLACK)

  def print(position: Position): Unit = {
    Display.moveCursor(position)

    Display.print("Menu:")
    Display.moveCursor(position.x, Display.cursorPosition.y + 2)

    printListItem("Solve")
    printListItem("Exit")
  }

  private def printListItem(label: String): Unit = {
    val curPos = Display.cursorPosition
    Display.setColor(listStyleColor)
    Display.print("- ")
    Display.removeColor(listStyleColor)
    Display.print(label)
    Display.moveCursor(curPos.x, curPos.y + 1)
  }
}
