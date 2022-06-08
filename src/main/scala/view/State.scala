package view

import sudoku.models.Game

class State {
  val game: Game                        = new Game()
  var isHelpMode: Boolean               = false
  var isFullValidationActive: Boolean   = false
  var isSimpleValidationActive: Boolean = false
}
