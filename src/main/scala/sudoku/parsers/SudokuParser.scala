package sudoku.parsers

import sudoku.errors.{InvalidSudokuError, SudokuError, SudokuNotFoundError}
import sudoku.models.Sudoku
import sudoku.models.Sudoku.SudokuField

import java.io.FileNotFoundException
import scala.io.Source
import scala.util.{Failure, Success, Try}

trait SudokuParser {
  def parse(inputData: String): Sudoku

  protected def charToSudokuField(char: Char): SudokuField = {
    val n = char.toInt - 48
    SudokuField(if (n != 0) Some(n) else None, isPermanent = n != 0)
  }
}

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
}
