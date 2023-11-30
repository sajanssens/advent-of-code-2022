package nl.bramjanssens

import java.util.*

fun main() {
    part1()
    part2()

}

private fun part1() {
    val testMonkeys = listOf(
        Monkey(LinkedList(listOf(79, 98)), { x -> x * 19 }, { x -> (x % 23) == 0 }, 2 to 3),
        Monkey(LinkedList(listOf(54, 65, 75, 74)), { x -> x + 6 }, { x -> (x % 19) == 0 }, 2 to 0),
        Monkey(LinkedList(listOf(79, 60, 97)), { x -> x * x }, { x -> (x % 13) == 0 }, 1 to 3),
        Monkey(LinkedList(listOf(74)), { x -> x + 3 }, { x -> (x % 17) == 0 }, 0 to 1)
    )

    val monkeys = listOf(
        Monkey(LinkedList(listOf(64, 89, 65, 95)), { x -> x * 7 }, { x -> (x % 3) == 0 }, 4 to 1),
        Monkey(LinkedList(listOf(76, 66, 74, 87, 70, 56, 51, 66)), { x -> x + 5 }, { x -> (x % 13) == 0 }, 7 to 3),
        Monkey(LinkedList(listOf(91, 60, 63)), { x -> x * x }, { x -> (x % 2) == 0 }, 6 to 5),
        Monkey(LinkedList(listOf(92, 61, 79, 97, 79)), { x -> x + 6 }, { x -> (x % 11) == 0 }, 2 to 6),
        Monkey(LinkedList(listOf(93, 54)), { x -> x * 11 }, { x -> (x % 5) == 0 }, 1 to 7),
        Monkey(LinkedList(listOf(60, 79, 92, 69, 88, 82, 70)), { x -> x + 8 }, { x -> (x % 17) == 0 }, 4 to 0),
        Monkey(LinkedList(listOf(64, 57, 73, 89, 55, 53)), { x -> x + 1 }, { x -> (x % 19) == 0 }, 0 to 5),
        Monkey(LinkedList(listOf(62)), { x -> x + 4 }, { x -> (x % 7) == 0 }, 3 to 2),
    )

    repeat(20) {
        for (m in monkeys) {
            m.inspect(monkeys)
        }
    }

    val sortedMonkeys = monkeys.sortedByDescending { m -> m.inspections }
    val monkeyBusiness = sortedMonkeys[0].inspections * sortedMonkeys[1].inspections
    println(monkeyBusiness)
}

private fun part2() {

}

class Monkey(
    val items: Queue<Int>,
    val operation: (Int) -> Int,
    val test: (Int) -> Boolean,
    val neighbors: Pair<Int, Int>,
    var inspections: Int = 0
) {

    fun inspect(monkeys: List<Monkey>) {
        repeat(items.size) {
            inspections++
            val item = items.poll()
            var worry = operation(item) / 3
            if (test(worry)) {
                monkeys[neighbors.first].items.add(worry)
            } else {
                monkeys[neighbors.second].items.add(worry)
            }
        }
    }
}
