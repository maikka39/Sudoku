package sudoku.view

import sudoku.models.{SolveAction, Action => GameAction}
import sudoku.solvers.BacktrackingSudokuSolver
import sudoku.view.display.Display

object Actions {
  sealed case class Action private (keybind: Char, label: String, onCall: Unit => Option[GameAction])

  val actions: Seq[Action] = Seq(
    Action('q', "Quit", { _ => onQuit() }),
    Action('s', "Solve puzzle", { _ => onSolve() }),
    Action('o', "Open puzzle", { _ => ??? }),
    Action('r', "Redraw", { _ => None })
  )

  private def onQuit(): Option[GameAction] = {
    Display.quit()
    System.exit(0)
    None
  }

  private def onSolve(): Option[GameAction] = {
    Some(SolveAction(BacktrackingSudokuSolver))
  }
}
