package view.printers

import sudoku.models.{Action, StartSudokuAction}
import view.config.Config
import view.display.Display
import view.display.Display.{Color, DisplayPosition, TextStyle, createColorPair}

import java.io.File

object SudokuSelectorPrinter {
  private val selectorPosition = Config.selectorPosition
  private val listStart        = DisplayPosition(selectorPosition.y + 2, selectorPosition.x)
  private val listStyleColor   = createColorPair(Color.Yellow, Color.Black)

  def selectCurrent(): Option[Action] = {
    val action = getFilePathAtLocation(Display.cursorPosition).map(StartSudokuAction)
    if (action.isDefined)
      Display.moveCursor(Config.cursorStart)
    action
  }

  def getFilePathAtLocation(displayPosition: DisplayPosition): Option[String] = {
    val fileIndex = displayPosition.y - listStart.y
    if (fileIndex < 0)
      None
    else
      listOfFiles().drop(fileIndex).headOption
  }

  private def listOfFiles(): Seq[String] = {
    val directory = new File(System.getProperty("user.dir"))
    if (directory.exists && directory.isDirectory) {
      directory.listFiles.filter(_.isFile).map(_.getName)
    } else {
      Seq()
    }
  }

  def print(): Unit = {
    Display.moveCursor(selectorPosition)

    Display.setTextStyle(TextStyle.Bold)
    Display.print("Select a puzzle:")
    Display.removeTextStyle(TextStyle.Bold)

    Display.moveCursor(listStart)
    listOfFiles().foreach(printListItem)

  }

  private def printListItem(string: String): Unit = {
    val curPos = Display.cursorPosition
    Display.setColor(listStyleColor)
    Display.print("-")
    Display.removeColor(listStyleColor)
    Display.print(s" ${string}")
    Display.moveCursor(curPos.y + 1, curPos.x)
  }
}
