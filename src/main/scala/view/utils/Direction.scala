package view.utils

import sudoku.models.Position
import view.display.Display.DisplayPosition

import scala.language.implicitConversions

object Direction extends Enumeration {
  type Direction = Value

  protected case class DirectionValue(relY: Int, relX: Int) extends super.Val {
    lazy val relativePosition: Position               = Position(relY, relX)
    lazy val relativeDisplayPosition: DisplayPosition = DisplayPosition(relY, relX)
  }

  implicit def valueToDirectionValue(x: Value): DirectionValue = x.asInstanceOf[DirectionValue]

  val North: Direction = DirectionValue(-1, 0)
  val East: Direction  = DirectionValue(0, +1)
  val South: Direction = DirectionValue(+1, 0)
  val West: Direction  = DirectionValue(0, -1)
}
