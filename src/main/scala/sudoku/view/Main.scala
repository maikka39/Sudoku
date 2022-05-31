package sudoku.view

import sudoku.models.Game
import sudoku.parsers.SudokuParser
import sudoku.view.config.Config
import sudoku.view.display.Display
import sudoku.view.display.Display.Position
import sudoku.view.printers.{ErrorPrinter, InGameMenuPrinter, SudokuPrinter}

object Main extends GameSetup with App {
  Display.init()
  Display.setEcho(false)

  draw()
  while (true) { loop() }
}

trait GameSetup {
  lazy val game: Game = new Game {
    sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.9x9").toOption
//    sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.samurai").toOption
  }

  def loop(): Unit = {
    val key = Display.getKeyPress
    Actions.actions
      .find(_.keybind == key)
      .foreach(action =>
        action.onCall().flatMap(game.executeAction) match {
          case Some(value) => ErrorPrinter.print(Config.errorPosition, value)
          case None        => draw()
        }
      )
  }

  def draw(): Unit = {
    game.sudoku match {
      case Some(sudoku) => {
        SudokuPrinter.print(Config.sudokuPosition, sudoku)
        InGameMenuPrinter.print(
          Position(Config.sudokuPosition.x + sudoku.grid.length * 4 + 5, Config.sudokuPosition.y + 3)
        )
      }
      case None => ???
    }
  }
}
