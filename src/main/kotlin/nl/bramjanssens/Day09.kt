package nl.bramjanssens

import nl.bramjanssens.InputType.Main
import nl.bramjanssens.InputType.Test
import kotlin.math.absoluteValue

fun main() {
    // part1()
    part2()
}

private fun part1() {
    val head = Knot(0, 0)
    val tail = Knot(0, 0)
    val tailMoves = mutableSetOf<Point>()

    for (line in lines(9, Main)) {
        val (dir, steps) = line.split(" ")
        repeat(steps.toInt()) {
            head.move(dir)
            val tailMove = tail.move(dir, head)
            tailMoves.add(tailMove)
        }
    }

    println(tailMoves.count())
}

private fun part2() {
    val knots = List(10) { Knot(0, 0) }
    val head = knots.first()
    val tail = knots.last()
    val tailMoves = mutableSetOf<Point>()

    for (line in lines(9, Test)) {
        val (dir, steps) = line.split(" ")

        repeat(steps.toInt()) {
            head.move(dir)

            for ((k1, k2) in knots.zipWithNext()) {
                val move = k2.move(dir, k1)
                if (k2 === tail) {
                    tailMoves.add(move)
                }
            }
        }
    }

    println(tailMoves.count())
}

data class Knot(var x: Int, var y: Int) {
    fun move(dir: String) {
        when (dir) {
            "R" -> x++
            "L" -> x--
            "U" -> y++
            "D" -> y--
        }
    }

    fun move(dir: String, head: Knot): Point {
        if (!adjacentTo(head)) {
            move(dir)
            if (diagonalTo(head)) {
                correctDiagonal(dir, head)
            }
        }
        return Point(x, y)
    }

    private fun adjacentTo(other: Knot) = (y - other.y).absoluteValue <= 1 && (x - other.x).absoluteValue <= 1

    private fun diagonalTo(other: Knot) = (y - other.y).absoluteValue >= 1 && (x - other.x).absoluteValue >= 1

    private fun correctDiagonal(dir: String, head: Knot) {
        if (dir.horizontal) y = head.y
        if (dir.vertical) x = head.x
    }

}

data class Point(val x: Int, val y: Int)

private val String.horizontal
    get() = this == "L" || this == "R"

private val String.vertical
    get() = this == "U" || this == "D"
