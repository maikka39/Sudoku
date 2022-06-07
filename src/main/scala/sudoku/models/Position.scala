package sudoku.models

case class Position(y: Int, x: Int) {
  def +(position: Position): Position = {
    Position(this.y + position.y, this.x + position.x)
  }
}
