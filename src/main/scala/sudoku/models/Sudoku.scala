package sudoku.models

import sudoku.models.Sudoku.{FieldGroup, Grid}

trait RegularRowsAndCols {
  val grid: Grid

  val rowsAndCols: Seq[FieldGroup] = {
    val rowColListList = for {
      n <- grid.indices
      row = grid.indices.map(x => Position(n, x))
      col = grid.indices.map(y => Position(y, n))
    } yield Seq(row, col)

    rowColListList.flatten
  }
}

trait Sudoku {
  val grid: Grid
  val fieldGroups: Seq[FieldGroup]
  val rowsAndCols: Seq[FieldGroup]

  def isValid: Boolean = (fieldGroups ++ rowsAndCols).forall(isGroupValid)

  def copy(grid: Grid): Sudoku = {
    val grd = grid
    val fg  = fieldGroups
    val rc  = rowsAndCols
    new Sudoku {
      override val grid: Grid                   = grd
      override val fieldGroups: Seq[FieldGroup] = fg
      override val rowsAndCols: Seq[FieldGroup] = rc
    }
  }

  protected def isGroupValid(fieldGroup: FieldGroup): Boolean = {
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
  )
}

final class RegularSudoku(val grid: Grid) extends Sudoku with RegularRowsAndCols {
  override val fieldGroups: Seq[FieldGroup] = {
    val fieldSize = grid.length match {
      case 4 => (2, 2)
      case 6 => (2, 3)
      case 9 => (3, 3)
    }

    val coordinates = for {
      fieldStartY <- grid.indices by fieldSize._1
      fieldStartX <- grid.indices by fieldSize._2
      y           <- fieldStartY until fieldStartY + fieldSize._1
      x           <- fieldStartX until fieldStartX + fieldSize._2
    } yield (y, x)

    coordinates.map(pos => Position(pos._1, pos._2)).sliding(grid.length, grid.length).toSeq
  }
}

final class JigsawSudoku(val grid: Grid, val fieldGroups: Seq[FieldGroup]) extends Sudoku with RegularRowsAndCols

final class SamuraiSudoku(val grid: Grid) extends Sudoku {
  override val fieldGroups: Seq[FieldGroup] = {
    val coordinates = for {
      fieldStartY <- grid.indices by 3
      fieldStartX <- grid.indices by 3
      if grid(fieldStartY)(fieldStartX).isActive
      y <- fieldStartY until fieldStartY + 3
      x <- fieldStartX until fieldStartX + 3
    } yield (y, x)

    coordinates
      .map(pos => Position(pos._1, pos._2))
      .sliding(9, 9)
      .toSeq
  }

  override val rowsAndCols: Seq[FieldGroup] = {
    val rowColListList = for {
      centerY <- 4 until 17 by 6
      centerX <- 4 until 17 by 6
      if grid(centerY)(centerX).isActive
      n <- -4 until 5
      row = (-4 until 5).map(x => Position(centerY + n, centerX + x))
      col = (-4 until 5).map(y => Position(centerY + y, centerX + n))
    } yield Seq(row, col)

    rowColListList.flatten
  }
}
