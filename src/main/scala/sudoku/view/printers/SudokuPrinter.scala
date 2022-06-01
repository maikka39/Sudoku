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

      drawVerticalLine(column, 0)

      for ((field, fieldIndex) <- column.zipWithIndex) {
        if (field.isPermanent) {
          Display.addTextStyles(TextStyle.BOLD)
          Display.setColor(permanentNumberColor)
        }

        Display.print(s" ${field.number.map(_.toString).getOrElse(" ")} ")

        Display.setTextStyle(TextStyle.NORMAL)

        drawVerticalLine(column, fieldIndex)
      }

      Display.moveCursor(Position(startPosition.x, startPosition.y + (columnIndex * 2) + 2))

      drawHorizontalLine(sudoku.grid, columnIndex)
    }

    Display.moveCursor(startPosition)
    Display.refresh()
  }

  private def drawVerticalLine(column: Sudoku.Column, index: Int): Unit = {
    val currentField   = column.drop(index).head
    lazy val nextField = column.drop(index + 1).headOption

    if (currentField.isActive || nextField.exists(_.isActive))
      Display.print("|")
    else
      Display.print(" ")
  }

  private def drawHorizontalLine(grid: Sudoku.Grid, index: Int): Unit = {
    val transposedGrid = grid.transpose
    val row            = transposedGrid.drop(index).head
    val previousRow    = transposedGrid.drop(index + 1).headOption

    for ((field, fieldIndex) <- row.zipWithIndex) {
      lazy val fieldAbove  = previousRow.flatMap(_.drop(fieldIndex).headOption)
      lazy val fieldToLeft = row.drop(fieldIndex - 1).headOption

      if (field.isActive || fieldAbove.exists(_.isActive))
        Display.print("+---")
      else if (fieldToLeft.exists(_.isActive))
        Display.print("+   ")
      else
        Display.print("    ")
    }

    if (row.last.isActive || previousRow.map(_.last).exists(_.isActive))
      Display.print("+")
    else
      Display.print(" ")
  }
}
