import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SudokuCheckerTest {
    @Test
    fun `test empty board is valid`() {
        val board = Array(9) { Array(9) { '-' } }
        assertEquals(true, isSudokuValid(board))
    }

    @Test
    fun `test valid complete board`() {
        val board = arrayOf(
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
        assertEquals(true, isSudokuValid(board))
    }

    @Test
    fun `test invalid row`() {
        val board = arrayOf(
            arrayOf('5','5','4','6','7','8','9','1','2'),  // duplicate 5 in row
            arrayOf('6','7','2','1','9','5','3','4','8'),
            arrayOf('1','9','8','3','4','2','5','6','7'),
            arrayOf('8','5','9','7','6','1','4','2','3'),
            arrayOf('4','2','6','8','5','3','7','9','1'),
            arrayOf('7','1','3','9','2','4','8','5','6'),
            arrayOf('9','6','1','5','3','7','2','8','4'),
            arrayOf('2','8','7','4','1','9','6','3','5'),
            arrayOf('3','4','5','2','8','6','1','7','9')
        )
        assertEquals(false, isSudokuValid(board))
    }

    @Test
    fun `test invalid column`() {
        val board = arrayOf(
            arrayOf('5','3','4','6','7','8','9','1','2'),
            arrayOf('5','7','2','1','9','5','3','4','8'),  // duplicate 5 in column
            arrayOf('1','9','8','3','4','2','5','6','7'),
            arrayOf('8','5','9','7','6','1','4','2','3'),
            arrayOf('4','2','6','8','5','3','7','9','1'),
            arrayOf('7','1','3','9','2','4','8','5','6'),
            arrayOf('9','6','1','5','3','7','2','8','4'),
            arrayOf('2','8','7','4','1','9','6','3','5'),
            arrayOf('3','4','5','2','8','6','1','7','9')
        )
        assertEquals(false, isSudokuValid(board))
    }

    @Test
    fun `test invalid box`() {
        val board = arrayOf(
            arrayOf('5','3','4','6','7','8','9','1','2'),
            arrayOf('6','5','2','1','9','5','3','4','8'),  // duplicate 5 in 3x3 box
            arrayOf('1','9','8','3','4','2','5','6','7'),
            arrayOf('8','5','9','7','6','1','4','2','3'),
            arrayOf('4','2','6','8','5','3','7','9','1'),
            arrayOf('7','1','3','9','2','4','8','5','6'),
            arrayOf('9','6','1','5','3','7','2','8','4'),
            arrayOf('2','8','7','4','1','9','6','3','5'),
            arrayOf('3','4','5','2','8','6','1','7','9')
        )
        assertEquals(false, isSudokuValid(board))
    }
}