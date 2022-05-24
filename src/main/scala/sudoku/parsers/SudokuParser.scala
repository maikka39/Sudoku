package sudoku.parsers

import sudoku.errors.{InvalidSudokuError, SudokuError, SudokuNotFoundError}
import sudoku.models.Sudoku.SudokuField
import sudoku.models.{JigsawSudoku, Position, RegularSudoku, SamuraiSudoku, Sudoku}

import java.io.FileNotFoundException
import scala.io.Source
import scala.util.{Failure, Success, Try}

object SudokuParser {
  def parse(filePath: String): Either[SudokuError, Sudoku] = {
    val puzzleType = filePath.split("\\.").last

    val triedInputData = Try {
      val source = Source.fromFile(filePath)
      try source.getLines.filter(!_.startsWith("#")).mkString("\n")
      finally source.close()
    }

    triedInputData match {
      case Failure(exception) =>
        exception match {
          case _: FileNotFoundException => Left(SudokuNotFoundError())
          case e                        => throw e
        }
      case Success(inputData) =>
        puzzleType match {
          case "4x4" | "6x6" | "9x9" => Right(RegularSudokuParser.parse(inputData))
          case "jigsaw"              => Right(JigsawSudokuParser.parse(inputData))
          case "samurai"             => Right(SamuraiSudokuParser.parse(inputData))
          case _                     => Left(InvalidSudokuError())
        }
    }
  }

  private def charToSudokuField(char: Char): SudokuField = {
    val n = char.toInt - 48
    SudokuField(if (n != 0) Some(n) else None, isPermanent = n != 0)
  }

  private object RegularSudokuParser {
    def parse(inputData: String): RegularSudoku = {
      val size = Math.sqrt(inputData.length).intValue

      val grid = inputData
        .map(charToSudokuField)
        .sliding(size, size)
        .toSeq
        .transpose

      new RegularSudoku(grid)
    }
  }

  private object SamuraiSudokuParser {
    private val filler =
      Seq(SudokuField(None, isActive = false), SudokuField(None, isActive = false), SudokuField(None, isActive = false))

    def parse(inputData: String): SamuraiSudoku = {
      val lines = inputData.split("\n")

      val regularSudokuArray = lines.map(RegularSudokuParser.parse)

      // noinspection ZeroIndexToHead
      val grid = Seq(
        regularSudokuArray(0).grid(0) ++ filler ++ regularSudokuArray(3).grid(0),
        regularSudokuArray(0).grid(1) ++ filler ++ regularSudokuArray(3).grid(1),
        regularSudokuArray(0).grid(2) ++ filler ++ regularSudokuArray(3).grid(2),
        regularSudokuArray(0).grid(3) ++ filler ++ regularSudokuArray(3).grid(3),
        regularSudokuArray(0).grid(4) ++ filler ++ regularSudokuArray(3).grid(4),
        regularSudokuArray(0).grid(5) ++ filler ++ regularSudokuArray(3).grid(5),
        regularSudokuArray(0).grid(6) ++ regularSudokuArray(2).grid(0).slice(3, 6) ++ regularSudokuArray(3).grid(6),
        regularSudokuArray(0).grid(7) ++ regularSudokuArray(2).grid(1).slice(3, 6) ++ regularSudokuArray(3).grid(7),
        regularSudokuArray(0).grid(8) ++ regularSudokuArray(2).grid(2).slice(3, 6) ++ regularSudokuArray(3).grid(8),
        filler ++ filler ++ regularSudokuArray(2).grid(3) ++ filler ++ filler,
        filler ++ filler ++ regularSudokuArray(2).grid(4) ++ filler ++ filler,
        filler ++ filler ++ regularSudokuArray(2).grid(5) ++ filler ++ filler,
        regularSudokuArray(1).grid(0) ++ regularSudokuArray(2).grid(6).slice(3, 6) ++ regularSudokuArray(4).grid(0),
        regularSudokuArray(1).grid(1) ++ regularSudokuArray(2).grid(7).slice(3, 6) ++ regularSudokuArray(4).grid(1),
        regularSudokuArray(1).grid(2) ++ regularSudokuArray(2).grid(8).slice(3, 6) ++ regularSudokuArray(4).grid(2),
        regularSudokuArray(0).grid(3) ++ filler ++ regularSudokuArray(3).grid(3),
        regularSudokuArray(0).grid(4) ++ filler ++ regularSudokuArray(3).grid(4),
        regularSudokuArray(0).grid(5) ++ filler ++ regularSudokuArray(3).grid(5),
        regularSudokuArray(0).grid(6) ++ filler ++ regularSudokuArray(3).grid(6),
        regularSudokuArray(0).grid(7) ++ filler ++ regularSudokuArray(3).grid(7),
        regularSudokuArray(0).grid(8) ++ filler ++ regularSudokuArray(3).grid(8)
      )

      new SamuraiSudoku(grid)
    }
  }

  private object JigsawSudokuParser {
    def parse(inputData: String): JigsawSudoku = {
      val jigsawData = inputData.drop(10).split("=")

      val grid = jigsawData
        .map(_(0))
        .map(charToSudokuField)
        .sliding(9, 9)
        .toSeq
        .transpose

      val fieldGroups = jigsawData
        .map(_(2))
        .map(_.toInt - 48)
        .sliding(9, 9)
        .toSeq
        .transpose
        .zipWithIndex
        .flatMap { case (col, x) => col.zipWithIndex.map { case (groupNumber, y) => (groupNumber, Position(x, y)) } }
        .groupBy(_._1)
        .view
        .mapValues(tupledPositions => tupledPositions.map(_._2))
        .toSeq
        .sortBy(_._1)
        .map(_._2)

      new JigsawSudoku(grid, fieldGroups)
    }
  }
}
