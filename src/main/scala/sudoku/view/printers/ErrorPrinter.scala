package sudoku.view.printers

import sudoku.errors.{
  FieldNotEditableError,
  InvalidSudokuError,
  NoSudokuSelectedError,
  SudokuError,
  SudokuNotFoundError,
  UnsolvableSudokuError
}
import sudoku.view.display.Display
import sudoku.view.display.Display.{Color, Position, TextStyle}

object ErrorPrinter {
  private val errorColor = Display.createColorPair(Color.Red, Color.White)

  def print(position: Position, sudokuError: SudokuError): Unit = {
    val originalPosition = Display.cursorPosition
    Display.moveCursor(position)
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
    Display.moveCursor(originalPosition)
  }
}
