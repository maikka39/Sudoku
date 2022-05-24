package sudoku.models

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.models.Sudoku.{FieldGroup, Grid, SudokuField}

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
}
