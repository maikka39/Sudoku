package view.printers

import sudoku.models.Sudoku
import sudoku.models.Sudoku.FieldGroup
import view.display.Display.{Color, DisplayPosition, TextStyle, createColorPair}
import sudoku.models.{Position => GamePosition}
import view.config.Config
import view.display.Display
import view.display.Display.{Color, DisplayPosition, TextStyle}
import view.utils.Direction

object SudokuPrinter {
  private val permanentNumberColor = createColorPair(Color.White, Color.Black)
  private val borderColor          = createColorPair(Color.Grey, Color.Black)
  private val groupBorderColor     = createColorPair(Color.White, Color.Black)
  private val sudokuPosition       = Config.sudokuPosition

  def cursorPositionToGamePosition(grid: Sudoku.Grid, cursorPosition: DisplayPosition): Option[GamePosition] = {
    val y = (cursorPosition.y - sudokuPosition.y - 1) / 2
    val x = (cursorPosition.x - sudokuPosition.x - 2) / 4

    if (y < 0 || y >= grid.length || x < 0 || x >= grid.head.length)
      None
    else
      Some(GamePosition(y, x))
  }

  def print(sudoku: Sudoku): Unit = {
    Display.moveCursor(sudokuPosition)
    drawHorizontalLine(sudoku, -1)

    for ((row, y) <- sudoku.grid.zipWithIndex) {
      Display.moveCursor(DisplayPosition(sudokuPosition.y + (y * 2) + 1, sudokuPosition.x))

      drawVerticalLine(sudoku, GamePosition(y, -1))

      for ((field, x) <- row.zipWithIndex) {
        if (field.isPermanent) {
          Display.addTextStyles(TextStyle.Bold)
          Display.setColor(permanentNumberColor)
        }

        Display.print(s" ${field.number.map(_.toString).getOrElse(" ")} ")

        Display.setTextStyle(TextStyle.Normal)

        drawVerticalLine(sudoku, GamePosition(y, x))
      }

      Display.moveCursor(DisplayPosition(sudokuPosition.y + (y * 2) + 2, sudokuPosition.x))

      drawHorizontalLine(sudoku, y)
    }

    Display.moveCursor(sudokuPosition)
    Display.refresh()
  }

  private def findBorders(fieldGroups: Seq[FieldGroup], position: GamePosition) = {
    fieldGroups
      .find(_.exists(_ == position))
      .toSeq
      .flatMap(fieldGroup =>
        Direction.values
          .map(direction => (fieldGroup.contains(position + direction.relativePosition), direction))
          .filter(!_._1)
          .map(_._2)
      )
  }

  private def drawVerticalLine(sudoku: Sudoku, position: GamePosition): Unit = {
    val findBorders = this.findBorders(sudoku.fieldGroups, _)

    val currentField   = sudoku.grid.drop(position.y).headOption.flatMap(row => row.drop(position.x).headOption)
    lazy val nextField = sudoku.grid.drop(position.y).headOption.flatMap(row => row.drop(position.x + 1).headOption)

    if (
      findBorders(position).contains(Direction.East) || findBorders(position.copy(x = position.x + 1))
        .contains(Direction.West)
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

  private def drawHorizontalLine(sudoku: Sudoku, y: Int): Unit = {
    val findBorders = this.findBorders(sudoku.fieldGroups, _)

    val row     = sudoku.grid.drop(y).head
    val nextRow = sudoku.grid.drop(y + 1).headOption

    for ((field, x) <- row.zipWithIndex) {
      lazy val fieldBelow  = nextRow.flatMap(_.drop(x).headOption)
      lazy val fieldToLeft = row.drop(x - 1).headOption

      if (field.isActive || fieldBelow.exists(_.isActive) || fieldToLeft.exists(_.isActive)) {
        if (
          findBorders(GamePosition(y, x)).contains(Direction.South) ||
          findBorders(GamePosition(y, x)).contains(Direction.West) ||
          findBorders(GamePosition(y, x - 1)).contains(Direction.South) ||
          findBorders(GamePosition(y + 1, x)).contains(Direction.West) ||
          findBorders(GamePosition(y + 1, x)).contains(Direction.North)
        )
          Display.setColor(groupBorderColor)
        else
          Display.setColor(borderColor)

        Display.print("+")

        Display.setTextStyle(TextStyle.Normal)

        if (field.isActive || fieldBelow.exists(_.isActive)) {
          if (
            findBorders(GamePosition(y, x)).contains(Direction.South) ||
            findBorders(GamePosition(y + 1, x)).contains(Direction.North)
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
}
