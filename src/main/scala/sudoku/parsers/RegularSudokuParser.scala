package sudoku.parsers

import sudoku.models.Sudoku.FieldGroup
import sudoku.models.{Position, Sudoku}

protected object RegularSudokuParser extends SudokuParser {
  val supportedFormats: Seq[String] = Seq("4x4", "6x6", "9x9")

  def parse(inputData: String): Sudoku = {
    val size = Math.sqrt(inputData.length).intValue

    val grid = inputData
      .map(charToSudokuField)
      .sliding(size, size)
      .toSeq

    val fieldGroups: Seq[FieldGroup] = {
      val fieldSize = grid.length match {
        case 4 => (2, 2)
        case 6 => (2, 3)
        case 9 => (3, 3)
      }

      val coordinates = for {
        fieldStartY <- grid.indices by fieldSize._1
        fieldStartX <- grid.indices by fieldSize._2
        y           <- fieldStartY until fieldStartY + fieldSize._1
        x           <- fieldStartX until fieldStartX + fieldSize._2
      } yield Position(y, x)

      coordinates
        .sliding(grid.length, grid.length)
        .toSeq
    }

    Sudoku(grid, fieldGroups, Helpers.calculateRegularRowsAndCols(grid))
  }
}
