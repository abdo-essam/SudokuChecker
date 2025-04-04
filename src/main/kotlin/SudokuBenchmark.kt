import kotlin.math.sqrt

object SudokuBenchmark {
    fun runBenchmark() {
        val sizes = listOf(4, 9, 16, 25)

        sizes.forEach { size ->
            val board = generateValidBoard(size)
            val startTime = System.nanoTime()
            repeat(100) {
                isSudokuValid(board)
            }
            val endTime = System.nanoTime()
            val averageTime = (endTime - startTime) / 100 / 1_000_000.0

            println("Average time for ${size}x${size} board: $averageTime ms")
        }
    }

    private fun generateValidBoard(size: Int): Array<Array<Char>> {
        return Array(size) { row ->
            Array(size) { col ->
                val num = ((row * 3 + row / sqrt(size.toDouble()).toInt() + col) % size + 1)
                (num + '0'.code).toChar()
            }
        }
    }
}