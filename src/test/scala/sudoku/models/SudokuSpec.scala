package sudoku.models

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must.Matchers
import sudoku.models.Sudoku.SudokuField

class SudokuSpec extends AnyWordSpec with Matchers {
  "RegularSudoku" should {
    "calculate the correct fieldGroups for a 4x4 grid" in {
      val sudoku = RegularSudoku(List.range(0, 4).map(_ => List.range(0, 4).map(n => SudokuField(Some(n), Seq()))))

      sudoku.fieldGroups mustBe List(
        Seq(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1)),
        Seq(Position(0, 2), Position(0, 3), Position(1, 2), Position(1, 3)),
        Seq(Position(2, 0), Position(2, 1), Position(3, 0), Position(3, 1)),
        Seq(Position(2, 2), Position(2, 3), Position(3, 2), Position(3, 3))
      )
    }

    "calculate the correct fieldGroups for a 6x6 grid" in {
      val sudoku = RegularSudoku(List.range(0, 6).map(_ => List.range(0, 6).map(n => SudokuField(Some(n), Seq()))))

      sudoku.fieldGroups mustBe List(
        Seq(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1), Position(2, 0), Position(2, 1)),
        Seq(Position(0, 2), Position(0, 3), Position(1, 2), Position(1, 3), Position(2, 2), Position(2, 3)),
        Seq(Position(0, 4), Position(0, 5), Position(1, 4), Position(1, 5), Position(2, 4), Position(2, 5)),
        Seq(Position(3, 0), Position(3, 1), Position(4, 0), Position(4, 1), Position(5, 0), Position(5, 1)),
        Seq(Position(3, 2), Position(3, 3), Position(4, 2), Position(4, 3), Position(5, 2), Position(5, 3)),
        Seq(Position(3, 4), Position(3, 5), Position(4, 4), Position(4, 5), Position(5, 4), Position(5, 5))
      )
    }

    "calculate the correct fieldGroups for a 9x9 grid" in {
      val sudoku = RegularSudoku(List.range(0, 9).map(_ => List.range(0, 9).map(n => SudokuField(Some(n), Seq()))))

      sudoku.fieldGroups mustBe List(
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

    "calculate the correct rows and columns" in {
      val sudoku = RegularSudoku(List.range(0, 4).map(_ => List.range(0, 4).map(n => SudokuField(Some(n), Seq()))))

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
  }
}
