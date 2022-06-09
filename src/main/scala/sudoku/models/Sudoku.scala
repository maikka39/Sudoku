package sudoku.models

import sudoku.models.Sudoku.{FieldGroup, Grid}
import sudoku.solvers.SudokuSolver

final case class Sudoku(grid: Grid, fieldGroups: Seq[FieldGroup], rowsAndCols: Seq[FieldGroup]) {
  lazy val isValid: Boolean                                   = (fieldGroups ++ rowsAndCols).forall(isGroupValid)
  def solution(implicit solver: SudokuSolver): Option[Sudoku] = SolveAction(solver).execute(Some(this)).toOption.flatten

  private def isGroupValid(fieldGroup: FieldGroup): Boolean = {
    fieldGroup
      .map(pos => grid(pos.y)(pos.x))
      .map(_.number.getOrElse(-1))
      .sorted
      .equals(List.range(1, fieldGroup.length + 1))
  }

  def isFieldPossiblyValid(position: Position): Boolean = {
    val applicableGroups = (fieldGroups ++ rowsAndCols).filter(fg => fg.contains(position))
    val field            = grid(position.y)(position.x)

    applicableGroups.flatten.filter(pos => pos != position).count(p => grid(p.y)(p.x).number == field.number) < 1
  }
}

object Sudoku {
  type FieldGroup = Seq[Position]
  type Grid       = Seq[Row]
  type Row        = Seq[SudokuField]

  final case class SudokuField(
    number: Option[Int],
    helpNumbers: Seq[Int] = Seq(),
    isPermanent: Boolean = false,
    isActive: Boolean = true
  ) {
    val isEditable: Boolean = isActive && !isPermanent
  }
}
