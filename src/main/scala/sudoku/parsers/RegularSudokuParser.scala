package sudoku.parsers

import sudoku.models.RegularSudoku

protected object RegularSudokuParser extends SudokuParser {
  val supportedFormats: Seq[String] = Seq("4x4", "6x6", "9x9")

  def parse(inputData: String): RegularSudoku = {
    val size = Math.sqrt(inputData.length).intValue

    val grid = inputData
      .map(charToSudokuField)
      .sliding(size, size)
      .toSeq

    new RegularSudoku(grid)
  }
}
