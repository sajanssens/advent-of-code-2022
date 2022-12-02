package nl.bramjanssens

import nl.bramjanssens.InputType.Main

fun main() {
    // part 1
    println(
        lines(2, Main)
            .map { it.split(" ") }
            .sumOf { score(it[0], it[1]) }
    )

    // part 2
    println(
        lines(2, Main)
            .map { it.split(" ") }
            .sumOf { score(it[0], whatToChoose(it[0], it[1])) }
    )
}

fun shape(s: String) =
    when (s) {
        "Y" -> 2
        "X" -> 1
        "Z" -> 3
        else -> throw IllegalArgumentException()
    }

// A for Rock, B for Paper, and C for Scissors (he)
// X for Rock, Y for Paper, and Z for Scissors (you)
fun score(he: String, you: String) = shape(you) + round(he, you)

private fun round(he: String, you: String): Int {
    if (draw(you, he)) return 3
    if (win(you, he)) return 6
    return 0
}

private fun win(you: String, he: String) =
    you == "X" && he == "C" ||
            you == "Y" && he == "A" ||
            you == "Z" && he == "B"

private fun draw(you: String, he: String) =
    you == "X" && he == "A" ||
            you == "Y" && he == "B" ||
            you == "Z" && he == "C"

// A for Rock, B for Paper, and C for Scissors (he)
// X for Rock, Y for Paper, and Z for Scissors (you)
fun whatToChoose(he: String, outcome: String) = when (outcome) {
    "X" -> loose(he)
    "Y" -> draw(he)
    "Z" -> win(he)
    else -> throw IllegalArgumentException()
}

private fun win(he: String) = when (he) { // win
    "A" -> "Y"
    "B" -> "Z"
    "C" -> "X"
    else -> throw IllegalArgumentException()
}

private fun draw(he: String) = when (he) {
    "A" -> "X"
    "B" -> "Y"
    "C" -> "Z"
    else -> throw IllegalArgumentException()
}

private fun loose(he: String) = when (he) {
    "A" -> "Z"
    "B" -> "X"
    "C" -> "Y"
    else -> throw IllegalArgumentException()
}
