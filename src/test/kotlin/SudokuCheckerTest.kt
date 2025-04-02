import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**

 * Comprehensive Tests *

  Tests for board dimensions
  Tests for invalid characters
  Tests for different board sizes
  Tests for all types of violations (row, column, box)
  Tests for empty and complete boards

 */

class SudokuCheckerTest {
    @Test
    fun `test empty board is valid`() {
        val board = Array(9) { Array(9) { '-' } }
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid board dimensions`() {
        val board = Array(8) { Array(9) { '-' } }  // 8x9 board
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid characters`() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = 'X'  // Invalid character
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test valid complete board`() {
        val board = arrayOf(
            arrayOf('5', '3', '4', '6', '7', '8', '9', '1', '2'),
            arrayOf('6', '7', '2', '1', '9', '5', '3', '4', '8'),
            arrayOf('1', '9', '8', '3', '4', '2', '5', '6', '7'),
            arrayOf('8', '5', '9', '7', '6', '1', '4', '2', '3'),
            arrayOf('4', '2', '6', '8', '5', '3', '7', '9', '1'),
            arrayOf('7', '1', '3', '9', '2', '4', '8', '5', '6'),
            arrayOf('9', '6', '1', '5', '3', '7', '2', '8', '4'),
            arrayOf('2', '8', '7', '4', '1', '9', '6', '3', '5'),
            arrayOf('3', '4', '5', '2', '8', '6', '1', '7', '9')
        )
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid row`() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[0][1] = '1'  // Duplicate in row
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid column`() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][0] = '1'  // Duplicate in column
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid box`() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][1] = '1'  // Duplicate in 3x3 box
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test valid 4x4 board`() {
        val board = arrayOf(
            arrayOf('1', '2', '3', '4'),
            arrayOf('3', '4', '1', '2'),
            arrayOf('2', '1', '4', '3'),
            arrayOf('4', '3', '2', '1')
        )
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }
}