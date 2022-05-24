package sudoku.models

import sudoku.errors.{FieldNotEditableError, NoSudokuSelectedError, SudokuError, UnsolvableSudokuError}
import sudoku.models.Action.{withEditableField, withSudoku}
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

  def withEditableField(
    sudokuField: SudokuField
  )(block: SudokuField => Either[SudokuError, Option[Sudoku]]): Either[SudokuError, Option[Sudoku]] = {
    if (sudokuField.isActive && !sudokuField.isPermanent)
      block(sudokuField)
    else
      Left(FieldNotEditableError())
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

final case class EnterNumberAction(number: Int, position: Position) extends Action {
  override def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]] = withSudoku(maybeSudoku) {
    sudoku =>
      withEditableField(sudoku.grid(position.x)(position.y)) { currentField =>
        val currentNumber = currentField.number
        val newNumber     = if (currentNumber.exists(_.equals(number))) None else Some(number)

        val newSudoku = sudoku.copy(
          sudoku.grid.updated(
            position.x,
            sudoku
              .grid(position.x)
              .updated(position.y, SudokuField(newNumber))
          )
        )

        Right(Some(newSudoku))
      }
  }
}

final case class EnterHelpNumberAction(number: Int, position: Position) extends Action {
  override def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]] = withSudoku(maybeSudoku) {
    sudoku =>
      withEditableField(sudoku.grid(position.x)(position.y)) { currentField =>
        val currentNumbers = currentField.helpNumbers

        val newNumbers =
          if (currentNumbers.contains(number)) currentNumbers.filter(!_.equals(number))
          else currentNumbers ++ Seq(number)

        val newSudoku = sudoku.copy(
          sudoku.grid
            .updated(position.x, sudoku.grid(position.x).updated(position.y, SudokuField(None, newNumbers)))
        )

        Right(Some(newSudoku))
      }
  }
}

final case class StartSudokuAction(filePath: String) extends Action {
  override def execute(maybeSudoku: Option[Sudoku]): Either[SudokuError, Option[Sudoku]] = {
    SudokuParser.parse(filePath).map(Some(_))
  }
}
