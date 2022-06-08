package sudoku.models

import org.scalamock.scalatest.MockFactory
import org.scalatest.EitherValues
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.errors.{
  FieldNotEditableError,
  InvalidNumberError,
  NoSudokuSelectedError,
  SudokuNotFoundError,
  UnsolvableSudokuError
}
import sudoku.models.Sudoku.SudokuField
import sudoku.solvers.SudokuSolver
import sudoku.testUtils.TestPuzzles

class ActionSpec extends AnyWordSpec with Matchers with MockFactory with EitherValues {
  val testSudoku: Sudoku = TestPuzzles.regularSudoku4x4

  "SolveAction" should {
    "solve a solvable sudoku" in {
      val mockSolver = mock[SudokuSolver]

      (mockSolver.solve _).expects(testSudoku).returns(Some(testSudoku)).once()

      val newSudoku = SolveAction(mockSolver).execute(Some(testSudoku))

      newSudoku.value.get mustBe testSudoku
    }

    "fail on an unsolvable sudoku" in {
      val mockSolver = mock[SudokuSolver]

      (mockSolver.solve _).expects(testSudoku).returns(None).once()

      val newSudoku = SolveAction(mockSolver).execute(Some(testSudoku))

      newSudoku.left.value match {
        case UnsolvableSudokuError() =>
        case _                       => fail("Wrong error type, expected UnsolvableSudokuError")
      }
    }

    "fail when no sudoku is selected" in {
      val mockSolver = mock[SudokuSolver]

      (mockSolver.solve _).expects(*).never()

      val newSudoku = SolveAction(mockSolver).execute(None)

      newSudoku.left.value match {
        case NoSudokuSelectedError() =>
        case _                       => fail("Wrong error type, expected NoSudokuSelectedError")
      }
    }
  }

  "EnterNumberAction" should {
    "enter a number when the field doesn't already have the given number and is not permanent" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(None)))
      )

      val newSudoku = EnterNumberAction(5, Position(1, 3)).execute(Some(sudoku))

      newSudoku.value.get.grid(1)(3).number.get mustBe 5
    }

    "remove a number when the field already has the given number and is not permanent" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(Some(1))))
      )

      val newSudoku = EnterNumberAction(1, Position(1, 3)).execute(Some(sudoku))

      newSudoku.value.get.grid(1)(3).number mustBe None
    }

    "fail when trying to enter a number in a permanent field" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(Some(1), isPermanent = true)))
      )

      val newSudoku = EnterNumberAction(1, Position(1, 3)).execute(Some(sudoku))

      newSudoku.left.value match {
        case FieldNotEditableError() =>
        case _                       => fail("Wrong error type, expected FieldNotEditableError")
      }
    }

    "fail when trying to enter a number in a inactive field" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(Some(1), isActive = false)))
      )

      val newSudoku = EnterNumberAction(1, Position(0, 1)).execute(Some(sudoku))

      newSudoku.left.value match {
        case FieldNotEditableError() =>
        case _                       => fail("Wrong error type, expected FieldNotEditableError")
      }
    }

    "fail when an invalid number is entered" in {
      val newSudoku = EnterNumberAction(200, Position(0, 0)).execute(Some(testSudoku))

      newSudoku.left.value match {
        case InvalidNumberError() =>
        case _                    => fail("Wrong error type, expected InvalidNumberError")
      }
    }

    "fail when no sudoku is selected" in {
      val sudoku = None

      val newSudoku = EnterNumberAction(1, Position(0, 0)).execute(sudoku)

      newSudoku.left.value match {
        case NoSudokuSelectedError() =>
        case _                       => fail("Wrong error type, expected NoSudokuSelectedError")
      }
    }
  }

  "EnterHelpNumberAction" should {
    "add a number when the field doesn't already have the given number and is not permanent" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(None, Seq(1))))
      )

      val newSudoku = EnterHelpNumberAction(5, Position(1, 3)).execute(Some(sudoku))

      newSudoku.value.get.grid(1)(3).helpNumbers mustBe Seq(1, 5)
    }

    "remove a number when the field already has the given number and is not permanent" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(None, Seq(3, 4))))
      )

      val newSudoku = EnterHelpNumberAction(3, Position(1, 3)).execute(Some(sudoku))

      newSudoku.value.get.grid(1)(3).helpNumbers mustBe Seq(4)
    }

    "fail when trying to enter a number in a permanent field" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(Some(1), isPermanent = true)))
      )

      val newSudoku = EnterHelpNumberAction(1, Position(1, 3)).execute(Some(sudoku))

      newSudoku.left.value match {
        case FieldNotEditableError() =>
        case _                       => fail("Wrong error type, expected FieldNotEditableError")
      }
    }

    "fail when trying to enter a number in a inactive field" in {
      val sudoku = testSudoku.copy(
        testSudoku.grid
          .updated(1, testSudoku.grid(1).updated(3, SudokuField(Some(1), isActive = false)))
      )

      val newSudoku = EnterHelpNumberAction(1, Position(0, 1)).execute(Some(sudoku))

      newSudoku.left.value match {
        case FieldNotEditableError() =>
        case _                       => fail("Wrong error type, expected FieldNotEditableError")
      }
    }

    "fail when no sudoku is selected" in {
      val sudoku = None

      val newSudoku = EnterHelpNumberAction(1, Position(0, 0)).execute(sudoku)

      newSudoku.left.value match {
        case NoSudokuSelectedError() =>
        case _                       => fail("Wrong error type, expected NoSudokuSelectedError")
      }
    }
  }

  "StartSudokuAction" should {
    "return the parsed sudoku (depends on SudokuParser)" in {
      val newSudoku = StartSudokuAction("./src/test/resources/puzzles/puzzle.4x4").execute(None)

      newSudoku.value.get.grid mustBe TestPuzzles.regularSudoku4x4.grid
    }

    "fail when the sudoku is not found (depends on SudokuParser)" in {
      val newSudoku = StartSudokuAction("abc").execute(None)

      newSudoku.left.value match {
        case SudokuNotFoundError() =>
        case _                     => fail("Wrong error type, expected SudokuNotFoundError")
      }
    }
  }

  "CloseSudokuAction" should {
    "close the sudoku" in {
      val newSudoku = CloseSudokuAction().execute(Some(TestPuzzles.regularSudoku4x4))

      newSudoku.value mustBe None
    }
  }
}
