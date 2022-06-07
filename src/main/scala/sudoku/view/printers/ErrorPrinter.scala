package sudoku.view.printers

import sudoku.errors._
import sudoku.view.config.Config
import sudoku.view.display.Display
import sudoku.view.display.Display.{Color, TextStyle}

object ErrorPrinter {
  private val errorColor    = Display.createColorPair(Color.Red, Color.White)
  private val errorPosition = Config.errorPosition

  def print(sudokuError: SudokuError): Unit = {
    Display.moveCursor(errorPosition)
    Display.setColor(errorColor)
    Display.addTextStyle(TextStyle.Blink)

    val errorMessage = sudokuError match {
      case NoSudokuSelectedError() => "No sudoku has been selected!"
      case FieldNotEditableError() => "This field cannot be edited!"
      case UnsolvableSudokuError() => "This sudoku seems to be unsolvable!"
      case SudokuNotFoundError()   => "Couldn't find any sudoku at that path!"
      case InvalidSudokuError()    => "This sudoku is not valid and cannot be imported!"
    }
    Display.print(errorMessage)

    Display.setTextStyle(TextStyle.Normal)
  }

  def clear(): Unit = {
    Display.moveCursor(errorPosition)
    Display.print(" " * 50)
  }
}
