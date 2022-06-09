package sudoku.models

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PositionSpec extends AnyWordSpec with Matchers {
  "Position" should {
    "add two positions together correctly" in {
      val pos1 = Position(3, 4)
      val pos2 = Position(2, 5)

      (pos1 + pos2) mustBe Position(5, 9)
    }
  }
}
