package view

import sudoku.errors.SudokuError
import sudoku.models.Game
import view.display.Display
import view.printers.ErrorPrinter
import view.states.{InGameState, NoGameState, State}
import view.utils.Config

object Main extends GameSetup with App {
  init()
  draw()
  while (true) { loop() }
}

trait GameSetup {
  val game: Game           = new Game()
  private var state: State = new NoGameState(this)

  def init(): Unit = {
    Display.init()
    Display.setEcho(false)
    Display.moveCursor(Config.cursorStart)
  }

  def loop(): Unit = {
    onKeyPress(Display.getKeyPress) match {
      case Some(sudokuError) => ErrorPrinter.print(sudokuError)
      case None              => draw()
    }
  }

  def draw(): Unit = {
    val cursorPosition = Display.cursorPosition
    Display.clear()

    val stateChanged = changeState()

    state.print()

    Display.moveCursor(if (stateChanged) Config.cursorStart else cursorPosition)
  }

  def onKeyPress(key: Char): Option[SudokuError] = {
    state.actions
      .find(_.keybind == key)
      .flatMap(action =>
        action
          .onCall()
          .flatMap(game.executeAction)
      )
  }

  private var hadSudokuOnPreviousLoop = false
  private def changeState(): Boolean = {
    val changed = game.sudoku.isDefined != hadSudokuOnPreviousLoop
    if (changed)
      game.sudoku match {
        case Some(_) => state = new InGameState(this)
        case None    => state = new NoGameState(this)
      }
    hadSudokuOnPreviousLoop = game.sudoku.isDefined
    changed
  }
}
