package sudoku.parsers

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.testUtils.TestPuzzles

class SudokuParserSpec extends AnyWordSpec with Matchers {

  "SudokuParser" should {
    "correctly read a 4x4 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.4x4")

      sudoku.grid mustBe TestPuzzles.regularSudoku4x4.grid
    }

    "correctly read a 6x6 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.6x6")

      sudoku.grid mustBe TestPuzzles.regularSudoku6x6.grid
    }

    "correctly read a 9x9 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.9x9")

      sudoku.grid mustBe TestPuzzles.regularSudoku9x9.grid
    }

    "correctly read a jigsaw puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.jigsaw")

      sudoku.grid mustBe TestPuzzles.jigsawSudoku.grid
      sudoku.fieldGroups mustBe TestPuzzles.jigsawSudoku.fieldGroups
    }

    "correctly read a samurai puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.samurai")

      sudoku.grid mustBe TestPuzzles.samuraiSudoku.grid
    }
  }
}
