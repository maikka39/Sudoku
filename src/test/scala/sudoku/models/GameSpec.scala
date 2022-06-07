package sudoku.models

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.models.Sudoku.{FieldGroup, Grid, SudokuField}
import sudoku.testUtils.TestPuzzles

class GameSpec extends AnyWordSpec with Matchers with MockFactory {
  "executeAction" should {
    "run the given action" in {
      val mockAction = mock[Action]

      val gameSudoku = Some(new Sudoku {
        override val grid: Grid                   = Seq(Seq(SudokuField(None)))
        override val fieldGroups: Seq[FieldGroup] = Seq()
        override val rowsAndCols: Seq[FieldGroup] = Seq()
      })

      val returnedSudoku = Some(gameSudoku.get.copy(Seq(Seq(SudokuField(Some(1))))))

      val game = new Game {
        sudoku = gameSudoku
      }

      (mockAction.execute _).expects(gameSudoku).returns(Right(returnedSudoku)).once()

      game.executeAction(mockAction)

      game.sudoku mustBe returnedSudoku
    }
  }

  "solution" should {
    "return the solution for a sudoku (depends on Solver)" in {
      val game = new Game {
        sudoku = Some(TestPuzzles.regularSudoku4x4)
      }

      game.solution.get.grid mustBe Seq(
        Seq(
          SudokuField(Some(2), Seq()),
          SudokuField(Some(3), Seq(), isPermanent = true),
          SudokuField(Some(4), Seq(), isPermanent = true),
          SudokuField(Some(1), Seq())
        ),
        Seq(
          SudokuField(Some(4), Seq(), isPermanent = true),
          SudokuField(Some(1), Seq()),
          SudokuField(Some(3), Seq()),
          SudokuField(Some(2), Seq(), isPermanent = true)
        ),
        Seq(
          SudokuField(Some(1), Seq(), isPermanent = true),
          SudokuField(Some(4), Seq()),
          SudokuField(Some(2), Seq()),
          SudokuField(Some(3), Seq(), isPermanent = true)
        ),
        Seq(
          SudokuField(Some(3), Seq()),
          SudokuField(Some(2), Seq(), isPermanent = true),
          SudokuField(Some(1), Seq(), isPermanent = true),
          SudokuField(Some(4), Seq())
        )
      )
    }
  }
}
