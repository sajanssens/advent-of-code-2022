package nl.bramjanssens

fun main() {
    val lines = lines(8, InputType.Main)
    val grid = Grid(lines)
    println(grid.countVisibleTrees())
}

class Grid(lines: List<String>) {
    private val width = lines.size
    private val cells = Array(width) { IntArray(width) }

    init {
        lines.withIndex().forEach { addRow(it.index, it.value) }
    }

    private fun addRow(i: Int, row: String) {
        cells[i] = row.split("").filter { it.isNotBlank() }.map { it.toInt() }.toIntArray()
    }

    private fun allNorthOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in r - 1 downTo 0) add(cells[i][c])
        }

    private fun allSouthOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in r + 1 until width) add(cells[i][c])
        }.toList()

    private fun allWestOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in c - 1 downTo 0) add(cells[r][i])
        }.toList()

    private fun allEastOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in c + 1 until width) add(cells[r][i])
        }.toList()

    fun countVisibleTrees(): Int {
        var high = (width - 1) * 4

        for (row in 1 until width - 1) {
            for (col in 1 until width - 1) {
                val c = cells[row][col]

                val north = allNorthOf(row, col)
                val east = allEastOf(row, col)
                val south = allSouthOf(row, col)
                val west = allWestOf(row, col)

                if (north.all { it < c } || east.all { it < c } || south.all { it < c } || west.all { it < c }) high++
            }
        }
        return high
    }

}
