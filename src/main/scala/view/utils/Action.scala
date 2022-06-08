package view.utils

import sudoku.models.{Action => GameAction}

final case class Action private (
  keybind: Char,
  label: String,
  onCall: () => Option[GameAction],
  hidden: Boolean = false
)
