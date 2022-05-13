package sudoku.models

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must.Matchers
import sudoku.models.Sudoku.SudokuField
import sudoku.testUtils.TestPuzzles

class SudokuSpec extends AnyWordSpec with Matchers {
  "RegularSudoku" should {
    "calculate the correct rows and columns" in {
      val sudoku = TestPuzzles.regularSudoku4x4

      sudoku.rowsAndCols mustBe Seq(
        Seq(Position(0, 0), Position(1, 0), Position(2, 0), Position(3, 0)),
        Seq(Position(0, 0), Position(0, 1), Position(0, 2), Position(0, 3)),
        Seq(Position(0, 1), Position(1, 1), Position(2, 1), Position(3, 1)),
        Seq(Position(1, 0), Position(1, 1), Position(1, 2), Position(1, 3)),
        Seq(Position(0, 2), Position(1, 2), Position(2, 2), Position(3, 2)),
        Seq(Position(2, 0), Position(2, 1), Position(2, 2), Position(2, 3)),
        Seq(Position(0, 3), Position(1, 3), Position(2, 3), Position(3, 3)),
        Seq(Position(3, 0), Position(3, 1), Position(3, 2), Position(3, 3))
      )
    }

    "calculate the correct fieldGroups for a 4x4 grid" in {
      val sudoku = TestPuzzles.regularSudoku4x4

      sudoku.fieldGroups mustBe Seq(
        Seq(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1)),
        Seq(Position(0, 2), Position(0, 3), Position(1, 2), Position(1, 3)),
        Seq(Position(2, 0), Position(2, 1), Position(3, 0), Position(3, 1)),
        Seq(Position(2, 2), Position(2, 3), Position(3, 2), Position(3, 3))
      )
    }

