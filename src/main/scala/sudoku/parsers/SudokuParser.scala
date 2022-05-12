package sudoku.parsers

import sudoku.models.Sudoku.SudokuField
import sudoku.models.{JigsawSudoku, Position, RegularSudoku, Sudoku}

import scala.io.Source

object SudokuParser {
  def parse(filePath: String): Sudoku = {
    val puzzleType = filePath.split("\\.").last

    val source = Source.fromFile(filePath)
    val inputData =
      try source.getLines.filter(!_.startsWith("#")).mkString
      finally source.close()

    puzzleType match {
      case "4x4" | "6x6" | "9x9" => RegularSudokuParser.parse(inputData)
      case "jigsaw"              => JigsawSudokuParser.parse(inputData)
      case "samurai"             => ???
    }

  }
}

object RegularSudokuParser {
  def parse(inputData: String): RegularSudoku = {
    val size = Math.sqrt(inputData.length).intValue

    val grid = inputData
      .map(_.toInt - 48)
      .map(n => SudokuField(if (n != 0) Some(n) else None, Seq(), isPermanent = n != 0))
      .sliding(size, size)
      .toSeq
      .transpose

    RegularSudoku(grid)
  }
}

object JigsawSudokuParser {
  def parse(inputData: String): JigsawSudoku = {
    val jigsawData = inputData.drop(10).split("=")

    val grid = jigsawData
      .map(_(0))
      .map(_.toInt - 48)
      .map(n => SudokuField(if (n != 0) Some(n) else None, Seq(), isPermanent = n != 0))
      .sliding(9, 9)
      .toSeq
      .transpose

    val groupNumbers = jigsawData
      .map(_(2))
      .map(_.toInt - 48)
      .sliding(9, 9)
      .toSeq
      .transpose

    val groupPositions = for {
      (col, x)         <- groupNumbers.zipWithIndex
      (groupNumber, y) <- col.zipWithIndex
    } yield (groupNumber, Position(x, y))

    val fieldGroups = groupPositions
      .groupBy(_._1)
      .view
      .mapValues(positions => positions.map(_._2))
      .toSeq
      .sortBy(_._1)
      .map(_._2)

    JigsawSudoku(grid, fieldGroups)
  }
}
