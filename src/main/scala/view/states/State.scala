package view.states

import sudoku.models.{Action => GameAction}
import view.GameSetup
import view.display.Display
import view.utils.Action

trait State {
  def print(): Unit

  protected val gameSetup: GameSetup
  protected val stateActions: Seq[Action] = Seq()

  private val globalActions: Seq[Action] =
    Seq(Action('q', "Quit", { quit }), Action('r', "Redraw", { () => None }))

  final lazy val actions: Seq[Action] = {
    stateActions ++ globalActions
  }

  private def quit(): Option[GameAction] = {
    Display.quit()
    System.exit(0)
    None
  }
}
