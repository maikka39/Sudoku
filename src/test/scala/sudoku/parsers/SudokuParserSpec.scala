package sudoku.parsers

import org.scalatest.EitherValues
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.errors.{InvalidSudokuError, SudokuNotFoundError}
import sudoku.testUtils.TestPuzzles

class SudokuParserSpec extends AnyWordSpec with Matchers with EitherValues {
  "SudokuParser" should {
    "correctly read a 4x4 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.4x4")

      sudoku.value mustBe TestPuzzles.regularSudoku4x4
    }

    "correctly read a 6x6 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.6x6")

      sudoku.value mustBe TestPuzzles.regularSudoku6x6
    }

    "correctly read a 9x9 puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.9x9")

      sudoku.value mustBe TestPuzzles.regularSudoku9x9
    }

    "correctly read a jigsaw puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.jigsaw")

      sudoku.value mustBe TestPuzzles.jigsawSudoku
    }

    "correctly read a samurai puzzle" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.samurai")

      sudoku.value mustBe TestPuzzles.samuraiSudoku
    }

    "fail when no file is found at the given path" in {
      val sudoku = SudokuParser.parse("abc")

      sudoku.left.value match {
        case SudokuNotFoundError() =>
        case _                     => fail("Wrong error type, expected SudokuNotFoundError")
      }
    }

    "fail when the sudoku type is not recognized" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.invalid")

      sudoku.left.value match {
        case InvalidSudokuError() =>
        case _                    => fail("Wrong error type, expected InvalidSudokuError")
      }
    }

    "fail when invalid data is given for a valid extension" in {
      val sudoku = SudokuParser.parse("./src/test/resources/puzzles/invalid.4x4")

      sudoku.left.value match {
        case InvalidSudokuError() =>
        case _                    => fail("Wrong error type, expected InvalidSudokuError")
      }
    }
  }
}
