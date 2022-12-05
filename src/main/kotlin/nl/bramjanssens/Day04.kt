package nl.bramjanssens

import nl.bramjanssens.InputType.Main

fun main() {
    // part 1
    println(
        sections().count { fullyOverlap(it.first, it.second) }
    )

    // part 2
    println(
        sections().count { overlap(it.first, it.second) }
    )
}

private fun sections() = lines(4, Main)
    .map { it.split(",").flatMap { it.split("-") } }
    .map { it.map { it.toInt() } }
    .map { IntRange(it[0], it[1]) to IntRange(it[2], it[3]) }

private fun fullyOverlap(r1: IntRange, r2: IntRange) = r1.intersect(r2) == r1.toSet() || r2.intersect(r1) == r2.toSet()
private fun overlap(r1: IntRange, r2: IntRange) = r1.intersect(r2).isNotEmpty()
