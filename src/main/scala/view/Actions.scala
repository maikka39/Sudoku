package view

import sudoku.models.{
  CloseSudokuAction,
  EnterHelpNumberAction,
  EnterNumberAction,
  SolveAction,
  Sudoku,
  Action => GameAction
}
import sudoku.solvers.BacktrackingSudokuSolver
import view.config.Config
import view.display.Display
import view.display.Display.DisplayPosition
import view.printers.{InGameSudokuPrinter, SudokuSelectorPrinter}
import view.utils.Direction
import view.utils.Direction.Direction

object Actions {
  final case class Action private (
    keybind: Char,
    label: String,
    onCall: State => Option[GameAction],
    hidden: Boolean = false
  )

  val actions: Seq[Action] = Seq(
    Action('q', "Quit", { _ => onQuit() }),
    Action('s', "Solve puzzle", { onSolve }),
    Action('o', "Open puzzle", { onOpenPuzzleFromGame }),
    Action('p', "Toggle help mode", { toggleHelpMode }),
    Action('v', "Toggle full validation", { toggleFullValidation }),
    Action('c', "Toggle simple validation", { toggleSimpleValidation }),
    Action('r', "Redraw", { _ => None }),
    Action('h', "Cursor left", { s => moveCursor(s, Direction.West) }),
    Action('j', "Cursor down", { s => moveCursor(s, Direction.South) }),
    Action('k', "Cursor up", { s => moveCursor(s, Direction.North) }),
    Action('l', "Cursor right", { s => moveCursor(s, Direction.East) }),
    Action('1', "Enter 1", { s => enterNumber(s, 1) }, hidden = true),
    Action('2', "Enter 2", { s => enterNumber(s, 2) }, hidden = true),
    Action('3', "Enter 3", { s => enterNumber(s, 3) }, hidden = true),
    Action('4', "Enter 4", { s => enterNumber(s, 4) }, hidden = true),
    Action('5', "Enter 5", { s => enterNumber(s, 5) }, hidden = true),
    Action('6', "Enter 6", { s => enterNumber(s, 6) }, hidden = true),
    Action('7', "Enter 7", { s => enterNumber(s, 7) }, hidden = true),
    Action('8', "Enter 8", { s => enterNumber(s, 8) }, hidden = true),
    Action('9', "Enter 9", { s => enterNumber(s, 9) }, hidden = true),
    Action(10.toChar, "Select", { select }, hidden = true)
  )

  private def moveCursor(state: State, direction: Direction): Option[GameAction] = {
    state.game.sudoku match {
      case Some(sudoku) => moveCursorInGame(sudoku, direction)
      case None         => moveCursorInSelectMenu(direction)
    }
    None
  }

  private def select(state: State): Option[GameAction] = {
    if (state.game.sudoku.isDefined)
      None
    else
      SudokuSelectorPrinter.selectCurrent()
  }

  private def moveCursorInGame(sudoku: Sudoku, direction: Direction): Unit = {
    val relPos = direction match {
      case Direction.North => DisplayPosition(-2, 0)
      case Direction.East  => DisplayPosition(0, +4)
      case Direction.South => DisplayPosition(+2, 0)
      case Direction.West  => DisplayPosition(0, -4)
    }

    val newPosition       = Display.cursorPosition + relPos
    val maybeGamePosition = InGameSudokuPrinter.cursorPositionToGamePosition(sudoku.grid, newPosition)

    maybeGamePosition.foreach(_ => Display.moveCursor(newPosition))
  }

  private def moveCursorInSelectMenu(direction: Direction): Unit = {
    val relPos = direction match {
      case Direction.North => DisplayPosition(-1, 0)
      case Direction.South => DisplayPosition(+1, 0)
      case _               => DisplayPosition(0, 0)
    }

    val newPos = Display.cursorPosition + relPos

    SudokuSelectorPrinter.getFilePathAtLocation(newPos).foreach(_ => Display.moveCursor(newPos))
  }

  private def onQuit(): Option[GameAction] = {
    Display.quit()
    System.exit(0)
    None
  }

  private def toggleHelpMode(state: State): Option[GameAction] = {
    state.isHelpMode = !state.isHelpMode
    None
  }

  private def toggleFullValidation(state: State): Option[GameAction] = {
    state.isFullValidationActive = !state.isFullValidationActive
    None
  }
  private def toggleSimpleValidation(state: State): Option[GameAction] = {
    state.isSimpleValidationActive = !state.isSimpleValidationActive
    None
  }

  private def enterNumber(state: State, number: Int): Option[GameAction] = {
    state.game.sudoku.flatMap(sudoku =>
      InGameSudokuPrinter
        .cursorPositionToGamePosition(sudoku.grid)
        .flatMap(pos =>
          if (state.isHelpMode)
            Some(EnterHelpNumberAction(number, pos))
          else
            Some(EnterNumberAction(number, pos))
        )
    )
  }

  private def onSolve(state: State): Option[GameAction] = {
    state.game.sudoku.flatMap(_ => Some(SolveAction(BacktrackingSudokuSolver)))
  }

  private def onOpenPuzzleFromGame(state: State): Option[GameAction] = {
    state.game.sudoku.map(_ => {
      Display.moveCursor(Config.cursorStart)
      CloseSudokuAction()
    })
  }
}
