package view

import sudoku.models.Game
import view.config.Config
import view.display.Display
import view.display.Display.DisplayPosition
import view.printers.{ErrorPrinter, InGameMenuPrinter, SudokuPrinter, SudokuSelectorPrinter}

class State {
  val game: Game                        = new Game()
  var isHelpMode: Boolean               = false
  var isFullValidationActive: Boolean   = false
  var isSimpleValidationActive: Boolean = false
}

object Main extends GameSetup with App {
  init()
  draw()
  while (true) { loop() }
}

trait GameSetup {
  val state: State = new State()

  def init(): Unit = {
    Display.init()
    Display.setEcho(false)
    Display.moveCursor(Config.cursorStart)
  }

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
    Display.clear()
    state.game.sudoku match {
      case Some(sudoku) => {
        SudokuPrinter.print(sudoku, state)
        InGameMenuPrinter.print(
          DisplayPosition(Config.sudokuPosition.y + 3, Config.sudokuPosition.x + sudoku.grid.length * 4 + 5)
        )
      }
      case None => {
        SudokuSelectorPrinter.print()
      }
    }
    Display.moveCursor(cursorPosition)
  }
}
