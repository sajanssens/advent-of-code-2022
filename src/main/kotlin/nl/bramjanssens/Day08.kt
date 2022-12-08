package nl.bramjanssens

fun main() {
    val lines = lines(8, InputType.Main)
    val field = Field(lines.size)
    for ((i, row) in lines.withIndex()) {
        field.addRow(i, row)
    }
    println(field.countHighTrees())
}

data class Field(val size: Int) {
    private val cells = Array(size) { IntArray(size) }

    fun addRow(i: Int, row: String) {
        cells[i] = row.split("").filter { it.isNotBlank() }.map { it.toInt() }.toIntArray()
    }

    private fun allNorthOf(r: Int, c: Int): List<Int> {
        val cells = mutableListOf<Int>()
        for (i in r - 1 downTo 0) cells.add(this.cells[i][c])
        return cells.toList()
    }

    private fun allSouthOf(r: Int, c: Int): List<Int> {
        val cells = mutableListOf<Int>()
        for (i in r + 1 until size) cells.add(this.cells[i][c])
        return cells.toList()
    }

    private fun allWestOf(r: Int, c: Int): List<Int> {
        val cells = mutableListOf<Int>()
        for (i in c - 1 downTo 0) cells.add(this.cells[r][i])
        return cells.toList()
    }

    private fun allEastOf(r: Int, c: Int): List<Int> {
        val cells = mutableListOf<Int>()
        for (i in c + 1 until size) cells.add(this.cells[r][i])
        return cells.toList()
    }

    fun countHighTrees(): Int {
        var high = (size - 1) * 4

        for (row in 1 until size - 1) {
            for (col in 1 until size - 1) {
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
