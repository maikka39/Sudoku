package sudoku.parsers

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must.Matchers
import sudoku.models.RegularSudoku
import sudoku.models.Sudoku.SudokuField

class SudokuParserSpec extends AnyWordSpec with Matchers {
  lazy val regularSudoku4x4: RegularSudoku = RegularSudoku(
    Seq(
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      )
    )
  )

  lazy val regularSudoku6x6: RegularSudoku = RegularSudoku(
    Seq(
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(Some(4), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      )
    )
  )

  lazy val regularSudoku9x9: RegularSudoku = RegularSudoku(
    Seq(
      Seq(
        SudokuField(Some(7), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(9), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(9), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(7), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(9), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(7), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true)
      )
    )
  )

  "SudokuParser" should {
    "correctly read a 4x4 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.4x4")

      sudoku mustBe regularSudoku4x4
    }

    "correctly read a 6x6 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.6x6")

      sudoku mustBe regularSudoku6x6
    }

    "correctly read a 9x9 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.9x9")

      sudoku mustBe regularSudoku9x9
    }
  }
}
