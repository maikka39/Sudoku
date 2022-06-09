package sudoku.solvers

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SudokuSolverSpec extends AnyWordSpec with Matchers {
  "SudokuSolver" must {
    "have an implicit default solver defined" in {
      SudokuSolver.defaultSolver
    }
  }
}
