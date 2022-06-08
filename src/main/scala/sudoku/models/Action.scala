package sudoku.models

import sudoku.errors.{
  FieldNotEditableError,
  InvalidNumberError,
  NoSudokuSelectedError,
  SudokuError,
  UnsolvableSudokuError
}
import sudoku.models.Action.withSudoku
import sudoku.models.Sudoku.SudokuField
import sudoku.parsers.SudokuParser
import sudoku.solvers.SudokuSolver

trait Action {
  def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]]
}

protected object Action {
  def withSudoku(
    maybeSudoku: Option[Sudoku]
  )(block: Sudoku => Either[SudokuError, Option[Sudoku]]): Either[SudokuError, Option[Sudoku]] = maybeSudoku match {
    case Some(sudoku) => block(sudoku)
    case None         => Left(NoSudokuSelectedError())
  }
}

final case class SolveAction(solver: SudokuSolver) extends Action {
  override def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]] =
    withSudoku(maybeSudoku) { sudoku =>
      solver.solve(sudoku) match {
        case Some(solvedSudoku) => Right(Some(solvedSudoku))
        case None               => Left(UnsolvableSudokuError())
      }
    }
}

trait BaseEnterNumberAction extends Action {
  val number: Int
  val position: Position

  override def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]] = withSudoku(maybeSudoku) {
    sudoku =>
      val sudokuField       = sudoku.grid(position.y)(position.x)
      val validNumber       = Option.when(1 <= number && number <= 9)(number)
      val validCurrentField = Option.when(sudokuField.isEditable)(sudokuField)

      (validNumber, validCurrentField) match {
        case (None, _)                          => Left(InvalidNumberError())
        case (_, None)                          => Left(FieldNotEditableError())
        case (Some(number), Some(currentField)) => Right(enterNumber(sudoku, currentField, number))

      }
  }

  protected def enterNumber(sudoku: Sudoku, currentField: SudokuField, number: Int): Option[Sudoku]
}

final case class EnterNumberAction(number: Int, position: Position) extends BaseEnterNumberAction {
  override protected def enterNumber(sudoku: Sudoku, currentField: SudokuField, number: Int): Option[Sudoku] = {
    val currentNumber = currentField.number
    val newNumber     = if (currentNumber.exists(_.equals(number))) None else Some(number)

    val newSudoku = sudoku.copy(
      sudoku.grid.updated(
        position.y,
        sudoku
          .grid(position.y)
          .updated(position.x, SudokuField(newNumber))
      )
    )

    Some(newSudoku)
  }
}

final case class EnterHelpNumberAction(number: Int, position: Position) extends BaseEnterNumberAction {
  override protected def enterNumber(sudoku: Sudoku, currentField: SudokuField, number: Int): Option[Sudoku] = {
    val currentNumbers = currentField.helpNumbers

    val newNumbers =
      if (currentNumbers.contains(number)) currentNumbers.filter(!_.equals(number))
      else currentNumbers ++ Seq(number)

    val newSudoku = sudoku.copy(
      sudoku.grid
        .updated(position.y, sudoku.grid(position.y).updated(position.x, SudokuField(None, newNumbers)))
    )

    Some(newSudoku)
  }
}

final case class StartSudokuAction(filePath: String) extends Action {
  override def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]] = {
    SudokuParser.parse(filePath).map(Some(_))
  }
}

final case class CloseSudokuAction() extends Action {
  override def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]] = Right(None)
}
