package sudoku.models

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.models.Sudoku.SudokuField
import sudoku.solvers.SudokuSolver
import sudoku.testUtils.TestPuzzles

class SudokuSpec extends AnyWordSpec with Matchers with MockFactory {
  "Sudoku" should {
    val validSolvedSudoku = Sudoku(
      Seq(
        Seq(
          SudokuField(Some(1), Seq(), isPermanent = true),
          SudokuField(Some(4), Seq()),
          SudokuField(Some(2), Seq()),
          SudokuField(Some(3), Seq())
        ),
        Seq(
          SudokuField(Some(3), Seq()),
          SudokuField(Some(2), Seq()),
          SudokuField(Some(4), Seq(), isPermanent = true),
          SudokuField(Some(1), Seq())
        ),
        Seq(
          SudokuField(Some(4), Seq()),
          SudokuField(Some(3), Seq(), isPermanent = true),
          SudokuField(Some(1), Seq()),
          SudokuField(Some(2), Seq())
        ),
        Seq(
          SudokuField(Some(2), Seq()),
          SudokuField(Some(1), Seq()),
          SudokuField(Some(3), Seq()),
          SudokuField(Some(4), Seq(), isPermanent = true)
        )
      ),
      Seq(
        Seq(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1)),
        Seq(Position(0, 2), Position(0, 3), Position(1, 2), Position(1, 3)),
        Seq(Position(2, 0), Position(2, 1), Position(3, 0), Position(3, 1)),
        Seq(Position(2, 2), Position(2, 3), Position(3, 2), Position(3, 3))
      ),
      Seq(
        Seq(Position(0, 0), Position(1, 0), Position(2, 0), Position(3, 0)),
        Seq(Position(0, 0), Position(0, 1), Position(0, 2), Position(0, 3)),
        Seq(Position(0, 1), Position(1, 1), Position(2, 1), Position(3, 1)),
        Seq(Position(1, 0), Position(1, 1), Position(1, 2), Position(1, 3)),
        Seq(Position(0, 2), Position(1, 2), Position(2, 2), Position(3, 2)),
        Seq(Position(2, 0), Position(2, 1), Position(2, 2), Position(2, 3)),
        Seq(Position(0, 3), Position(1, 3), Position(2, 3), Position(3, 3)),
        Seq(Position(3, 0), Position(3, 1), Position(3, 2), Position(3, 3))
      )
    )

    "mark a valid sudoku as valid" in {
      validSolvedSudoku.isValid mustBe true
    }

    "mark a sudoku with an invalid group as invalid" in {
      val sudoku = validSolvedSudoku.copy(fieldGroups =
        Seq(
          Seq(Position(0, 0), Position(0, 1), Position(1, 1), Position(1, 1)),
          Seq(Position(0, 2), Position(0, 3), Position(1, 2), Position(1, 3)),
          Seq(Position(2, 0), Position(2, 1), Position(3, 0), Position(3, 1)),
          Seq(Position(2, 2), Position(2, 3), Position(3, 2), Position(3, 3))
        )
      )

      sudoku.isValid mustBe false
    }

    "mark a sudoku with an invalid row or column as invalid" in {
      val sudoku = validSolvedSudoku.copy(rowsAndCols =
        Seq(
          Seq(Position(0, 0), Position(1, 0), Position(3, 0), Position(3, 0)),
          Seq(Position(0, 0), Position(0, 1), Position(0, 2), Position(0, 3)),
          Seq(Position(0, 1), Position(1, 1), Position(2, 1), Position(3, 1)),
          Seq(Position(1, 0), Position(1, 1), Position(1, 2), Position(1, 3)),
          Seq(Position(0, 2), Position(1, 2), Position(2, 2), Position(3, 2)),
          Seq(Position(2, 0), Position(2, 1), Position(2, 2), Position(2, 3)),
          Seq(Position(0, 3), Position(1, 3), Position(2, 3), Position(3, 3)),
          Seq(Position(3, 0), Position(3, 1), Position(3, 2), Position(3, 3))
        )
      )

      sudoku.isValid mustBe false
    }

    "mark valid fields as valid in isFieldPossiblyValid" in {
      val sudoku = EnterNumberAction(2, Position(0, 0)).execute(Some(TestPuzzles.regularSudoku4x4)).toOption.flatten.get

      sudoku.isFieldPossiblyValid(Position(0, 0)) mustBe true
    }

    "mark invalid fields as invalid in isFieldPossiblyValid" in {
      val sudoku = EnterNumberAction(3, Position(0, 0)).execute(Some(TestPuzzles.regularSudoku4x4)).toOption.flatten.get

      sudoku.isFieldPossiblyValid(Position(0, 0)) mustBe false
    }

    "return the solution for a sudoku" in {
      val sudoku = TestPuzzles.regularSudoku4x4

      val mockSolver = mock[SudokuSolver]

      (mockSolver.solve _).expects(sudoku).returns(Some(validSolvedSudoku)).once()

      sudoku.solution(mockSolver) mustBe Some(validSolvedSudoku)
    }
  }
}
