import kotlin.math.sqrt

class SudokuChecker {
    // companion object to Makes the functions easily accessible without instantiation
    companion object {

        fun isSudokuValid(board: Array<Array<Char>>): Boolean {
            return hasValidDimensions(board) &&
                    hasValidCharacters(board) &&
                    hasValidRows(board) &&
                    hasValidColumns(board) &&
                    hasValidBoxes(board)
        }

        // hasValidDimensions: Ensures the board is a perfect square
        private fun hasValidDimensions(board: Array<Array<Char>>): Boolean {
            val size = board.size
            val boxSize = sqrt(size.toDouble()).toInt()

            // Check if size is a perfect square
            if (boxSize * boxSize != size) return false

            // Check if all rows have correct length
            return board.all { it.size == size }
        }

        // hasValidCharacters: Checks if only valid numbers and '-' are used
        private fun hasValidCharacters(board: Array<Array<Char>>): Boolean {
            val size = board.size
            val validChars = (('1'..('0' + size)).toSet() + '-')
            return board.all { row ->
                row.all { it in validChars }
            }
        }

        private fun hasValidRows(board: Array<Array<Char>>): Boolean {
            return board.all { row -> isValidGroup(row.toList()) }
        }

        private fun hasValidColumns(board: Array<Array<Char>>): Boolean {
            val size = board.size
            return (0 until size).all { col ->
                val column = board.map { it[col] }
                isValidGroup(column)
            }
        }

        private fun hasValidBoxes(board: Array<Array<Char>>): Boolean {
            val size = board.size
            val boxSize = sqrt(size.toDouble()).toInt()

            for (boxRow in 0 until size step boxSize) {
                for (boxCol in 0 until size step boxSize) {
                    val box = getBox(board, boxRow, boxCol, boxSize)
                    if (!isValidGroup(box)) return false
                }
            }
            return true
        }

        // getBox: Extracts a box from the board
        private fun getBox(
            board: Array<Array<Char>>,
            startRow: Int,
            startCol: Int,
            boxSize: Int
        ): List<Char> {
            val box = mutableListOf<Char>()
            for (row in startRow until startRow + boxSize) {
                for (col in startCol until startCol + boxSize) {
                    box.add(board[row][col])
                }
            }
            return box
        }

        // isValidGroup: Generic function for checking duplicates in any group
        private fun isValidGroup(group: List<Char>): Boolean {
            val seen = mutableSetOf<Char>()
            return group.all { char ->
                char == '-' || seen.add(char)
            }
        }
    }
}