    "calculate the correct fieldGroups for a 6x6 grid" in {
      val sudoku = TestPuzzles.regularSudoku6x6

      sudoku.fieldGroups mustBe Seq(
        Seq(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1), Position(2, 0), Position(2, 1)),
        Seq(Position(0, 2), Position(0, 3), Position(1, 2), Position(1, 3), Position(2, 2), Position(2, 3)),
        Seq(Position(0, 4), Position(0, 5), Position(1, 4), Position(1, 5), Position(2, 4), Position(2, 5)),
        Seq(Position(3, 0), Position(3, 1), Position(4, 0), Position(4, 1), Position(5, 0), Position(5, 1)),
        Seq(Position(3, 2), Position(3, 3), Position(4, 2), Position(4, 3), Position(5, 2), Position(5, 3)),
        Seq(Position(3, 4), Position(3, 5), Position(4, 4), Position(4, 5), Position(5, 4), Position(5, 5))
      )
    }

    "calculate the correct fieldGroups for a 9x9 grid" in {
      val sudoku = TestPuzzles.regularSudoku9x9

      sudoku.fieldGroups mustBe Seq(
        Seq(
          Position(0, 0),
          Position(0, 1),
          Position(0, 2),
          Position(1, 0),
          Position(1, 1),
          Position(1, 2),
          Position(2, 0),
          Position(2, 1),
          Position(2, 2)
        ),
        Seq(
          Position(0, 3),
          Position(0, 4),
          Position(0, 5),
          Position(1, 3),
          Position(1, 4),
          Position(1, 5),
          Position(2, 3),
          Position(2, 4),
          Position(2, 5)
        ),
        Seq(
          Position(0, 6),
          Position(0, 7),
          Position(0, 8),
          Position(1, 6),
          Position(1, 7),
          Position(1, 8),
          Position(2, 6),
          Position(2, 7),
          Position(2, 8)
        ),
        Seq(
          Position(3, 0),
          Position(3, 1),
          Position(3, 2),
          Position(4, 0),
          Position(4, 1),
          Position(4, 2),
          Position(5, 0),
          Position(5, 1),
          Position(5, 2)
        ),
        Seq(
          Position(3, 3),
          Position(3, 4),
          Position(3, 5),
          Position(4, 3),
          Position(4, 4),
          Position(4, 5),
          Position(5, 3),
          Position(5, 4),
          Position(5, 5)
        ),
        Seq(
          Position(3, 6),
          Position(3, 7),
          Position(3, 8),
          Position(4, 6),
          Position(4, 7),
          Position(4, 8),
          Position(5, 6),
          Position(5, 7),
          Position(5, 8)
        ),
        Seq(
          Position(6, 0),
          Position(6, 1),
          Position(6, 2),
          Position(7, 0),
          Position(7, 1),
          Position(7, 2),
          Position(8, 0),
          Position(8, 1),
          Position(8, 2)
        ),
        Seq(
          Position(6, 3),
          Position(6, 4),
          Position(6, 5),
          Position(7, 3),
          Position(7, 4),
          Position(7, 5),
          Position(8, 3),
          Position(8, 4),
          Position(8, 5)
        ),
        Seq(
          Position(6, 6),
          Position(6, 7),
          Position(6, 8),
          Position(7, 6),
          Position(7, 7),
          Position(7, 8),
          Position(8, 6),
          Position(8, 7),
          Position(8, 8)
        )
      )
    }
  }

  "JigsawSudoku" should {
    "calculate the correct rows and columns" in {
      val sudoku = TestPuzzles.jigsawSudoku

      sudoku.rowsAndCols mustBe Seq(
        Seq(
          Position(0, 0),
          Position(1, 0),
          Position(2, 0),
          Position(3, 0),
          Position(4, 0),
          Position(5, 0),
          Position(6, 0),
          Position(7, 0),
          Position(8, 0)
        ),
        Seq(
          Position(0, 0),
          Position(0, 1),
          Position(0, 2),
          Position(0, 3),
          Position(0, 4),
          Position(0, 5),
          Position(0, 6),
          Position(0, 7),
          Position(0, 8)
        ),
        Seq(
          Position(0, 1),
          Position(1, 1),
          Position(2, 1),
          Position(3, 1),
          Position(4, 1),
          Position(5, 1),
          Position(6, 1),
          Position(7, 1),
          Position(8, 1)
        ),
        Seq(
          Position(1, 0),
          Position(1, 1),
          Position(1, 2),
          Position(1, 3),
          Position(1, 4),
          Position(1, 5),
          Position(1, 6),
          Position(1, 7),
          Position(1, 8)
        ),
        Seq(
          Position(0, 2),
          Position(1, 2),
          Position(2, 2),
          Position(3, 2),
          Position(4, 2),
          Position(5, 2),
          Position(6, 2),
          Position(7, 2),
          Position(8, 2)
        ),
        Seq(
          Position(2, 0),
          Position(2, 1),
          Position(2, 2),
          Position(2, 3),
          Position(2, 4),
          Position(2, 5),
          Position(2, 6),
          Position(2, 7),
          Position(2, 8)
        ),
        Seq(
          Position(0, 3),
          Position(1, 3),
          Position(2, 3),
          Position(3, 3),
          Position(4, 3),
          Position(5, 3),
          Position(6, 3),
          Position(7, 3),
          Position(8, 3)
        ),
        Seq(
          Position(3, 0),
          Position(3, 1),
          Position(3, 2),
          Position(3, 3),
          Position(3, 4),
          Position(3, 5),
          Position(3, 6),
          Position(3, 7),
          Position(3, 8)
        ),
        Seq(
          Position(0, 4),
          Position(1, 4),
          Position(2, 4),
          Position(3, 4),
          Position(4, 4),
          Position(5, 4),
          Position(6, 4),
          Position(7, 4),
          Position(8, 4)
        ),
        Seq(
          Position(4, 0),
          Position(4, 1),
          Position(4, 2),
          Position(4, 3),
          Position(4, 4),
          Position(4, 5),
          Position(4, 6),
          Position(4, 7),
          Position(4, 8)
        ),
        Seq(
          Position(0, 5),
          Position(1, 5),
          Position(2, 5),
          Position(3, 5),
          Position(4, 5),
          Position(5, 5),
          Position(6, 5),
          Position(7, 5),
          Position(8, 5)
        ),
        Seq(
          Position(5, 0),
          Position(5, 1),
          Position(5, 2),
          Position(5, 3),
          Position(5, 4),
          Position(5, 5),
          Position(5, 6),
          Position(5, 7),
          Position(5, 8)
        ),
        Seq(
          Position(0, 6),
          Position(1, 6),
          Position(2, 6),
          Position(3, 6),
          Position(4, 6),
          Position(5, 6),
          Position(6, 6),
          Position(7, 6),
          Position(8, 6)
        ),
        Seq(
          Position(6, 0),
          Position(6, 1),
          Position(6, 2),
          Position(6, 3),
          Position(6, 4),
          Position(6, 5),
          Position(6, 6),
          Position(6, 7),
          Position(6, 8)
        ),
        Seq(
          Position(0, 7),
          Position(1, 7),
          Position(2, 7),
          Position(3, 7),
          Position(4, 7),
          Position(5, 7),
          Position(6, 7),
          Position(7, 7),
          Position(8, 7)
        ),
        Seq(
          Position(7, 0),
          Position(7, 1),
          Position(7, 2),
          Position(7, 3),
          Position(7, 4),
          Position(7, 5),
          Position(7, 6),
          Position(7, 7),
          Position(7, 8)
        ),
        Seq(
          Position(0, 8),
          Position(1, 8),
          Position(2, 8),
          Position(3, 8),
          Position(4, 8),
          Position(5, 8),
          Position(6, 8),
          Position(7, 8),
          Position(8, 8)
        ),
        Seq(
          Position(8, 0),
          Position(8, 1),
          Position(8, 2),
          Position(8, 3),
          Position(8, 4),
          Position(8, 5),
          Position(8, 6),
          Position(8, 7),
          Position(8, 8)
        )
      )
    }
  }

  "SamuraiSudoku" should {
    "calculate the correct rows and columns" in {
      val sudoku = TestPuzzles.samuraiSudoku

      sudoku.rowsAndCols mustBe Seq(
        Seq(
          Position(0, 0),
          Position(1, 0),
          Position(2, 0),
          Position(3, 0),
          Position(4, 0),
          Position(5, 0),
          Position(6, 0),
          Position(7, 0),
          Position(8, 0)
        ),
        Seq(
          Position(0, 0),
          Position(0, 1),
          Position(0, 2),
          Position(0, 3),
          Position(0, 4),
          Position(0, 5),
          Position(0, 6),
          Position(0, 7),
          Position(0, 8)
        ),
        Seq(
          Position(0, 1),
          Position(1, 1),
          Position(2, 1),
          Position(3, 1),
          Position(4, 1),
          Position(5, 1),
          Position(6, 1),
          Position(7, 1),
          Position(8, 1)
        ),
        Seq(
          Position(1, 0),
          Position(1, 1),
          Position(1, 2),
          Position(1, 3),
          Position(1, 4),
          Position(1, 5),
          Position(1, 6),
          Position(1, 7),
          Position(1, 8)
        ),
        Seq(
          Position(0, 2),
          Position(1, 2),
          Position(2, 2),
          Position(3, 2),
          Position(4, 2),
          Position(5, 2),
          Position(6, 2),
          Position(7, 2),
          Position(8, 2)
        ),
        Seq(
          Position(2, 0),
          Position(2, 1),
          Position(2, 2),
          Position(2, 3),
          Position(2, 4),
          Position(2, 5),
          Position(2, 6),
          Position(2, 7),
          Position(2, 8)
        ),
        Seq(
          Position(0, 3),
          Position(1, 3),
          Position(2, 3),
          Position(3, 3),
          Position(4, 3),
          Position(5, 3),
          Position(6, 3),
          Position(7, 3),
          Position(8, 3)
        ),
        Seq(
          Position(3, 0),
          Position(3, 1),
          Position(3, 2),
          Position(3, 3),
          Position(3, 4),
          Position(3, 5),
          Position(3, 6),
          Position(3, 7),
          Position(3, 8)
        ),
        Seq(
          Position(0, 4),
          Position(1, 4),
          Position(2, 4),
          Position(3, 4),
          Position(4, 4),
          Position(5, 4),
          Position(6, 4),
          Position(7, 4),
          Position(8, 4)
        ),
        Seq(
          Position(4, 0),
          Position(4, 1),
          Position(4, 2),
          Position(4, 3),
          Position(4, 4),
          Position(4, 5),
          Position(4, 6),
          Position(4, 7),
          Position(4, 8)
        ),
        Seq(
          Position(0, 5),
          Position(1, 5),
          Position(2, 5),
          Position(3, 5),
          Position(4, 5),
          Position(5, 5),
          Position(6, 5),
          Position(7, 5),
          Position(8, 5)
        ),
        Seq(
          Position(5, 0),
          Position(5, 1),
          Position(5, 2),
          Position(5, 3),
          Position(5, 4),
          Position(5, 5),
          Position(5, 6),
          Position(5, 7),
          Position(5, 8)
        ),
        Seq(
          Position(0, 6),
          Position(1, 6),
          Position(2, 6),
          Position(3, 6),
          Position(4, 6),
          Position(5, 6),
          Position(6, 6),
          Position(7, 6),
          Position(8, 6)
        ),
        Seq(
          Position(6, 0),
          Position(6, 1),
          Position(6, 2),
          Position(6, 3),
          Position(6, 4),
          Position(6, 5),
          Position(6, 6),
          Position(6, 7),
          Position(6, 8)
        ),
        Seq(
          Position(0, 7),
          Position(1, 7),
          Position(2, 7),
          Position(3, 7),
          Position(4, 7),
          Position(5, 7),
          Position(6, 7),
          Position(7, 7),
          Position(8, 7)
        ),
        Seq(
          Position(7, 0),
          Position(7, 1),
          Position(7, 2),
          Position(7, 3),
          Position(7, 4),
          Position(7, 5),
          Position(7, 6),
          Position(7, 7),
          Position(7, 8)
        ),
        Seq(
          Position(0, 8),
          Position(1, 8),
          Position(2, 8),
          Position(3, 8),
          Position(4, 8),
          Position(5, 8),
          Position(6, 8),
          Position(7, 8),
          Position(8, 8)
        ),
        Seq(
          Position(8, 0),
          Position(8, 1),
          Position(8, 2),
          Position(8, 3),
          Position(8, 4),
          Position(8, 5),
          Position(8, 6),
          Position(8, 7),
          Position(8, 8)
        ),
        Seq(
          Position(0, 12),
          Position(1, 12),
          Position(2, 12),
          Position(3, 12),
          Position(4, 12),
          Position(5, 12),
          Position(6, 12),
          Position(7, 12),
          Position(8, 12)
        ),
        Seq(
          Position(0, 12),
          Position(0, 13),
          Position(0, 14),
          Position(0, 15),
          Position(0, 16),
          Position(0, 17),
          Position(0, 18),
          Position(0, 19),
          Position(0, 20)
        ),
        Seq(
          Position(0, 13),
          Position(1, 13),
          Position(2, 13),
          Position(3, 13),
          Position(4, 13),
          Position(5, 13),
          Position(6, 13),
          Position(7, 13),
          Position(8, 13)
        ),
        Seq(
          Position(1, 12),
          Position(1, 13),
          Position(1, 14),
          Position(1, 15),
          Position(1, 16),
          Position(1, 17),
          Position(1, 18),
          Position(1, 19),
          Position(1, 20)
        ),
        Seq(
          Position(0, 14),
          Position(1, 14),
          Position(2, 14),
          Position(3, 14),
          Position(4, 14),
          Position(5, 14),
          Position(6, 14),
          Position(7, 14),
          Position(8, 14)
        ),
        Seq(
          Position(2, 12),
          Position(2, 13),
          Position(2, 14),
          Position(2, 15),
          Position(2, 16),
          Position(2, 17),
          Position(2, 18),
          Position(2, 19),
          Position(2, 20)
        ),
        Seq(
          Position(0, 15),
          Position(1, 15),
          Position(2, 15),
          Position(3, 15),
          Position(4, 15),
          Position(5, 15),
          Position(6, 15),
          Position(7, 15),
          Position(8, 15)
        ),
        Seq(
          Position(3, 12),
          Position(3, 13),
          Position(3, 14),
          Position(3, 15),
          Position(3, 16),
          Position(3, 17),
          Position(3, 18),
          Position(3, 19),
          Position(3, 20)
        ),
        Seq(
          Position(0, 16),
          Position(1, 16),
          Position(2, 16),
          Position(3, 16),
          Position(4, 16),
          Position(5, 16),
          Position(6, 16),
          Position(7, 16),
          Position(8, 16)
        ),
        Seq(
          Position(4, 12),
          Position(4, 13),
          Position(4, 14),
          Position(4, 15),
          Position(4, 16),
          Position(4, 17),
          Position(4, 18),
          Position(4, 19),
          Position(4, 20)
        ),
        Seq(
          Position(0, 17),
          Position(1, 17),
          Position(2, 17),
          Position(3, 17),
          Position(4, 17),
          Position(5, 17),
          Position(6, 17),
          Position(7, 17),
          Position(8, 17)
        ),
        Seq(
          Position(5, 12),
          Position(5, 13),
          Position(5, 14),
          Position(5, 15),
          Position(5, 16),
          Position(5, 17),
          Position(5, 18),
          Position(5, 19),
          Position(5, 20)
        ),
        Seq(
          Position(0, 18),
          Position(1, 18),
          Position(2, 18),
          Position(3, 18),
          Position(4, 18),
          Position(5, 18),
          Position(6, 18),
          Position(7, 18),
          Position(8, 18)
        ),
        Seq(
          Position(6, 12),
          Position(6, 13),
          Position(6, 14),
          Position(6, 15),
          Position(6, 16),
          Position(6, 17),
          Position(6, 18),
          Position(6, 19),
          Position(6, 20)
        ),
        Seq(
          Position(0, 19),
          Position(1, 19),
          Position(2, 19),
          Position(3, 19),
          Position(4, 19),
          Position(5, 19),
          Position(6, 19),
          Position(7, 19),
          Position(8, 19)
        ),
        Seq(
          Position(7, 12),
          Position(7, 13),
          Position(7, 14),
          Position(7, 15),
          Position(7, 16),
          Position(7, 17),
          Position(7, 18),
          Position(7, 19),
          Position(7, 20)
        ),
        Seq(
          Position(0, 20),
          Position(1, 20),
          Position(2, 20),
          Position(3, 20),
          Position(4, 20),
          Position(5, 20),
          Position(6, 20),
          Position(7, 20),
          Position(8, 20)
        ),
        Seq(
          Position(8, 12),
          Position(8, 13),
          Position(8, 14),
          Position(8, 15),
          Position(8, 16),
          Position(8, 17),
          Position(8, 18),
          Position(8, 19),
          Position(8, 20)
        ),
        Seq(
          Position(6, 6),
          Position(7, 6),
          Position(8, 6),
          Position(9, 6),
          Position(10, 6),
          Position(11, 6),
          Position(12, 6),
          Position(13, 6),
          Position(14, 6)
        ),
        Seq(
          Position(6, 6),
          Position(6, 7),
          Position(6, 8),
          Position(6, 9),
          Position(6, 10),
          Position(6, 11),
          Position(6, 12),
          Position(6, 13),
          Position(6, 14)
        ),
        Seq(
          Position(6, 7),
          Position(7, 7),
          Position(8, 7),
          Position(9, 7),
          Position(10, 7),
          Position(11, 7),
          Position(12, 7),
          Position(13, 7),
          Position(14, 7)
        ),
        Seq(
          Position(7, 6),
          Position(7, 7),
          Position(7, 8),
          Position(7, 9),
          Position(7, 10),
          Position(7, 11),
          Position(7, 12),
          Position(7, 13),
          Position(7, 14)
        ),
        Seq(
          Position(6, 8),
          Position(7, 8),
          Position(8, 8),
          Position(9, 8),
          Position(10, 8),
          Position(11, 8),
          Position(12, 8),
          Position(13, 8),
          Position(14, 8)
        ),
        Seq(
          Position(8, 6),
          Position(8, 7),
          Position(8, 8),
          Position(8, 9),
          Position(8, 10),
          Position(8, 11),
          Position(8, 12),
          Position(8, 13),
          Position(8, 14)
        ),
        Seq(
          Position(6, 9),
          Position(7, 9),
          Position(8, 9),
          Position(9, 9),
          Position(10, 9),
          Position(11, 9),
          Position(12, 9),
          Position(13, 9),
          Position(14, 9)
        ),
        Seq(
          Position(9, 6),
          Position(9, 7),
          Position(9, 8),
          Position(9, 9),
          Position(9, 10),
          Position(9, 11),
          Position(9, 12),
          Position(9, 13),
          Position(9, 14)
        ),
        Seq(
          Position(6, 10),
          Position(7, 10),
          Position(8, 10),
          Position(9, 10),
          Position(10, 10),
          Position(11, 10),
          Position(12, 10),
          Position(13, 10),
          Position(14, 10)
        ),
        Seq(
          Position(10, 6),
          Position(10, 7),
          Position(10, 8),
          Position(10, 9),
          Position(10, 10),
          Position(10, 11),
          Position(10, 12),
          Position(10, 13),
          Position(10, 14)
        ),
        Seq(
          Position(6, 11),
          Position(7, 11),
          Position(8, 11),
          Position(9, 11),
          Position(10, 11),
          Position(11, 11),
          Position(12, 11),
          Position(13, 11),
          Position(14, 11)
        ),
        Seq(
          Position(11, 6),
          Position(11, 7),
          Position(11, 8),
          Position(11, 9),
          Position(11, 10),
          Position(11, 11),
          Position(11, 12),
          Position(11, 13),
          Position(11, 14)
        ),
        Seq(
          Position(6, 12),
          Position(7, 12),
          Position(8, 12),
          Position(9, 12),
          Position(10, 12),
          Position(11, 12),
          Position(12, 12),
          Position(13, 12),
          Position(14, 12)
        ),
        Seq(
          Position(12, 6),
          Position(12, 7),
          Position(12, 8),
          Position(12, 9),
          Position(12, 10),
          Position(12, 11),
          Position(12, 12),
          Position(12, 13),
          Position(12, 14)
        ),
        Seq(
          Position(6, 13),
          Position(7, 13),
          Position(8, 13),
          Position(9, 13),
          Position(10, 13),
          Position(11, 13),
          Position(12, 13),
          Position(13, 13),
          Position(14, 13)
        ),
        Seq(
          Position(13, 6),
          Position(13, 7),
          Position(13, 8),
          Position(13, 9),
          Position(13, 10),
          Position(13, 11),
          Position(13, 12),
          Position(13, 13),
          Position(13, 14)
        ),
        Seq(
          Position(6, 14),
          Position(7, 14),
          Position(8, 14),
          Position(9, 14),
          Position(10, 14),
          Position(11, 14),
          Position(12, 14),
          Position(13, 14),
          Position(14, 14)
        ),
        Seq(
          Position(14, 6),
          Position(14, 7),
          Position(14, 8),
          Position(14, 9),
          Position(14, 10),
          Position(14, 11),
          Position(14, 12),
          Position(14, 13),
          Position(14, 14)
        ),
        Seq(
          Position(12, 0),
          Position(13, 0),
          Position(14, 0),
          Position(15, 0),
          Position(16, 0),
          Position(17, 0),
          Position(18, 0),
          Position(19, 0),
          Position(20, 0)
        ),
        Seq(
          Position(12, 0),
          Position(12, 1),
          Position(12, 2),
          Position(12, 3),
          Position(12, 4),
          Position(12, 5),
          Position(12, 6),
          Position(12, 7),
          Position(12, 8)
        ),
        Seq(
          Position(12, 1),
          Position(13, 1),
          Position(14, 1),
          Position(15, 1),
          Position(16, 1),
          Position(17, 1),
          Position(18, 1),
          Position(19, 1),
          Position(20, 1)
        ),
        Seq(
          Position(13, 0),
          Position(13, 1),
          Position(13, 2),
          Position(13, 3),
          Position(13, 4),
          Position(13, 5),
          Position(13, 6),
          Position(13, 7),
          Position(13, 8)
        ),
        Seq(
          Position(12, 2),
          Position(13, 2),
          Position(14, 2),
          Position(15, 2),
          Position(16, 2),
          Position(17, 2),
          Position(18, 2),
          Position(19, 2),
          Position(20, 2)
        ),
        Seq(
          Position(14, 0),
          Position(14, 1),
          Position(14, 2),
          Position(14, 3),
          Position(14, 4),
          Position(14, 5),
          Position(14, 6),
          Position(14, 7),
          Position(14, 8)
        ),
        Seq(
          Position(12, 3),
          Position(13, 3),
          Position(14, 3),
          Position(15, 3),
          Position(16, 3),
          Position(17, 3),
          Position(18, 3),
          Position(19, 3),
          Position(20, 3)
        ),
        Seq(
          Position(15, 0),
          Position(15, 1),
          Position(15, 2),
          Position(15, 3),
          Position(15, 4),
          Position(15, 5),
          Position(15, 6),
          Position(15, 7),
          Position(15, 8)
        ),
        Seq(
          Position(12, 4),
          Position(13, 4),
          Position(14, 4),
          Position(15, 4),
          Position(16, 4),
          Position(17, 4),
          Position(18, 4),
          Position(19, 4),
          Position(20, 4)
        ),
        Seq(
          Position(16, 0),
          Position(16, 1),
          Position(16, 2),
          Position(16, 3),
          Position(16, 4),
          Position(16, 5),
          Position(16, 6),
          Position(16, 7),
          Position(16, 8)
        ),
        Seq(
          Position(12, 5),
          Position(13, 5),
          Position(14, 5),
          Position(15, 5),
          Position(16, 5),
          Position(17, 5),
          Position(18, 5),
          Position(19, 5),
          Position(20, 5)
        ),
        Seq(
          Position(17, 0),
          Position(17, 1),
          Position(17, 2),
          Position(17, 3),
          Position(17, 4),
          Position(17, 5),
          Position(17, 6),
          Position(17, 7),
          Position(17, 8)
        ),
        Seq(
          Position(12, 6),
          Position(13, 6),
          Position(14, 6),
          Position(15, 6),
          Position(16, 6),
          Position(17, 6),
          Position(18, 6),
          Position(19, 6),
          Position(20, 6)
        ),
        Seq(
          Position(18, 0),
          Position(18, 1),
          Position(18, 2),
          Position(18, 3),
          Position(18, 4),
          Position(18, 5),
          Position(18, 6),
          Position(18, 7),
          Position(18, 8)
        ),
        Seq(
          Position(12, 7),
          Position(13, 7),
          Position(14, 7),
          Position(15, 7),
          Position(16, 7),
          Position(17, 7),
          Position(18, 7),
          Position(19, 7),
          Position(20, 7)
        ),
        Seq(
          Position(19, 0),
          Position(19, 1),
          Position(19, 2),
          Position(19, 3),
          Position(19, 4),
          Position(19, 5),
          Position(19, 6),
          Position(19, 7),
          Position(19, 8)
        ),
        Seq(
          Position(12, 8),
          Position(13, 8),
          Position(14, 8),
          Position(15, 8),
          Position(16, 8),
          Position(17, 8),
          Position(18, 8),
          Position(19, 8),
          Position(20, 8)
        ),
        Seq(
          Position(20, 0),
          Position(20, 1),
          Position(20, 2),
          Position(20, 3),
          Position(20, 4),
          Position(20, 5),
          Position(20, 6),
          Position(20, 7),
          Position(20, 8)
        ),
        Seq(
          Position(12, 12),
          Position(13, 12),
          Position(14, 12),
          Position(15, 12),
          Position(16, 12),
          Position(17, 12),
          Position(18, 12),
          Position(19, 12),
          Position(20, 12)
        ),
        Seq(
          Position(12, 12),
          Position(12, 13),
          Position(12, 14),
          Position(12, 15),
          Position(12, 16),
          Position(12, 17),
          Position(12, 18),
          Position(12, 19),
          Position(12, 20)
        ),
        Seq(
          Position(12, 13),
          Position(13, 13),
          Position(14, 13),
          Position(15, 13),
          Position(16, 13),
          Position(17, 13),
          Position(18, 13),
          Position(19, 13),
          Position(20, 13)
        ),
        Seq(
          Position(13, 12),
          Position(13, 13),
          Position(13, 14),
          Position(13, 15),
          Position(13, 16),
          Position(13, 17),
          Position(13, 18),
          Position(13, 19),
          Position(13, 20)
        ),
        Seq(
          Position(12, 14),
          Position(13, 14),
          Position(14, 14),
          Position(15, 14),
          Position(16, 14),
          Position(17, 14),
          Position(18, 14),
          Position(19, 14),
          Position(20, 14)
        ),
        Seq(
          Position(14, 12),
          Position(14, 13),
          Position(14, 14),
          Position(14, 15),
          Position(14, 16),
          Position(14, 17),
          Position(14, 18),
          Position(14, 19),
          Position(14, 20)
        ),
        Seq(
          Position(12, 15),
          Position(13, 15),
          Position(14, 15),
          Position(15, 15),
          Position(16, 15),
          Position(17, 15),
          Position(18, 15),
          Position(19, 15),
          Position(20, 15)
        ),
        Seq(
          Position(15, 12),
          Position(15, 13),
          Position(15, 14),
          Position(15, 15),
          Position(15, 16),
          Position(15, 17),
          Position(15, 18),
          Position(15, 19),
          Position(15, 20)
        ),
        Seq(
          Position(12, 16),
          Position(13, 16),
          Position(14, 16),
          Position(15, 16),
          Position(16, 16),
          Position(17, 16),
          Position(18, 16),
          Position(19, 16),
          Position(20, 16)
        ),
        Seq(
          Position(16, 12),
          Position(16, 13),
          Position(16, 14),
          Position(16, 15),
          Position(16, 16),
          Position(16, 17),
          Position(16, 18),
          Position(16, 19),
          Position(16, 20)
        ),
        Seq(
          Position(12, 17),
          Position(13, 17),
          Position(14, 17),
          Position(15, 17),
          Position(16, 17),
          Position(17, 17),
          Position(18, 17),
          Position(19, 17),
          Position(20, 17)
        ),
        Seq(
          Position(17, 12),
          Position(17, 13),
          Position(17, 14),
          Position(17, 15),
          Position(17, 16),
          Position(17, 17),
          Position(17, 18),
          Position(17, 19),
          Position(17, 20)
        ),
        Seq(
          Position(12, 18),
          Position(13, 18),
          Position(14, 18),
          Position(15, 18),
          Position(16, 18),
          Position(17, 18),
          Position(18, 18),
          Position(19, 18),
          Position(20, 18)
        ),
        Seq(
          Position(18, 12),
          Position(18, 13),
          Position(18, 14),
          Position(18, 15),
          Position(18, 16),
          Position(18, 17),
          Position(18, 18),
          Position(18, 19),
          Position(18, 20)
        ),
        Seq(
          Position(12, 19),
          Position(13, 19),
          Position(14, 19),
          Position(15, 19),
          Position(16, 19),
          Position(17, 19),
          Position(18, 19),
          Position(19, 19),
          Position(20, 19)
        ),
        Seq(
          Position(19, 12),
          Position(19, 13),
          Position(19, 14),
          Position(19, 15),
          Position(19, 16),
          Position(19, 17),
          Position(19, 18),
          Position(19, 19),
          Position(19, 20)
        ),
        Seq(
          Position(12, 20),
          Position(13, 20),
          Position(14, 20),
          Position(15, 20),
          Position(16, 20),
          Position(17, 20),
          Position(18, 20),
          Position(19, 20),
          Position(20, 20)
        ),
        Seq(
          Position(20, 12),
          Position(20, 13),
          Position(20, 14),
          Position(20, 15),
          Position(20, 16),
          Position(20, 17),
          Position(20, 18),
          Position(20, 19),
          Position(20, 20)
        )
      )
    }

    "calculate the correct fieldGroups" in {
      val sudoku = TestPuzzles.samuraiSudoku

      sudoku.fieldGroups mustBe Seq(
        Seq(
          Position(0, 0),
          Position(0, 1),
          Position(0, 2),
          Position(1, 0),
          Position(1, 1),
          Position(1, 2),
          Position(2, 0),
          Position(2, 1),
          Position(2, 2)
        ),
        Seq(
          Position(0, 3),
          Position(0, 4),
          Position(0, 5),
          Position(1, 3),
          Position(1, 4),
          Position(1, 5),
          Position(2, 3),
          Position(2, 4),
          Position(2, 5)
        ),
        Seq(
          Position(0, 6),
          Position(0, 7),
          Position(0, 8),
          Position(1, 6),
          Position(1, 7),
          Position(1, 8),
          Position(2, 6),
          Position(2, 7),
          Position(2, 8)
        ),
        Seq(
          Position(0, 12),
          Position(0, 13),
          Position(0, 14),
          Position(1, 12),
          Position(1, 13),
          Position(1, 14),
          Position(2, 12),
          Position(2, 13),
          Position(2, 14)
        ),
        Seq(
          Position(0, 15),
          Position(0, 16),
          Position(0, 17),
          Position(1, 15),
          Position(1, 16),
          Position(1, 17),
          Position(2, 15),
          Position(2, 16),
          Position(2, 17)
        ),
        Seq(
          Position(0, 18),
          Position(0, 19),
          Position(0, 20),
          Position(1, 18),
          Position(1, 19),
          Position(1, 20),
          Position(2, 18),
          Position(2, 19),
          Position(2, 20)
        ),
        Seq(
          Position(3, 0),
          Position(3, 1),
          Position(3, 2),
          Position(4, 0),
          Position(4, 1),
          Position(4, 2),
          Position(5, 0),
          Position(5, 1),
          Position(5, 2)
        ),
        Seq(
          Position(3, 3),
          Position(3, 4),
          Position(3, 5),
          Position(4, 3),
          Position(4, 4),
          Position(4, 5),
          Position(5, 3),
          Position(5, 4),
          Position(5, 5)
        ),
        Seq(
          Position(3, 6),
          Position(3, 7),
          Position(3, 8),
          Position(4, 6),
          Position(4, 7),
          Position(4, 8),
          Position(5, 6),
          Position(5, 7),
          Position(5, 8)
        ),
        Seq(
          Position(3, 12),
          Position(3, 13),
          Position(3, 14),
          Position(4, 12),
          Position(4, 13),
          Position(4, 14),
          Position(5, 12),
          Position(5, 13),
          Position(5, 14)
        ),
        Seq(
          Position(3, 15),
          Position(3, 16),
          Position(3, 17),
          Position(4, 15),
          Position(4, 16),
          Position(4, 17),
          Position(5, 15),
          Position(5, 16),
          Position(5, 17)
        ),
        Seq(
          Position(3, 18),
          Position(3, 19),
          Position(3, 20),
          Position(4, 18),
          Position(4, 19),
          Position(4, 20),
          Position(5, 18),
          Position(5, 19),
          Position(5, 20)
        ),
        Seq(
          Position(6, 0),
          Position(6, 1),
          Position(6, 2),
          Position(7, 0),
          Position(7, 1),
          Position(7, 2),
          Position(8, 0),
          Position(8, 1),
          Position(8, 2)
        ),
        Seq(
          Position(6, 3),
          Position(6, 4),
          Position(6, 5),
          Position(7, 3),
          Position(7, 4),
          Position(7, 5),
          Position(8, 3),
          Position(8, 4),
          Position(8, 5)
        ),
        Seq(
          Position(6, 6),
          Position(6, 7),
          Position(6, 8),
          Position(7, 6),
          Position(7, 7),
          Position(7, 8),
          Position(8, 6),
          Position(8, 7),
          Position(8, 8)
        ),
        Seq(
          Position(6, 9),
          Position(6, 10),
          Position(6, 11),
          Position(7, 9),
          Position(7, 10),
          Position(7, 11),
          Position(8, 9),
          Position(8, 10),
          Position(8, 11)
        ),
        Seq(
          Position(6, 12),
          Position(6, 13),
          Position(6, 14),
          Position(7, 12),
          Position(7, 13),
          Position(7, 14),
          Position(8, 12),
          Position(8, 13),
          Position(8, 14)
        ),
        Seq(
          Position(6, 15),
          Position(6, 16),
          Position(6, 17),
          Position(7, 15),
          Position(7, 16),
          Position(7, 17),
          Position(8, 15),
          Position(8, 16),
          Position(8, 17)
        ),
        Seq(
          Position(6, 18),
          Position(6, 19),
          Position(6, 20),
          Position(7, 18),
          Position(7, 19),
          Position(7, 20),
          Position(8, 18),
          Position(8, 19),
          Position(8, 20)
        ),
        Seq(
          Position(9, 6),
          Position(9, 7),
          Position(9, 8),
          Position(10, 6),
          Position(10, 7),
          Position(10, 8),
          Position(11, 6),
          Position(11, 7),
          Position(11, 8)
        ),
        Seq(
          Position(9, 9),
          Position(9, 10),
          Position(9, 11),
          Position(10, 9),
          Position(10, 10),
          Position(10, 11),
          Position(11, 9),
          Position(11, 10),
          Position(11, 11)
        ),
        Seq(
          Position(9, 12),
          Position(9, 13),
          Position(9, 14),
          Position(10, 12),
          Position(10, 13),
          Position(10, 14),
          Position(11, 12),
          Position(11, 13),
          Position(11, 14)
        ),
        Seq(
          Position(12, 0),
          Position(12, 1),
          Position(12, 2),
          Position(13, 0),
          Position(13, 1),
          Position(13, 2),
          Position(14, 0),
          Position(14, 1),
          Position(14, 2)
        ),
        Seq(
          Position(12, 3),
          Position(12, 4),
          Position(12, 5),
          Position(13, 3),
          Position(13, 4),
          Position(13, 5),
          Position(14, 3),
          Position(14, 4),
          Position(14, 5)
        ),
        Seq(
          Position(12, 6),
          Position(12, 7),
          Position(12, 8),
          Position(13, 6),
          Position(13, 7),
          Position(13, 8),
          Position(14, 6),
          Position(14, 7),
          Position(14, 8)
        ),
        Seq(
          Position(12, 9),
          Position(12, 10),
          Position(12, 11),
          Position(13, 9),
          Position(13, 10),
          Position(13, 11),
          Position(14, 9),
          Position(14, 10),
          Position(14, 11)
        ),
        Seq(
          Position(12, 12),
          Position(12, 13),
          Position(12, 14),
          Position(13, 12),
          Position(13, 13),
          Position(13, 14),
          Position(14, 12),
          Position(14, 13),
          Position(14, 14)
        ),
        Seq(
          Position(12, 15),
          Position(12, 16),
          Position(12, 17),
          Position(13, 15),
          Position(13, 16),
          Position(13, 17),
          Position(14, 15),
          Position(14, 16),
          Position(14, 17)
        ),
        Seq(
          Position(12, 18),
          Position(12, 19),
          Position(12, 20),
          Position(13, 18),
          Position(13, 19),
          Position(13, 20),
          Position(14, 18),
          Position(14, 19),
          Position(14, 20)
        ),
        Seq(
          Position(15, 0),
          Position(15, 1),
          Position(15, 2),
          Position(16, 0),
          Position(16, 1),
          Position(16, 2),
          Position(17, 0),
          Position(17, 1),
          Position(17, 2)
        ),
        Seq(
          Position(15, 3),
          Position(15, 4),
          Position(15, 5),
          Position(16, 3),
          Position(16, 4),
          Position(16, 5),
          Position(17, 3),
          Position(17, 4),
          Position(17, 5)
        ),
        Seq(
          Position(15, 6),
          Position(15, 7),
          Position(15, 8),
          Position(16, 6),
          Position(16, 7),
          Position(16, 8),
          Position(17, 6),
          Position(17, 7),
          Position(17, 8)
        ),
        Seq(
          Position(15, 12),
          Position(15, 13),
          Position(15, 14),
          Position(16, 12),
          Position(16, 13),
          Position(16, 14),
          Position(17, 12),
          Position(17, 13),
          Position(17, 14)
        ),
        Seq(
          Position(15, 15),
          Position(15, 16),
          Position(15, 17),
          Position(16, 15),
          Position(16, 16),
          Position(16, 17),
          Position(17, 15),
          Position(17, 16),
          Position(17, 17)
        ),
        Seq(
          Position(15, 18),
          Position(15, 19),
          Position(15, 20),
          Position(16, 18),
          Position(16, 19),
          Position(16, 20),
          Position(17, 18),
          Position(17, 19),
          Position(17, 20)
        ),
        Seq(
          Position(18, 0),
          Position(18, 1),
          Position(18, 2),
          Position(19, 0),
          Position(19, 1),
          Position(19, 2),
          Position(20, 0),
          Position(20, 1),
          Position(20, 2)
        ),
        Seq(
          Position(18, 3),
          Position(18, 4),
          Position(18, 5),
          Position(19, 3),
          Position(19, 4),
          Position(19, 5),
          Position(20, 3),
          Position(20, 4),
          Position(20, 5)
        ),
        Seq(
          Position(18, 6),
          Position(18, 7),
          Position(18, 8),
          Position(19, 6),
          Position(19, 7),
          Position(19, 8),
          Position(20, 6),
          Position(20, 7),
          Position(20, 8)
        ),
        Seq(
          Position(18, 12),
          Position(18, 13),
          Position(18, 14),
          Position(19, 12),
          Position(19, 13),
          Position(19, 14),
          Position(20, 12),
          Position(20, 13),
          Position(20, 14)
        ),
        Seq(
          Position(18, 15),
          Position(18, 16),
          Position(18, 17),
          Position(19, 15),
          Position(19, 16),
          Position(19, 17),
          Position(20, 15),
          Position(20, 16),
          Position(20, 17)
        ),
        Seq(
          Position(18, 18),
          Position(18, 19),
          Position(18, 20),
          Position(19, 18),
          Position(19, 19),
          Position(19, 20),
          Position(20, 18),
          Position(20, 19),
          Position(20, 20)
        )
      )

    }
  }
}
