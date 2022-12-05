package nl.bramjanssens

import nl.bramjanssens.InputType.Main
import java.util.*

fun main() {
    val numberOfStacks = 9 // n
    val highestStack = 8

    // create n stacks
    val stacks = List(numberOfStacks) { Stack<String>() }

    // fill all stacks
    lines(5, Main)
        .take(highestStack)
        .reversed()
        .forEach {
            for (stackNr in 0 until numberOfStacks) {
                with(crate(it, stackNr)) {
                    if (isNotBlank()) stacks[stackNr].push(this)
                }
            }
        }

    // rearrangement procedure
    lines(5, Main)
        .drop(highestStack + 2)
        .map { filterWords(it) }
        .forEach {
            val (qty, from, to) = step(it)
            // part 1
            // repeat(qty) {
            //     stacks[to].push(stacks[from].pop())
            // }

            // part 2
            Stack<String>().apply {
                repeat(qty) { add(stacks[from].pop()) } // pick up `qty` crates from stack[from]
                repeat(qty) { stacks[to].push(pop()) }  // put down `qty` crates on stack[to]
            }
        }

    println(stacks.joinToString("") { it.peek() })
}

private fun crate(it: String, n: Int) = it.substring(4 * n + 1, 4 * n + 2)

private fun filterWords(it: String) = it.replace("move ", "").replace(" from ", "|").replace(" to ", "|")

private fun step(it: String): Triple<Int, Int, Int> {
    val step = it.split("|").map { it.toInt() }
    return Triple(step[0], step[1] - 1, step[2] - 1)
}

