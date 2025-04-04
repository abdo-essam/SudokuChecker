fun main() {
    val testSuite = SudokuCheckerTest()
    testSuite.runAllTests()
}

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

    private var testsPassed = 0
    private var testsFailed = 0

    fun runAllTests() {
        println("Running SudokuChecker tests...\n")

        // Input Validation Tests
        testEmptyArrayBoard()
        testEmptyBoardIsValid()
        testInvalidBoardDimensions()

        // Character Validation Tests
        testInvalidCharacters()
        testBoardWithSpecialCharacters()

        // Complete Board Validation Tests
        testValidCompleteBoard()

        // Rule Violation Tests
        testInvalidRow()
        testInvalidColumn()
        testInvalidBox()

        // Multiple Violation Tests
        testViolationsInMultipleBoxes()

        // Different Board Size Tests
        testValid4x4Board()
        testValid1x1Board()

        // Edge Cases
        testEdgeCaseAllCellsFilledWithDashes()
        testEdgeCaseSingleViolationInLastCell()
        testEdgeCaseBoardWithAllSameNumbers()
        testPartiallyFilledValidBoard()

        println("\nTest results:")
        println("Passed: $testsPassed")
        println("Failed: $testsFailed")
    }

    private fun assertTrue(condition: Boolean, message: String) {
        if (condition) {
            println("PASS: $message")
            testsPassed++
        } else {
            println("FAIL: $message")
            testsFailed++
        }
    }

    private fun assertFalse(condition: Boolean, message: String) {
        assertTrue(!condition, message)
    }

    private fun assertThrows(block: () -> Unit, message: String) {
        try {
            block()
            println("FAIL: $message (Expected exception but none was thrown)")
            testsFailed++
        } catch (e: Exception) {
            println("PASS: $message")
            testsPassed++
        }
    }

    /**
     * Input Validation Tests
     */

    private fun testEmptyArrayBoard() {
        assertThrows(
            { SudokuChecker.isSudokuValid(arrayOf()) },
            "Empty array board should throw IllegalArgumentException"
        )
    }

    /**
     * Basic Board Structure Tests
     */

    private fun testEmptyBoardIsValid() {
        val board = Array(9) { Array(9) { '-' } }
        assertTrue(
            SudokuChecker.isSudokuValid(board),
            "Empty board (all dashes) should be valid"
        )
    }

    private fun testInvalidBoardDimensions() {
        val board = Array(8) { Array(9) { '-' } }  // 8x9 board
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Non-square board should be invalid"
        )
    }

    /**
     * Character Validation Tests
     */

    private fun testInvalidCharacters() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = 'X'  // Invalid character
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Board with invalid characters should be invalid"
        )
    }

    private fun testBoardWithSpecialCharacters() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '@'
        board[0][1] = '#'
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Board with special characters should be invalid"
        )
    }
    /**
     * Complete Board Validation Tests
     */

    private fun testValidCompleteBoard() {
        assertTrue(
            SudokuChecker.isSudokuValid(validBoard9x9),
            "Valid complete board should pass"
        )
    }

    /**
     * Rule Violation Tests
     */

    private fun testInvalidRow() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[0][1] = '1'  // Duplicate in row
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Board with duplicate in row should be invalid"
        )
    }

    private fun testInvalidColumn() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][0] = '1'  // Duplicate in column
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Board with duplicate in column should be invalid"
        )
    }

    private fun testInvalidBox() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][1] = '1'  // Duplicate in 3x3 box
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Board with duplicate in box should be invalid"
        )
    }

    /**
     * Multiple Violation Tests
     */

    private fun testViolationsInMultipleBoxes() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '1'
        board[1][1] = '1'  // First box violation
        board[0][3] = '2'
        board[1][4] = '2'  // Second box violation
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Board with violations in multiple boxes should be invalid"
        )
    }

    /**
     * Different Board Size Tests
     */

    private fun testValid4x4Board() {
        val board = arrayOf(
            arrayOf('1', '2', '3', '4'),
            arrayOf('3', '4', '1', '2'),
            arrayOf('2', '1', '4', '3'),
            arrayOf('4', '3', '2', '1')
        )
        assertTrue(
            SudokuChecker.isSudokuValid(board),
            "Valid 4x4 board should pass"
        )
    }

    private fun testValid1x1Board() {
        val board = arrayOf(arrayOf('1'))
        assertTrue(
            SudokuChecker.isSudokuValid(board),
            "Valid 1x1 board should pass"
        )
    }

    /**
     * Edge Cases
     */

    private fun testEdgeCaseAllCellsFilledWithDashes() {
        val board = Array(9) { Array(9) { '-' } }
        assertTrue(
            SudokuChecker.isSudokuValid(board),
            "Board with all dashes should be valid"
        )
    }

    private fun testEdgeCaseSingleViolationInLastCell() {
        val validBoard = validBoard9x9.map { it.clone() }.toTypedArray()
        validBoard[8][8] = validBoard[8][7]
        assertFalse(
            SudokuChecker.isSudokuValid(validBoard),
            "Board with violation in last cell should be invalid"
        )
    }

    private fun testEdgeCaseBoardWithAllSameNumbers() {
        val board = Array(9) { Array(9) { '1' } }
        assertFalse(
            SudokuChecker.isSudokuValid(board),
            "Board with all same numbers should be invalid"
        )
    }

    private fun testPartiallyFilledValidBoard() {
        val board = Array(9) { Array(9) { '-' } }
        board[0][0] = '5'
        board[0][1] = '3'
        board[1][0] = '6'
        assertTrue(
            SudokuChecker.isSudokuValid(board),
            "Partially filled valid board should be valid"
        )
    }
}