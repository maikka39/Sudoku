package view.states

import sudoku.models.{Action => GameAction}
import view.GameSetup
import view.display.Display
import view.display.Display.DisplayPosition
import view.printers.SudokuSelectorPrinter
import view.utils.Action

class NoGameState(protected val gameSetup: GameSetup) extends State {
  override protected val stateActions: Seq[Action] = Seq(
    Action('k', "Cursor up", { cursorUp }),
    Action('j', "Cursor down", { cursorDown }),
    Action(10.toChar, "Select", { select }, hidden = true)
  )

  def print(): Unit = SudokuSelectorPrinter.print()

  private def cursorUp(): Option[GameAction]   = moveCursorY(-1)
  private def cursorDown(): Option[GameAction] = moveCursorY(+1)

  private def moveCursorY(relY: Int): Option[GameAction] = {
    val newPos = Display.cursorPosition + DisplayPosition(relY, 0)
    SudokuSelectorPrinter.getFilePathAtLocation(newPos).foreach(_ => Display.moveCursor(newPos))
    None
  }

  private def select(): Option[GameAction] = {
    SudokuSelectorPrinter.selectCurrent()
  }
}
