package nl.bramjanssens

import nl.bramjanssens.InputType.Main

fun main() {
    val grid = Grid(lines(8, Main))
    println("part 1: ${grid.countVisibleTrees()}")
    println("part 2: ${grid.scenicScores().max()}")

}

class Grid(lines: List<String>) {
    private val width = lines.size
    private val trees = Array(width) { IntArray(width) }

    init {
        lines.withIndex().forEach { addRow(it.index, it.value) }
    }

    private fun addRow(i: Int, row: String) {
        trees[i] = row.split("").filter { it.isNotBlank() }.map { it.toInt() }.toIntArray()
    }

    private fun northOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in r - 1 downTo 0) add(trees[i][c])
        }.toList()

    private fun southOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in r + 1 until width) add(trees[i][c])
        }.toList()

    private fun westOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in c - 1 downTo 0) add(trees[r][i])
        }.toList()

    private fun eastOf(r: Int, c: Int) =
        mutableListOf<Int>().apply {
            for (i in c + 1 until width) add(trees[r][i])
        }.toList()

    fun countVisibleTrees(): Int {
        var count = (width - 1) * 4

        for (row in 1 until width - 1) {
            for (col in 1 until width - 1) {
                val c = trees[row][col]

                if (northOf(row, col).all { it < c } ||
                    eastOf(row, col).all { it < c } ||
                    southOf(row, col).all { it < c } ||
                    westOf(row, col).all { it < c }) {
                    count++
                }
            }
        }

        return count
    }

    fun scenicScores(): List<Int> {
        val scores = mutableListOf<Int>()

        for (row in 1 until width - 1) {
            for (col in 1 until width - 1) {
                scores.add(scenicScoreOf(row, col))
            }
        }
        return scores
    }

    private fun scenicScoreOf(row: Int, col: Int): Int {
        val tree = trees[row][col]

        return score(northOf(row, col), tree) *
                score(eastOf(row, col), tree) *
                score(southOf(row, col), tree) *
                score(westOf(row, col), tree)
    }

    private fun score(trees: List<Int>, it: Int): Int {
        var count = 0
        for (tree in trees) {
            if (tree >= it) {
                count++;
                break
            } else {
                count++
            }
        }
        return count
    }
}
