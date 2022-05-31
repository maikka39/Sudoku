package sudoku.view.display

import io.webfolder.curses4j.Curses
import sudoku.view.display.Display.Color.Color
import sudoku.view.display.Display.TextStyle.TextStyle

object Display {
  private var colorPairCounter = 0

  case class Position(x: Int, y: Int)
  protected case class ColorPair(id: Int)

  def init(): Unit = {
    Curses.initscr()

    if (Curses.has_colors()) {
      Curses.start_color()
    }
  }

  def refresh(): Unit                      = Curses.refresh()
  def clear(): Unit                        = Curses.clear()
  def moveCursor(position: Position): Unit = Curses.move(position.y, position.x)
  def print(string: String): Unit          = Curses.addstr(string)
  def insert(string: String): Unit         = Curses.insstr(string)
  def sleep(ms: Int): Unit                 = Curses.napms(ms)

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

  def getKeyPress: Int = Curses.getch()

  def quit(): Unit = Curses.endwin()

  object Color extends Enumeration {
    type Color = ColorValue

    protected case class ColorValue(value: Short) extends super.Val

    val BLACK: Color   = ColorValue(0)
    val RED: Color     = ColorValue(1)
    val GREEN: Color   = ColorValue(2)
    val YELLOW: Color  = ColorValue(3)
    val BLUE: Color    = ColorValue(4)
    val MAGENTA: Color = ColorValue(5)
    val CYAN: Color    = ColorValue(6)
    val WHITE: Color   = ColorValue(7)
  }

  object TextStyle extends Enumeration {
    type TextStyle = TextStyleValue

    protected case class TextStyleValue(value: Int) extends super.Val

    private val NCURSES_ATTR_SHIFT = 8

    private def NCURSES_BITS(mask: Int, shift: Int): Int = mask << (shift + NCURSES_ATTR_SHIFT)

    val NORMAL: TextStyle     = TextStyleValue(1 - 1)
    val STANDOUT: TextStyle   = TextStyleValue(NCURSES_BITS(1, 8))
    val UNDERLINE: TextStyle  = TextStyleValue(NCURSES_BITS(1, 9))
    val REVERSE: TextStyle    = TextStyleValue(NCURSES_BITS(1, 10))
    val BLINK: TextStyle      = TextStyleValue(NCURSES_BITS(1, 11))
    val DIM: TextStyle        = TextStyleValue(NCURSES_BITS(1, 12))
    val BOLD: TextStyle       = TextStyleValue(NCURSES_BITS(1, 13))
    val PROTECT: TextStyle    = TextStyleValue(NCURSES_BITS(1, 16))
    val INVIS: TextStyle      = TextStyleValue(NCURSES_BITS(1, 15))
    val ALTCHARSET: TextStyle = TextStyleValue(NCURSES_BITS(1, 14))
    val CHARTEXT: TextStyle   = TextStyleValue(NCURSES_BITS(1, 0) - 1)
  }
}
