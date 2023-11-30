package nl.bramjanssens

import nl.bramjanssens.InputType.Main
import kotlin.math.absoluteValue

fun main() {
    part1()
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
            if (!tail.adjacentTo(head)) {
                tail.moveTowards(head)
            }
            tailMoves.add(tail.toPoint())
        }
    }

    println(tailMoves.count())
}

private fun part2() {
    val knots = List(10) { Knot(0, 0) }
    val tailMoves = mutableSetOf<Point>()

    for (line in lines(9, Main)) {
        val (dir, steps) = line.split(" ")
        val head = knots.first()

        repeat(steps.toInt()) {
            head.move(dir)

            for ((h, t) in knots.zipWithNext()) {
                if (!t.adjacentTo(h)) {
                    t.moveTowards(h)
                }
            }

            tailMoves.add(knots.last().toPoint())
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
        return toPoint()
    }

    fun moveTowards(other: Knot): Point {
        val orientation = other.orientationTowards(this)

        if (orientation == "R") {
            this.x++
        } else if (orientation == "L") {
            this.x--
        } else if (orientation == "U") {
            this.y++
        } else if (orientation == "D") {
            this.y--
        } else if (orientation == "NE") {
            this.x++
            this.y++
        } else if (orientation == "NW") {
            this.x--
            this.y++
        } else if (orientation == "SE") {
            this.x++
            this.y--
        } else if (orientation == "SW") {
            this.x--
            this.y--
        }

        return toPoint()
    }

    fun toPoint() = Point(x, y)

    internal fun orientationTowards(other: Knot): String {
        if (x == other.x && y == other.y) return "SAME"

        if (y == other.y && x > other.x) return "R"
        if (y == other.y && x < other.x) return "L"
        if (x == other.x && y > other.y) return "U"
        if (x == other.x && y < other.y) return "D"

        if (x > other.x && y > other.y) return "NE"
        if (x < other.x && y > other.y) return "NW"
        if (x < other.x && y < other.y) return "SW"
        if (x > other.x && y < other.y) return "SE"

        return "?"
    }

    internal fun isRightFrom(other: Knot): Boolean {
        return y == other.y && x > other.x
    }

    fun adjacentTo(other: Knot) = (y - other.y).absoluteValue <= 1 && (x - other.x).absoluteValue <= 1

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
