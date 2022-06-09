package sudoku.parsers

import sudoku.models.Position
import sudoku.models.Sudoku.{FieldGroup, Grid}

object Helpers {
  def calculateRegularRowsAndCols(grid: Grid): Seq[FieldGroup] = {
    val rowColListList = for {
      n <- grid.indices
      row = grid.indices.map(x => Position(n, x))
      col = grid.indices.map(y => Position(y, n))
    } yield Seq(row, col)

    rowColListList.flatten
  }
}
