package sudoku.view

import sudoku.models.Game
import sudoku.parsers.SudokuParser
import sudoku.view.display.Display
import sudoku.view.display.Display.{Position, TextStyle}

object Main extends GameSetup with App {
//  init()
  run()
}

trait GameSetup {
  lazy val game: Game = new Game {
    sudoku = SudokuParser.parse("./src/test/resources/puzzles/puzzle.9x9").toOption
  }
  val gridSize = 3

  def run(): Unit = {
    Display.init()

    val sudokuRoot = Position(3, 5)
    Display.moveCursor(sudokuRoot)

    for (_ <- game.sudoku.get.grid.head) {
      Display.print("+---")
    }
    Display.print("+")

    for ((column, columnIndex) <- game.sudoku.get.grid.zipWithIndex) {
      Display.moveCursor(Position(sudokuRoot.x, sudokuRoot.y + (columnIndex * 2) + 1))
      Display.print("|")
      for (field <- column) {
        Display.print(" ")

        if (field.isPermanent)
          Display.addTextStyles(TextStyle.BOLD)

        Display.print(field.number.map(_.toString).getOrElse(" "))

        if (field.isPermanent)
          Display.setTextStyle(TextStyle.NORMAL)

        Display.print(" |")
      }

      Display.moveCursor(Position(sudokuRoot.x, sudokuRoot.y + (columnIndex * 2) + 2))
      for (_ <- game.sudoku.get.grid.head) {
        Display.print("+---")
      }
      Display.print("+")

    }

    Display.refresh()

    Display.getKeyPress

    Display.quit()
  }
}
