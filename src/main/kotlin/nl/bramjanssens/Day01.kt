package nl.bramjanssens

import nl.bramjanssens.InputType.Main
import nl.bramjanssens.InputType.Test

fun main() {
    val sums = mutableListOf<Int>()
    var sum = 0
    for (i in lines(1, Main)) {
        if (i != "") {
            sum += i.toInt()
        } else {
            sums.add(sum)
            sum = 0
        }
    }
    println(sums.max())

    println(
        sums.toList()
            .sortedDescending()
            .take(3)
            .sum()
    )

    val sep = System.getProperty("line.separator")
    val message = text(1, Test)
        .split(sep + sep)
        .maxOf {
            it.split(sep)
                .filter { it.isNotEmpty() }
                .sumOf(String::toInt)
        }

    println(message)
}
