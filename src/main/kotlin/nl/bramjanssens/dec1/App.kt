package nl.bramjanssens.dec1

import nl.bramjanssens.InputType.Main
import nl.bramjanssens.InputType.Test
import nl.bramjanssens.lines

fun main() {
    lines(1, Test).forEach { println(it) }
    lines(1, Main).forEach { println(it) }
}
