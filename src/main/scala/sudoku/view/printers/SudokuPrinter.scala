package sudoku.view.printers

import sudoku.models.Sudoku
import sudoku.view.display.Display
import sudoku.view.display.Display.{Color, Position, TextStyle, createColorPair}
import sudoku.models.{Position => GamePosition}
import sudoku.view.printers.Border.Border

protected object Border extends Enumeration {
  type Border = Value

  val Left, Top, Right, Bottom = Value
}

object SudokuPrinter {
  private val permanentNumberColor = createColorPair(Color.White, Color.Black)
  private val borderColor          = createColorPair(Color.Grey, Color.Black)
  private val groupBorderColor     = createColorPair(Color.White, Color.Black)

  def print(startPosition: Position, sudoku: Sudoku): Unit = {
    def findBorders(position: GamePosition): Seq[Border] = {
      sudoku.fieldGroups
        .find(_.exists(_ == position))
        .toSeq
        .flatMap(fieldGroup =>
          Seq((-1, 0, Border.Left), (1, 0, Border.Right), (0, -1, Border.Top), (0, 1, Border.Bottom))
            .map(rel => (fieldGroup.contains(GamePosition(position.y + rel._2, position.x + rel._1)), rel._3))
            .filter(!_._1)
            .map(_._2)
        )
    }

    def drawVerticalLine(grid: Sudoku.Grid, position: GamePosition): Unit = {
      val currentField   = grid.drop(position.y).headOption.flatMap(col => col.drop(position.x).headOption)
      lazy val nextField = grid.drop(position.y).headOption.flatMap(col => col.drop(position.x + 1).headOption)

      if (
        findBorders(position).contains(Border.Right) || findBorders(position.copy(x = position.x + 1))
          .contains(Border.Left)
      )
        Display.setColor(groupBorderColor)
      else
        Display.setColor(borderColor)

      if (currentField.exists(_.isActive) || nextField.exists(_.isActive))
        Display.print("|")
      else
        Display.print(" ")

      Display.setTextStyle(TextStyle.Normal)
    }

    def drawHorizontalLine(grid: Sudoku.Grid, y: Int): Unit = {
      val row     = grid.drop(y).head
      val nextRow = grid.drop(y + 1).headOption

      for ((field, x) <- row.zipWithIndex) {
        lazy val fieldBelow  = nextRow.flatMap(_.drop(x).headOption)
        lazy val fieldToLeft = row.drop(x - 1).headOption

        if (field.isActive || fieldBelow.exists(_.isActive) || fieldToLeft.exists(_.isActive)) {
          if (
            findBorders(GamePosition(y, x)).contains(Border.Bottom) ||
            findBorders(GamePosition(y, x)).contains(Border.Left) ||
            findBorders(GamePosition(y, x - 1)).contains(Border.Bottom) ||
            findBorders(GamePosition(y + 1, x)).contains(Border.Left) ||
            findBorders(GamePosition(y + 1, x)).contains(Border.Top)
          )
            Display.setColor(groupBorderColor)
          else
            Display.setColor(borderColor)

          Display.print("+")

          Display.setTextStyle(TextStyle.Normal)

          if (field.isActive || fieldBelow.exists(_.isActive)) {
            if (
              findBorders(GamePosition(y, x)).contains(Border.Bottom) ||
              findBorders(GamePosition(y + 1, x)).contains(Border.Top)
            )
              Display.setColor(groupBorderColor)
            else
              Display.setColor(borderColor)
            Display.print("---")
          } else
            Display.print("   ")
        } else
          Display.print("    ")

        Display.setTextStyle(TextStyle.Normal)
      }

      if (row.last.isActive || nextRow.map(_.last).exists(_.isActive))
        Display.print("+")
      else
        Display.print(" ")
    }

    Display.moveCursor(startPosition)
    drawHorizontalLine(sudoku.grid, -1)

    for ((row, y) <- sudoku.grid.zipWithIndex) {
      Display.moveCursor(Position(startPosition.y + (y * 2) + 1, startPosition.x))

      drawVerticalLine(sudoku.grid, GamePosition(y, -1))

      for ((field, x) <- row.zipWithIndex) {
        if (field.isPermanent) {
          Display.addTextStyles(TextStyle.Bold)
          Display.setColor(permanentNumberColor)
        }

        Display.print(s" ${field.number.map(_.toString).getOrElse(" ")} ")

        Display.setTextStyle(TextStyle.Normal)

        drawVerticalLine(sudoku.grid, GamePosition(y, x))
      }

      Display.moveCursor(Position(startPosition.y + (y * 2) + 2, startPosition.x))

      drawHorizontalLine(sudoku.grid, y)
    }

    Display.moveCursor(startPosition)
    Display.refresh()
  }
}
