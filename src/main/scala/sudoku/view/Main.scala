package sudoku.view

import sudoku.models.Game
import sudoku.parsers.SudokuParser
import sudoku.view.display.Display
import sudoku.view.display.Display.{Position, TextStyle}
import sudoku.view.printers.{InGameMenuPrinter, SudokuPrinter}

object Main extends GameSetup with App {
  Display.init()

  drawGame()

  Display.getKeyPress

  Display.quit()
}

trait GameSetup {
  private val sudokuPosition = Position(3, 3)

  lazy val game: Game = new Game {
    sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.samurai").toOption
  }

  def drawGame(): Unit = {
    game.sudoku match {
      case Some(sudoku) => {
        SudokuPrinter.print(sudokuPosition, sudoku)
        InGameMenuPrinter.print(Position(sudokuPosition.x + sudoku.grid.length * 4 + 5, sudokuPosition.y + 3))
      }
      case None => ???
    }
  }
}
