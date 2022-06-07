package view

import sudoku.models.Game
import sudoku.parsers.SudokuParser
import view.display.Display.DisplayPosition
import view.config.Config
import view.display.Display
import view.printers.{ErrorPrinter, InGameMenuPrinter, SudokuPrinter}

case class State(game: Game, var isHelpMode: Boolean = false)

object Main extends GameSetup with App {
  Display.init()
  Display.setEcho(false)
  Display.moveCursor(Config.sudokuPosition.y + 1, Config.sudokuPosition.x + 2)

  draw()
  while (true) { loop() }
}

trait GameSetup {
  val state: State = State(new Game {
    //    sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.9x9").toOption
    //    sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.samurai").toOption
    sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.jigsaw").toOption
  })

  def loop(): Unit = {
    val key = Display.getKeyPress
    Actions.actions
      .find(_.keybind == key)
      .foreach(action =>
        action.onCall(state).flatMap(state.game.executeAction) match {
          case Some(value) => ErrorPrinter.print(value)
          case None        => draw()
        }
      )
  }

  def draw(): Unit = {
    val cursorPosition = Display.cursorPosition
    state.game.sudoku match {
      case Some(sudoku) => {
        ErrorPrinter.clear()
        SudokuPrinter.print(sudoku)
        InGameMenuPrinter.print(
          DisplayPosition(Config.sudokuPosition.y + 3, Config.sudokuPosition.x + sudoku.grid.length * 4 + 5)
        )
        Display.moveCursor(cursorPosition)
      }
      case None => ???
    }
  }
}
