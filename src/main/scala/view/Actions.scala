package view

import sudoku.models.{CloseSudokuAction, Game, SolveAction, Sudoku, Action => GameAction}
import sudoku.solvers.BacktrackingSudokuSolver
import view.display.Display.DisplayPosition
import view.utils.Direction.Direction
import view.display.Display
import view.printers.SudokuPrinter
import view.utils.Direction

object Actions {
  sealed case class Action private (
    keybind: Char,
    label: String,
    onCall: Game => Option[GameAction],
    hidden: Boolean = false
  )

  val actions: Seq[Action] = Seq(
    Action('q', "Quit", { _ => onQuit() }),
    Action('s', "Solve puzzle", { g => onSolve(g) }),
    Action('o', "Open puzzle", { g => onOpenPuzzleFromGame(g) }),
    Action('r', "Redraw", { _ => None }),
    Action('h', "Cursor left", { g => moveCursor(g, Direction.West) }),
    Action('j', "Cursor down", { g => moveCursor(g, Direction.South) }),
    Action('k', "Cursor up", { g => moveCursor(g, Direction.North) }),
    Action('l', "Cursor right", { g => moveCursor(g, Direction.East) }),
    Action('1', "Enter 1", { g => enterNumber(g, 1) }, hidden = true),
    Action('2', "Enter 2", { g => enterNumber(g, 2) }, hidden = true),
    Action('3', "Enter 3", { g => enterNumber(g, 3) }, hidden = true),
    Action('4', "Enter 4", { g => enterNumber(g, 4) }, hidden = true),
    Action('5', "Enter 5", { g => enterNumber(g, 5) }, hidden = true),
    Action('6', "Enter 6", { g => enterNumber(g, 6) }, hidden = true),
    Action('7', "Enter 7", { g => enterNumber(g, 7) }, hidden = true),
    Action('8', "Enter 8", { g => enterNumber(g, 8) }, hidden = true),
    Action('9', "Enter 9", { g => enterNumber(g, 9) }, hidden = true)
  )

  private def moveCursor(game: Game, direction: Direction): Option[GameAction] = {
    game.sudoku match {
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

  private def enterNumber(game: Game, number: Int): Option[GameAction] = {
//    game.sudoku.flatMap(_ => )
    None
  }

  private def onSolve(game: Game): Option[GameAction] = {
    game.sudoku.flatMap(_ => Some(SolveAction(BacktrackingSudokuSolver)))
  }

  private def onOpenPuzzleFromGame(game: Game): Option[GameAction] = {
    game.sudoku.map(_ => CloseSudokuAction())
  }
}
