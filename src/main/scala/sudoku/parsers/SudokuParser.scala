package sudoku.parsers

import sudoku.models.Sudoku.SudokuField
import sudoku.models.{RegularSudoku, Sudoku}

import scala.io.Source

object SudokuParser {
  def parse(filePath: String): Sudoku = {
    val puzzleType = filePath.split("\\.").last

    val source = Source.fromFile(filePath)
    val inputData =
      try source.getLines.mkString
      finally source.close()

    puzzleType match {
      case "4x4" | "6x6" | "9x9" => RegularSudokuParser.parse(inputData)
      case "jigsaw"              => ???
      case "samurai"             => ???
    }

  }
}

object RegularSudokuParser {
  def parse(inputData: String): RegularSudoku = {
    val size = Math.sqrt(inputData.length).intValue

    val grid = inputData
      .sliding(size, size)
      .toSeq
      .transpose
      .map(a =>
        a
          .map(_.toInt - 48)
          .map(n => SudokuField(if (n != 0) Some(n) else None, Seq(), isPermanent = n != 0))
      )

    RegularSudoku(grid)
  }
}
