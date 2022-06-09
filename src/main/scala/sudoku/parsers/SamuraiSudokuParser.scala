package sudoku.parsers

import sudoku.models.{Position, Sudoku}
import sudoku.models.Sudoku.{FieldGroup, SudokuField}

protected object SamuraiSudokuParser extends SudokuParser {
  val supportedFormats: Seq[String] = Seq("samurai")

  private val filler =
    Seq(SudokuField(None, isActive = false), SudokuField(None, isActive = false), SudokuField(None, isActive = false))

  def parse(inputData: String): Sudoku = {
    val lines = inputData.split("\n")

    val regularSudokuArray = lines.map(RegularSudokuParser.parse)

    // noinspection ZeroIndexToHead
    val grid = Seq(
      regularSudokuArray(0).grid(0) ++ filler ++ regularSudokuArray(1).grid(0),
      regularSudokuArray(0).grid(1) ++ filler ++ regularSudokuArray(1).grid(1),
      regularSudokuArray(0).grid(2) ++ filler ++ regularSudokuArray(1).grid(2),
      regularSudokuArray(0).grid(3) ++ filler ++ regularSudokuArray(1).grid(3),
      regularSudokuArray(0).grid(4) ++ filler ++ regularSudokuArray(1).grid(4),
      regularSudokuArray(0).grid(5) ++ filler ++ regularSudokuArray(1).grid(5),
      regularSudokuArray(0).grid(6) ++ regularSudokuArray(2).grid(0).slice(3, 6) ++ regularSudokuArray(1).grid(6),
      regularSudokuArray(0).grid(7) ++ regularSudokuArray(2).grid(1).slice(3, 6) ++ regularSudokuArray(1).grid(7),
      regularSudokuArray(0).grid(8) ++ regularSudokuArray(2).grid(2).slice(3, 6) ++ regularSudokuArray(1).grid(8),
      filler ++ filler ++ regularSudokuArray(2).grid(3) ++ filler ++ filler,
      filler ++ filler ++ regularSudokuArray(2).grid(4) ++ filler ++ filler,
      filler ++ filler ++ regularSudokuArray(2).grid(5) ++ filler ++ filler,
      regularSudokuArray(3).grid(0) ++ regularSudokuArray(2).grid(6).slice(3, 6) ++ regularSudokuArray(4).grid(0),
      regularSudokuArray(3).grid(1) ++ regularSudokuArray(2).grid(7).slice(3, 6) ++ regularSudokuArray(4).grid(1),
      regularSudokuArray(3).grid(2) ++ regularSudokuArray(2).grid(8).slice(3, 6) ++ regularSudokuArray(4).grid(2),
      regularSudokuArray(3).grid(3) ++ filler ++ regularSudokuArray(4).grid(3),
      regularSudokuArray(3).grid(4) ++ filler ++ regularSudokuArray(4).grid(4),
      regularSudokuArray(3).grid(5) ++ filler ++ regularSudokuArray(4).grid(5),
      regularSudokuArray(3).grid(6) ++ filler ++ regularSudokuArray(4).grid(6),
      regularSudokuArray(3).grid(7) ++ filler ++ regularSudokuArray(4).grid(7),
      regularSudokuArray(3).grid(8) ++ filler ++ regularSudokuArray(4).grid(8)
    )

    val fieldGroups: Seq[FieldGroup] = {
      val coordinates = for {
        fieldStartY <- grid.indices by 3
        fieldStartX <- grid.indices by 3
        if grid(fieldStartY)(fieldStartX).isActive
        y <- fieldStartY until fieldStartY + 3
        x <- fieldStartX until fieldStartX + 3
      } yield Position(y, x)

      coordinates
        .sliding(9, 9)
        .toSeq
    }

    val rowsAndCols: Seq[FieldGroup] = {
      val rowColListList = for {
        centerY <- 4 until 17 by 6
        centerX <- 4 until 17 by 6
        if grid(centerY)(centerX).isActive
        n <- -4 until 5
        row = (-4 until 5).map(x => Position(centerY + n, centerX + x))
        col = (-4 until 5).map(y => Position(centerY + y, centerX + n))
      } yield Seq(row, col)

      rowColListList.flatten
    }

    Sudoku(grid, fieldGroups, rowsAndCols)
  }
}
