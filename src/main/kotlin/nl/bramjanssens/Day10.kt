package nl.bramjanssens

import nl.bramjanssens.InputType.Main

fun main() {
    part1and2()
}

private fun part1and2() {
    val log = mutableListOf<Record>()
    val crt = mutableListOf<Char>()

    var x = 1
    var cycle = 0
    for (line in lines(10, Main)) {
        if (line == "noop") {
            cycle++
            record(log, cycle, x)
            draw(cycle - 1, x, crt) // part 2
        } else {
            val (_, num) = line.split(" ")
            // start cycle 1
            cycle++
            // during cycle 1
            record(log, cycle, x)
            draw(cycle - 1, x, crt) // part 2
            // after cycle 1

            // start cycle 2
            cycle++
            // during cycle 2
            record(log, cycle, x)
            draw(cycle - 1, x, crt) // part 2
            x += num.toInt()
            // after cycle 2
        }
    }

    // part 1
    val indexes = listOf(20, 60, 100, 140, 180, 220)
    var sum = 0
    for (index in indexes) {
        val (c, n) = log[index - 1]
        sum += c * n
    }
    println(sum)

    // part 2
    for ((i, c) in crt.withIndex()) {
        print(c)
        if ((i + 1) % 40 == 0)
            println()
    }
}

private fun record(log: MutableList<Record>, cycle: Int, x: Int) {
    log.add(Record(cycle, x))
}

private fun draw(p: Int, x: Int, crt: MutableList<Char>) {
    if ((p % 40) in x - 1..x + 1)
        crt.add('#')
    else
        crt.add('.')
}

private fun part2() {

}

data class Record(val cycle: Int, val x: Int)
