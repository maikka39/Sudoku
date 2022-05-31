package sudoku.view.printers

import sudoku.models.Sudoku
import sudoku.view.display.Display
import sudoku.view.display.Display.{Color, Position, TextStyle, createColorPair}

object SudokuPrinter {
  private val permanentNumberColor = createColorPair(Color.WHITE, Color.BLACK)

  def print(startPosition: Position, sudoku: Sudoku): Unit = {
    Display.moveCursor(startPosition)

    drawHorizontalLine(sudoku.grid, 0)

    for ((column, columnIndex) <- sudoku.grid.zipWithIndex) {
      Display.moveCursor(Position(startPosition.x, startPosition.y + (columnIndex * 2) + 1))

      if (column.headOption.exists(_.isActive))
        Display.print("|")
      else
        Display.print(" ")

      for ((field, fieldIndex) <- column.zipWithIndex) {
        Display.print(" ")

        if (field.isPermanent) {
          Display.addTextStyles(TextStyle.BOLD)
          Display.setColor(permanentNumberColor)
        }

        Display.print(field.number.map(_.toString).getOrElse(" "))

        Display.setTextStyle(TextStyle.NORMAL)

        Display.print(" ")

        if (field.isActive || column.drop(fieldIndex + 1).headOption.exists(_.isActive))
          Display.print("|")
        else
          Display.print(" ")
      }

      Display.moveCursor(Position(startPosition.x, startPosition.y + (columnIndex * 2) + 2))

      drawHorizontalLine(sudoku.grid, columnIndex)
    }

    Display.moveCursor(startPosition)
    Display.refresh()
  }

  private def drawHorizontalLine(grid: Sudoku.Grid, index: Int): Unit = {
    for ((field, fieldIndex) <- grid.transpose.drop(index).head.zipWithIndex) {
      if (
        field.isActive || grid.transpose
          .drop(index + 1)
          .headOption
          .flatMap(_.drop(fieldIndex).headOption)
          .exists(_.isActive)
      )
        Display.print("+---")
      else if (grid.transpose.drop(index).head.drop(fieldIndex - 1).headOption.exists(_.isActive))
        Display.print("+   ")
      else
        Display.print("    ")
    }
    if (
      grid.transpose
        .drop(index)
        .head
        .last
        .isActive || grid.transpose.drop(index + 1).headOption.map(_.last).exists(_.isActive)
    )
      Display.print("+")
    else
      Display.print(" ")
  }
}
