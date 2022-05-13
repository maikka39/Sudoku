package sudoku.models

import sudoku.models.Sudoku.{FieldGroup, Grid, SudokuField}

trait RegularRowsAndCols {
  val grid: Grid

  val rowsAndCols: Seq[FieldGroup] = {
    val rowColListList = for {
      n <- grid.indices
      row = grid.indices.map(x => Position(x, n))
      col = grid.indices.map(y => Position(n, y))
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
      .map(pos => grid(pos.x)(pos.y))
      .map(_.number.getOrElse(-1))
      .sorted
      .equals(List.range(1, fieldGroup.length + 1))
  }
}

object Sudoku {
  type FieldGroup = Seq[Position]
  type Grid       = Seq[Seq[SudokuField]]

  final case class SudokuField(
    number: Option[Int],
    helpNumbers: Seq[Int] = Seq(),
    isPermanent: Boolean = false,
    isActive: Boolean = true
  )
}

final case class RegularSudoku(grid: Grid) extends Sudoku with RegularRowsAndCols {
  override val fieldGroups: Seq[FieldGroup] = {
    val fieldSize = grid.length match {
      case 4 => (2, 2)
      case 6 => (3, 2)
      case 9 => (3, 3)
    }

    val coordinates = for {
      fieldStartX <- grid.indices by fieldSize._1
      fieldStartY <- grid.indices by fieldSize._2
      x           <- fieldStartX until fieldStartX + fieldSize._1
      y           <- fieldStartY until fieldStartY + fieldSize._2
    } yield (x, y)

    coordinates.map(pos => Position(pos._1, pos._2)).sliding(grid.length, grid.length).toSeq
  }
}

final case class JigsawSudoku(grid: Grid, fieldGroups: Seq[FieldGroup]) extends Sudoku with RegularRowsAndCols

final case class SamuraiSudoku(grid: Grid) extends Sudoku {
  override val fieldGroups: Seq[FieldGroup] = {
    val coordinates = for {
      fieldStartX <- grid.indices by 3
      fieldStartY <- grid.indices by 3
      if grid(fieldStartX)(fieldStartY).isActive
      x <- fieldStartX until fieldStartX + 3
      y <- fieldStartY until fieldStartY + 3
    } yield (x, y)

    coordinates
      .map(pos => Position(pos._1, pos._2))
      .sliding(9, 9)
      .toSeq
  }

  override val rowsAndCols: Seq[FieldGroup] = {
    val rowColListList = for {
      centerX <- 4 until 17 by 6
      centerY <- 4 until 17 by 6
      if grid(centerX)(centerY).isActive
      n <- -4 until 5
      row = (-4 until 5).map(x => Position(centerX + x, centerY + n))
      col = (-4 until 5).map(y => Position(centerX + n, centerY + y))
    } yield Seq(row, col)

    rowColListList.flatten
  }
}
