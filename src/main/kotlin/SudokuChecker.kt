import kotlin.math.sqrt


// todo : I want to reduce the time complexity from O(3n²) to O(n²)
//  by combine row, column, and box checking into a single pass
// Row validation: O(n²) & Column validation: O(n²) & Box validation: O(n²) Total: O(n² + n² + n²) = O(3n²)

fun isSudokuValid(board: Array<Array<Char>>): Boolean {
    return hasValidDimensions(board) &&
            hasValidCharacters(board) &&
            hasValidRowsColumnsAndBoxes(board)
}

// Ensures the board is a perfect square
private fun hasValidDimensions(board: Array<Array<Char>>): Boolean {
    val size = board.size
    val boxSize = sqrt(size.toDouble()).toInt()

    // Check if size is a perfect square
    // Check if all rows have correct length
    return boxSize * boxSize == size && board.all { it.size == size }
}

// Checks if only valid numbers and '-' are used
private fun hasValidCharacters(board: Array<Array<Char>>): Boolean {
    val size = board.size
    // This ensures ('0' + size) is treated correctly as a Char
    val validChars = ('1'..('0' + size)).plus('-').toSet()
    return board.all { row ->
        row.all { it in validChars }
    }
}

private fun hasValidRowsColumnsAndBoxes(board: Array<Array<Char>>): Boolean {

    val size = board.size
    val boxSize = sqrt(size.toDouble()).toInt()

    // track numbers that have already appeared in the corresponding row, column, and sub-box.
    // A Set is an efficient data structure because it automatically handles duplicates.
    val rowSets = Array(size) { mutableSetOf<Char>() }
    val colSets = Array(size) { mutableSetOf<Char>() }
    val boxSets = Array(size) { mutableSetOf<Char>() }

    for (row in 0 until size) {
        for (col in 0 until size) {
            val cell = board[row][col]

            if (cell == '-') continue // Skip empty cells

            val boxIndex = (row / boxSize) * boxSize + (col / boxSize) // Calculate the box index

            // Check row, column, and box at the same time
            // O(1) lookup per cell,
            // trying to add it again will return false
            if (!rowSets[row].add(cell) || !colSets[col].add(cell) || !boxSets[boxIndex].add(cell)) {
                return false
            }
        }
    }
    return true
}