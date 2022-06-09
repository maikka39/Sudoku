package sudoku.models

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.models.Sudoku.SudokuField

class GameSpec extends AnyWordSpec with Matchers with MockFactory {
  "executeAction" should {
    "run the given action" in {
      val mockAction = mock[Action]

      val gameSudoku = Some(Sudoku(Seq(Seq(SudokuField(None))), Seq(), Seq()))

      val returnedSudoku = Some(gameSudoku.get.copy(grid = Seq(Seq(SudokuField(Some(1))))))

      val game = new Game {
        sudoku = gameSudoku
      }

      (mockAction.execute _).expects(gameSudoku).returns(Right(returnedSudoku)).once()

      game.executeAction(mockAction)

      game.sudoku mustBe returnedSudoku
    }
  }
}
