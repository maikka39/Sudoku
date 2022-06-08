package view.printers

import sudoku.errors._
import view.display.Display
import view.display.Display.{Color, TextStyle}
import view.utils.Config

object ErrorPrinter {
  private val errorColor    = Display.createColorPair(Color.Red, Color.White)
  private val errorPosition = Config.errorPosition

  def print(sudokuError: SudokuError): Unit = {
    val originalPosition = Display.cursorPosition
    Display.moveCursor(errorPosition)
    Display.setColor(errorColor)
    Display.addTextStyle(TextStyle.Blink)

    val errorMessage = sudokuError match {
      case NoSudokuSelectedError() => "No sudoku has been selected!"
      case FieldNotEditableError() => "This field cannot be edited!"
      case UnsolvableSudokuError() => "This sudoku seems to be unsolvable!"
      case SudokuNotFoundError()   => "Couldn't find any sudoku at that path!"
      case InvalidSudokuError()    => "This sudoku is not valid and cannot be imported!"
      case InvalidNumberError()    => "This is not a valid number!"
    }

    Display.print(errorMessage)

    Display.setTextStyle(TextStyle.Normal)
    Display.moveCursor(originalPosition)
  }
}
