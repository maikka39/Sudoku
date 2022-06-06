package sudoku.view.display

import io.webfolder.curses4j.Curses
import sudoku.view.display.Display.Color.Color
import sudoku.view.display.Display.TextStyle.TextStyle

object Display {
  private var colorPairCounter = 0

  case class Position(y: Int, x: Int)
  protected case class ColorPair(id: Int)

  def init(): Unit = {
    Curses.initscr()

    if (Curses.has_colors()) {
      Curses.start_color()
    }
  }

  def refresh(): Unit                      = Curses.refresh()
  def clear(): Unit                        = Curses.clear()
  def moveCursor(position: Position): Unit = moveCursor(position.y, position.x)
  def moveCursor(y: Int, x: Int): Unit     = Curses.move(y, x)
  def print(string: String): Unit          = Curses.addstr(string)
  def insert(string: String): Unit         = Curses.insstr(string)
  def sleep(ms: Int): Unit                 = Curses.napms(ms)
  def setEcho(shouldEcho: Boolean): Unit   = if (shouldEcho) Curses.echo() else Curses.noecho()
  def cursorPosition: Position             = Position(Curses.getcury(), Curses.getcurx())

  def addTextStyle(textStyle: TextStyle): Unit    = Curses.attron(textStyle.value)
  def addTextStyles(textStyles: TextStyle*): Unit = textStyles.foreach(addTextStyle)

  def setTextStyle(textStyle: TextStyle): Unit = Curses.attrset(textStyle.value)
  def setTextStyles(textStyles: TextStyle*): Unit = {
    textStyles.headOption.foreach(setTextStyle)
    textStyles.drop(1).foreach(addTextStyle)
  }

  def removeTextStyle(textStyle: TextStyle): Unit    = Curses.attroff(textStyle.value)
  def removeTextStyles(textStyles: TextStyle*): Unit = textStyles.foreach(removeTextStyle)

  def createColorPair(foregroundColor: Color, backgroundColor: Color): ColorPair = {
    colorPairCounter += 1
    Curses.init_pair(colorPairCounter, foregroundColor.value, backgroundColor.value)
    ColorPair(colorPairCounter)
  }

  def setColor(colorPair: ColorPair): Unit    = Curses.attron(Curses.COLOR_PAIR(colorPair.id))
  def removeColor(colorPair: ColorPair): Unit = Curses.attroff(Curses.COLOR_PAIR(colorPair.id))

  def getKeyPress: Char = Curses.getch().toChar

  def quit(): Unit = Curses.endwin()

  object Color extends Enumeration {
    type Color = ColorValue

    protected case class ColorValue(value: Short) extends super.Val

    val Black: Color   = ColorValue(0)
    val Red: Color     = ColorValue(1)
    val Green: Color   = ColorValue(2)
    val Yellow: Color  = ColorValue(3)
    val Blue: Color    = ColorValue(4)
    val Magenta: Color = ColorValue(5)
    val Cyan: Color    = ColorValue(6)
    val White: Color   = ColorValue(7)

    val Grey: Color = ColorValue(8)
  }

  object TextStyle extends Enumeration {
    type TextStyle = TextStyleValue

    protected case class TextStyleValue(value: Int) extends super.Val

    private val NCURSES_ATTR_SHIFT = 8

    private def NCURSES_BITS(mask: Int, shift: Int): Int = mask << (shift + NCURSES_ATTR_SHIFT)

    val Normal: TextStyle     = TextStyleValue(1 - 1)
    val Standout: TextStyle   = TextStyleValue(NCURSES_BITS(1, 8))
    val Underline: TextStyle  = TextStyleValue(NCURSES_BITS(1, 9))
    val Reverse: TextStyle    = TextStyleValue(NCURSES_BITS(1, 10))
    val Blink: TextStyle      = TextStyleValue(NCURSES_BITS(1, 11))
    val Dim: TextStyle        = TextStyleValue(NCURSES_BITS(1, 12))
    val Bold: TextStyle       = TextStyleValue(NCURSES_BITS(1, 13))
    val Protect: TextStyle    = TextStyleValue(NCURSES_BITS(1, 16))
    val Invisible: TextStyle  = TextStyleValue(NCURSES_BITS(1, 15))
    val AltCharSet: TextStyle = TextStyleValue(NCURSES_BITS(1, 14))
    val CharText: TextStyle   = TextStyleValue(NCURSES_BITS(1, 0) - 1)
  }
}
