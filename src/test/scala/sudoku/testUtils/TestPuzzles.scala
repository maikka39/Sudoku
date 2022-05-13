package sudoku.testUtils

import sudoku.models.Sudoku.SudokuField
import sudoku.models.{JigsawSudoku, Position, RegularSudoku, SamuraiSudoku}

object TestPuzzles {
  val regularSudoku4x4: RegularSudoku = RegularSudoku(
    Seq(
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      )
    )
  )

  val regularSudoku6x6: RegularSudoku = RegularSudoku(
    Seq(
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(Some(4), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq())
      )
    )
  )

  val regularSudoku9x9: RegularSudoku = RegularSudoku(
    Seq(
      Seq(
        SudokuField(Some(7), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(9), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(9), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(2), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(7), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(5), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(9), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true)
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(7), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq())
      ),
      Seq(
        SudokuField(Some(1), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(3), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(None, Seq()),
        SudokuField(Some(6), Seq(), isPermanent = true),
        SudokuField(None, Seq()),
        SudokuField(Some(4), Seq(), isPermanent = true)
      )
    )
  )

  val jigsawSudoku: JigsawSudoku = JigsawSudoku(
    List(
      List(
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List())
      ),
      List(
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List()),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List())
      )
    ),
    List(
      List(
        Position(0, 0),
        Position(1, 0),
        Position(1, 1),
        Position(1, 2),
        Position(1, 3),
        Position(1, 4),
        Position(1, 5),
        Position(1, 6),
        Position(2, 0)
      ),
      List(
        Position(2, 1),
        Position(2, 2),
        Position(2, 3),
        Position(2, 4),
        Position(3, 0),
        Position(3, 1),
        Position(3, 2),
        Position(3, 3),
        Position(3, 4)
      ),
      List(
        Position(4, 0),
        Position(4, 1),
        Position(4, 2),
        Position(5, 0),
        Position(5, 1),
        Position(5, 2),
        Position(6, 0),
        Position(7, 0),
        Position(8, 0)
      ),
      List(
        Position(0, 1),
        Position(0, 2),
        Position(0, 3),
        Position(0, 4),
        Position(0, 5),
        Position(0, 6),
        Position(0, 7),
        Position(1, 7),
        Position(2, 7)
      ),
      List(
        Position(6, 1),
        Position(7, 1),
        Position(8, 1),
        Position(8, 2),
        Position(8, 3),
        Position(8, 4),
        Position(8, 5),
        Position(8, 6),
        Position(8, 7)
      ),
      List(
        Position(2, 5),
        Position(2, 6),
        Position(3, 5),
        Position(4, 3),
        Position(4, 4),
        Position(4, 5),
        Position(5, 3),
        Position(6, 2),
        Position(6, 3)
      ),
      List(
        Position(6, 8),
        Position(7, 2),
        Position(7, 3),
        Position(7, 4),
        Position(7, 5),
        Position(7, 6),
        Position(7, 7),
        Position(7, 8),
        Position(8, 8)
      ),
      List(
        Position(5, 4),
        Position(5, 5),
        Position(5, 6),
        Position(5, 7),
        Position(5, 8),
        Position(6, 4),
        Position(6, 5),
        Position(6, 6),
        Position(6, 7)
      ),
      List(
        Position(0, 8),
        Position(1, 8),
        Position(2, 8),
        Position(3, 6),
        Position(3, 7),
        Position(3, 8),
        Position(4, 6),
        Position(4, 7),
        Position(4, 8)
      )
    )
  )

  val samuraiSudoku: SamuraiSudoku = SamuraiSudoku(
    List(
      List(
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false)
      ),
      List(
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false)
      ),
      List(
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false)
      ),
      List(
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List())
      ),
      List(
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(Some(2), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(None, List())
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(3), List(), isPermanent = true),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List())
      ),
      List(
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(8), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(9), List(), isPermanent = true),
        SudokuField(Some(4), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(1), List(), isPermanent = true)
      ),
      List(
        SudokuField(None, List()),
        SudokuField(Some(6), List(), isPermanent = true),
        SudokuField(Some(5), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(Some(2), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List(), isActive = false),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(7), List(), isPermanent = true),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(None, List()),
        SudokuField(Some(4), List(), isPermanent = true)
      )
    )
  )
}