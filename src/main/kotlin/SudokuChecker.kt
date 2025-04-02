import kotlin.math.sqrt

fun isSudokuValid(board: Array<Array<Char>>): Boolean {
    val size = board.size
    val boxSize = sqrt(size.toDouble()).toInt()

    // Check rows
    for (row in 0 until size) {
        val seen = mutableSetOf<Char>()
        for (col in 0 until size) {
            if (board[row][col] != '-') {
                if (!seen.add(board[row][col])) return false
            }
        }
    }

    // Check columns
    for (col in 0 until size) {
        val seen = mutableSetOf<Char>()
        for (row in 0 until size) {
            if (board[row][col] != '-') {
                if (!seen.add(board[row][col])) return false
            }
        }
    }

    // Check boxes
    for (boxRow in 0 until size step boxSize) {
        for (boxCol in 0 until size step boxSize) {
            val seen = mutableSetOf<Char>()
            for (row in boxRow until boxRow + boxSize) {
                for (col in boxCol until boxCol + boxSize) {
                    if (board[row][col] != '-') {
                        if (!seen.add(board[row][col])) return false
                    }
                }
            }
        }
    }
    return true
}