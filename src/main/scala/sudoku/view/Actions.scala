package sudoku.view

import sudoku.models.{CloseSudokuAction, SolveAction, Sudoku, Action => GameAction}
import sudoku.solvers.BacktrackingSudokuSolver
import sudoku.view.display.Display
import sudoku.view.display.Display.DisplayPosition
import sudoku.view.printers.SudokuPrinter
import sudoku.view.utils.Direction
import sudoku.view.utils.Direction.Direction

object Actions {
  sealed case class Action private (keybind: Char, label: String, onCall: Option[Sudoku] => Option[GameAction])

  val actions: Seq[Action] = Seq(
    Action('q', "Quit", { _ => onQuit() }),
    Action('s', "Solve puzzle", { _ => onSolve() }),
    Action('o', "Open puzzle", { _ => onOpenPuzzleFromGame() }),
    Action('r', "Redraw", { _ => None }),
    Action('h', "Cursor left", { s => moveCursor(s, Direction.West) }),
    Action('j', "Cursor down", { s => moveCursor(s, Direction.South) }),
    Action('k', "Cursor up", { s => moveCursor(s, Direction.North) }),
    Action('l', "Cursor right", { s => moveCursor(s, Direction.East) })
  )

  private def moveCursor(maybeSudoku: Option[Sudoku], direction: Direction): Option[GameAction] = {
    maybeSudoku match {
      case Some(sudoku) => moveCursorInGame(sudoku, direction)
      case None         => ???
    }
    None
  }

  private def moveCursorInGame(sudoku: Sudoku, direction: Direction): Unit = {
    val relPos = direction match {
      case Direction.North => DisplayPosition(-2, 0)
      case Direction.East  => DisplayPosition(0, +4)
      case Direction.South => DisplayPosition(+2, 0)
      case Direction.West  => DisplayPosition(0, -4)
    }

    val newPosition = Display.cursorPosition + relPos

    val maybeGamePosition = SudokuPrinter.cursorPositionToGamePosition(sudoku.grid, newPosition)

    maybeGamePosition.foreach(_ => Display.moveCursor(newPosition))
  }

  private def onQuit(): Option[GameAction] = {
    Display.quit()
    System.exit(0)
    None
  }

  private def onSolve(): Option[GameAction] = {
    Some(SolveAction(BacktrackingSudokuSolver))
  }

  private def onOpenPuzzleFromGame(): Option[GameAction] = {
    Some(CloseSudokuAction())
  }
}
