package sudoku.errors

sealed trait SudokuError

final case class NoSudokuSelectedError() extends SudokuError
final case class FieldNotEditableError() extends SudokuError
final case class UnsolvableSudokuError() extends SudokuError
final case class SudokuNotFoundError()   extends SudokuError
final case class InvalidSudokuError()    extends SudokuError
