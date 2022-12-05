package nl.bramjanssens

import nl.bramjanssens.InputType.Main

fun main() {
    // part 1
    println(
        lines(3, Main)
            .map { chop(it) }
            .map { intersect(it) }
            .sumOf { prio(it) }
    )

    // part 2
    println(lines(3, Main)
        .chunked(3)
        .map { intersect(it) }
        .sumOf { prio(it) }
    )
}

private fun chop(s: String) = s.substring(0, s.length / 2) to s.substring(s.length / 2)

private fun intersect(p: Pair<String, String>) = p.first.toSet().intersect(p.second.toSet()).first()

private fun intersect(it: List<String>) = it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first()

private fun prio(f: Char) = if (f in 'a'..'z') f.code - 96 else f.code - 38
