package view.states

import sudoku.models.{
  CloseSudokuAction,
  EnterHelpNumberAction,
  EnterNumberAction,
  Game,
  SolveAction,
  Sudoku,
  Action => GameAction
}
import sudoku.solvers.BacktrackingSudokuSolver
import view.GameSetup
import view.display.Display
import view.display.Display.DisplayPosition
import view.printers.{InGameMenuPrinter, InGameSudokuPrinter}
import view.utils.{Action, Direction}
import view.utils.Direction.Direction

class InGameState(protected val gameSetup: GameSetup) extends State {
  var isHelpMode: Boolean               = false
  var isFullValidationActive: Boolean   = false
  var isSimpleValidationActive: Boolean = false

  def game: Game             = gameSetup.game
  def sudoku: Option[Sudoku] = gameSetup.game.sudoku

  override def print(): Unit = {
    sudoku.foreach(sudoku => {
      InGameSudokuPrinter.print(this, sudoku)
      InGameMenuPrinter.print(this, sudoku)
    })
  }

  override protected val stateActions: Seq[Action] = Seq(
    Action('s', "Solve puzzle", { solve }),
    Action('o', "Open puzzle", { openPuzzle }),
    Action('p', "Toggle help mode", { toggleHelpMode }),
    Action('v', "Toggle full validation", { toggleFullValidation }),
    Action('c', "Toggle simple validation", { toggleSimpleValidation }),
    Action('h', "Cursor left", { moveCursor(Direction.West) }),
    Action('j', "Cursor down", { moveCursor(Direction.South) }),
    Action('k', "Cursor up", { moveCursor(Direction.North) }),
    Action('l', "Cursor right", { moveCursor(Direction.East) }),
    Action('1', "Enter 1", { enterNumber(1) }, hidden = true),
    Action('2', "Enter 2", { enterNumber(2) }, hidden = true),
    Action('3', "Enter 3", { enterNumber(3) }, hidden = true),
    Action('4', "Enter 4", { enterNumber(4) }, hidden = true),
    Action('5', "Enter 5", { enterNumber(5) }, hidden = true),
    Action('6', "Enter 6", { enterNumber(6) }, hidden = true),
    Action('7', "Enter 7", { enterNumber(7) }, hidden = true),
    Action('8', "Enter 8", { enterNumber(8) }, hidden = true),
    Action('9', "Enter 9", { enterNumber(9) }, hidden = true)
  )

  private def moveCursor(direction: Direction)(): Option[GameAction] = {
    sudoku.flatMap(sudoku => {
      val relPos = direction match {
        case Direction.North => DisplayPosition(-2, 0)
        case Direction.East  => DisplayPosition(0, +4)
        case Direction.South => DisplayPosition(+2, 0)
        case Direction.West  => DisplayPosition(0, -4)
      }

      val newPosition       = Display.cursorPosition + relPos
      val maybeGamePosition = InGameSudokuPrinter.cursorPositionToGamePosition(sudoku.grid, newPosition)

      maybeGamePosition.foreach(_ => Display.moveCursor(newPosition))
      None
    })
  }

  private def toggleHelpMode(): Option[GameAction] = {
    this.isHelpMode = !this.isHelpMode
    None
  }

  private def toggleFullValidation(): Option[GameAction] = {
    this.isFullValidationActive = !this.isFullValidationActive
    None
  }
  private def toggleSimpleValidation(): Option[GameAction] = {
    this.isSimpleValidationActive = !this.isSimpleValidationActive
    None
  }

  private def enterNumber(number: Int)(): Option[GameAction] = {
    sudoku.flatMap(sudoku =>
      InGameSudokuPrinter
        .cursorPositionToGamePosition(sudoku.grid)
        .flatMap(pos =>
          if (this.isHelpMode)
            Some(EnterHelpNumberAction(number, pos))
          else
            Some(EnterNumberAction(number, pos))
        )
    )
  }

  private def solve(): Option[GameAction] = {
    Some(SolveAction(BacktrackingSudokuSolver))
  }

  private def openPuzzle(): Option[GameAction] = {
    Some(CloseSudokuAction())
  }
}
