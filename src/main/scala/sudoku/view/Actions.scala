package sudoku.view

import sudoku.models.{Game, Action => GameAction}
import sudoku.view.display.Display

object Actions {
  protected final case class Action(keybind: Char, label: String, onCall: Unit => Option[GameAction])

  val actions: Seq[Action] = Seq(
    Action('q', "Quit", { _ => onQuit() }),
    Action('s', "Solve puzzle", { _ => ??? }),
    Action('o', "Open puzzle", { _ => ??? })
  )

  private def onQuit(): Option[GameAction] = {
    Display.quit()
    System.exit(0)
    None
  }
}
