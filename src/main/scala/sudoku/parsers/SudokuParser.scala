package sudoku.parsers

import sudoku.errors.{InvalidSudokuError, SudokuError, SudokuNotFoundError}
import sudoku.models.Sudoku
import sudoku.models.Sudoku.SudokuField

import java.io.FileNotFoundException
import scala.io.Source
import scala.util.{Failure, Success, Try}

trait SudokuParser {
  val supportedFormats: Seq[String]

  def parse(inputData: String): Sudoku

  protected def charToSudokuField(char: Char): SudokuField = {
    val n = char.toInt - 48
    SudokuField(if (n != 0) Some(n) else None, isPermanent = n != 0)
  }
}

object SudokuParser {
  private val parsers               = Seq(JigsawSudokuParser, RegularSudokuParser, SamuraiSudokuParser)
  val supportedFormats: Seq[String] = parsers.flatMap(p => p.supportedFormats)

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
        parsers
          .find(p => p.supportedFormats.contains(puzzleType))
          .flatMap(p => Try(p.parse(inputData)).toOption)
          .fold[Either[SudokuError, Sudoku]] {
            Left(InvalidSudokuError())
          } { sudoku =>
            Right(sudoku)
          }

    }
  }
}
