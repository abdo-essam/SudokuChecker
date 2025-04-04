import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * Comprehensive Test Suite for Sudoku Validator
 *
 * This test suite validates the SudokuChecker implementation across various scenarios:
 * - Board dimensions and structure validation
 * - Character validation (valid numbers, invalid characters)
 * - Rule violations (row, column, box)
 * - Edge cases and special scenarios
 * - Different board sizes (1x1, 4x4, 9x9)
 */
class SudokuCheckerTest {
    // Valid 9x9 Sudoku board used as a reference for testing
    private val validBoard9x9 = arrayOf(
        arrayOf('5','3','4','6','7','8','9','1','2'),
        arrayOf('6','7','2','1','9','5','3','4','8'),
        arrayOf('1','9','8','3','4','2','5','6','7'),
        arrayOf('8','5','9','7','6','1','4','2','3'),
        arrayOf('4','2','6','8','5','3','7','9','1'),
        arrayOf('7','1','3','9','2','4','8','5','6'),
        arrayOf('9','6','1','5','3','7','2','8','4'),
        arrayOf('2','8','7','4','1','9','6','3','5'),
        arrayOf('3','4','5','2','8','6','1','7','9')
    )

    /**
     * Input Validation Tests
     */

    @Test
    fun `test empty array board`() {
        // Should throw exception when empty array is provided
        assertFailsWith<IllegalArgumentException> {
            SudokuChecker.isSudokuValid(arrayOf())
        }
    }

    /**
     * Basic Board Structure Tests
     */

    @Test
    fun `test empty board is valid`() {
        // An empty board (all dashes) should be considered valid
        val board = Array(9) { Array(9) { '-' } }
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid board dimensions`() {
        // Board must be square and have valid dimensions for Sudoku
        val board = Array(8) { Array(9) { '-' } }  // 8x9 board
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    /**
     * Character Validation Tests
     */

    @Test
    fun `test invalid characters`() {
        // Only numbers 1-9 and dash (-) are valid characters
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = 'X'  // Invalid character
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test board with special characters`() {
        // Special characters should be rejected
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '@'
        board[0][1] = '#'
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test board with lowercase letters`() {
        // Lowercase letters should be rejected
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = 'a'
        board[0][1] = 'b'
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    /**
     * Complete Board Validation Tests
     */

    @Test
    fun `test valid complete board`() {
        // A completely filled valid board should pass
        assertEquals(true, SudokuChecker.isSudokuValid(validBoard9x9))
    }

    /**
     * Rule Violation Tests
     */

    @Test
    fun `test invalid row`() {
        // Duplicate numbers in the same row should fail
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[0][1] = '1'  // Duplicate in row
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid column`() {
        // Duplicate numbers in the same column should fail
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][0] = '1'  // Duplicate in column
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test invalid box`() {
        // Duplicate numbers in the same 3x3 box should fail
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][1] = '1'  // Duplicate in 3x3 box
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    /**
     * Multiple Violation Tests
     */

    @Test
    fun `test multiple violations in same row`() {
        // Multiple duplicates in the same row
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[0][1] = '1'
        board[0][2] = '1'
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test multiple violations in same column`() {
        // Multiple duplicates in the same column
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][0] = '1'
        board[2][0] = '1'
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test violations in multiple boxes`() {
        // Violations in multiple 3x3 boxes
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][1] = '1'  // First box violation
        board[0][3] = '2'
        board[1][4] = '2'  // Second box violation
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    /**
     * Different Board Size Tests
     */

    @Test
    fun `test valid 4x4 board`() {
        // 4x4 is a valid Sudoku size
        val board = arrayOf(
            arrayOf('1', '2', '3', '4'),
            arrayOf('3', '4', '1', '2'),
            arrayOf('2', '1', '4', '3'),
            arrayOf('4', '3', '2', '1')
        )
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test valid 1x1 board`() {
        // 1x1 is technically a valid Sudoku board
        val board = arrayOf(arrayOf('1'))
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }

    /**
     * Edge Cases
     */

    @Test
    fun `test edge case - all cells filled with dashes`() {
        // Empty board should be valid
        val board = Array(9) { Array(9) { '-' } }
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test edge case - single violation in last cell`() {
        // Violation in the last cell should be detected
        val validBoard = validBoard9x9.map { it.clone() }.toTypedArray()
        validBoard[8][8] = validBoard[8][7]
        assertEquals(false, SudokuChecker.isSudokuValid(validBoard))
    }

    @Test
    fun `test edge case - board with all same numbers`() {
        // Board filled with same number should be invalid
        val board = Array(9) { Array(9) { '1' } }
        assertEquals(false, SudokuChecker.isSudokuValid(board))
    }

    @Test
    fun `test partially filled valid board`() {
        // Partially filled board with no violations should be valid
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '5'
        board[0][1] = '3'
        board[1][0] = '6'
        assertEquals(true, SudokuChecker.isSudokuValid(board))
    }
/*    @Test
    @Disabled
    fun `test performance with large board`() {
        val size = 16
        val largeBoard = Array(size) { row ->
            Array(size) { col ->
                val num = ((row * 3 + row / 4 + col) % size + 1)
                (num + '0'.code).toChar()
            }
        }

        val startTime = System.nanoTime()
        val result = SudokuChecker.isSudokuValid(largeBoard)
        val endTime = System.nanoTime()

        println("Time taken for ${size}x${size} board: ${(endTime - startTime) / 1_000_000.0} ms")
        assertTrue(result)
    }*/

/*    @Test
    fun `test performance with multiple iterations`() {
        val iterations = 1000
        val startTime = System.nanoTime()

        repeat(iterations) {
            SudokuChecker.isSudokuValid(validBoard9x9)
        }

        val endTime = System.nanoTime()
        val averageTime = (endTime - startTime) / iterations / 1_000_000.0

        println("Average time per validation: $averageTime ms")
    }*/
}