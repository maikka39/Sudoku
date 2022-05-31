package sudoku.view.printers

import sudoku.models.Sudoku
import sudoku.view.display.Display
import sudoku.view.display.Display.{Position, TextStyle}

object SudokuPrinter {
  def print(startPosition: Position, sudoku: Sudoku): Unit = {
    Display.moveCursor(startPosition)

    for (_ <- sudoku.grid.head) {
      Display.print("+---")
    }
    Display.print("+")

    for ((column, columnIndex) <- sudoku.grid.zipWithIndex) {
      Display.moveCursor(Position(startPosition.x, startPosition.y + (columnIndex * 2) + 1))
      Display.print("|")
      for (field <- column) {
        Display.print(" ")

        if (field.isPermanent)
          Display.addTextStyles(TextStyle.BOLD)

        Display.print(field.number.map(_.toString).getOrElse(" "))

        if (field.isPermanent)
          Display.setTextStyle(TextStyle.NORMAL)

        Display.print(" |")
      }

      Display.moveCursor(Position(startPosition.x, startPosition.y + (columnIndex * 2) + 2))
      for (_ <- sudoku.grid.head) {
        Display.print("+---")
      }
      Display.print("+")

    }

    Display.moveCursor(startPosition)
    Display.refresh()
  }
}
