package sudoku.parsers

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.testUtils.TestPuzzles

class SudokuParserSpec extends AnyWordSpec with Matchers {

  "SudokuParser" should {
    "correctly read a 4x4 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.4x4")

      sudoku mustBe TestPuzzles.regularSudoku4x4
    }

    "correctly read a 6x6 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.6x6")

      sudoku mustBe TestPuzzles.regularSudoku6x6
    }

    "correctly read a 9x9 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.9x9")

      sudoku mustBe TestPuzzles.regularSudoku9x9
    }

    "correctly read a jigsaw puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.jigsaw")

      sudoku mustBe TestPuzzles.jigsawSudoku
    }

    "correctly read a samurai puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.samurai")

      sudoku mustBe TestPuzzles.samuraiSudoku
    }
  }
}
