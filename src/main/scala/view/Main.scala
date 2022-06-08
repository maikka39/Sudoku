package view

import view.config.Config
import view.display.Display
import view.printers.{ErrorPrinter, InGamePrinter, SudokuSelectorPrinter}

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
          case Some(sudokuError) => ErrorPrinter.print(sudokuError)
          case None              => draw()
        }
      )
  }

  def draw(): Unit = {
    val cursorPosition = Display.cursorPosition
    Display.clear()
    state.game.sudoku match {
      case Some(sudoku) => InGamePrinter.print(state, sudoku)
      case None         => SudokuSelectorPrinter.print()
    }
    Display.moveCursor(cursorPosition)
  }
}
