package sudoku.parsers

import sudoku.models.RegularSudoku

protected object RegularSudokuParser extends SudokuParser {
  def parse(inputData: String): RegularSudoku = {
    val size = Math.sqrt(inputData.length).intValue

    val grid = inputData
      .map(charToSudokuField)
      .sliding(size, size)
      .toSeq

    new RegularSudoku(grid)
  }
}
