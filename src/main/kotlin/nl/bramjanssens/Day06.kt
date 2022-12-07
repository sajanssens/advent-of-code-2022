package nl.bramjanssens

import nl.bramjanssens.InputType.Main

private const val four = 4
private const val fourteen = 14

fun main() {
    val input = lines(6, Main).first()

    // part 1
    input
        .windowed(four)
        .first { it.toSet().size == four }
        .also { println(input.indexOf(it) + four) }

    // part 2
    input
        .windowed(fourteen)
        .first { it.toSet().size == fourteen }
        .also { println(input.indexOf(it) + fourteen) }
}

