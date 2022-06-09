package sudoku.parsers

import sudoku.models.{JigsawSudoku, Position}

protected object JigsawSudokuParser extends SudokuParser {
  val supportedFormats: Seq[String] = Seq("jigsaw")

  def parse(inputData: String): JigsawSudoku = {
    val jigsawData = inputData.drop(10).split("=").toSeq

    val grid = jigsawData
      .map(_(0))
      .map(charToSudokuField)
      .sliding(9, 9)
      .toSeq

    val fieldGroups = jigsawData
      .map(_(2))
      .map(_.toInt - 48)
      .sliding(9, 9)
      .toSeq
      .zipWithIndex
      .flatMap { case (row, y) =>
        row.zipWithIndex.map(group => (group._1, Position(y, group._2)))
      }
      .groupBy(_._1)
      .view
      .mapValues(tupledPositions => tupledPositions.map(_._2))
      .toSeq
      .sortBy(_._1)
      .map(_._2)

    new JigsawSudoku(grid, fieldGroups)
  }
}
