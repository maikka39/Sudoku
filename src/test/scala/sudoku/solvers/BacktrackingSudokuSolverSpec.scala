package sudoku.solvers

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import sudoku.testUtils.TestPuzzles

class BacktrackingSudokuSolverSpec extends AnyWordSpec with Matchers {
  "solve" should {
    "solve a regular 4x4 sudoku" in {
      val sudoku       = TestPuzzles.regularSudoku4x4
      val solvedSudoku = BacktrackingSudokuSolver.solve(sudoku)

      println(solvedSudoku.get.grid)

      solvedSudoku.get.isValid mustBe true
    }

    "solve a regular 6x6 sudoku" in {
      val sudoku       = TestPuzzles.regularSudoku6x6
      val solvedSudoku = BacktrackingSudokuSolver.solve(sudoku)

      solvedSudoku.get.isValid mustBe true
    }

    "solve a regular 9x9 sudoku" in {
      val sudoku       = TestPuzzles.regularSudoku9x9
      val solvedSudoku = BacktrackingSudokuSolver.solve(sudoku)

      solvedSudoku.get.isValid mustBe true
    }

    "solve a jigsaw sudoku" in {
      val sudoku       = TestPuzzles.jigsawSudoku
      val solvedSudoku = BacktrackingSudokuSolver.solve(sudoku)

      solvedSudoku.get.isValid mustBe true
    }
  }
}
