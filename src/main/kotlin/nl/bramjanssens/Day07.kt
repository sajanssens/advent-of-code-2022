package nl.bramjanssens

import nl.bramjanssens.InputType.Main

fun String.isCdRoot() = this == "$ cd /"
fun String.isLs() = this == "$ ls"
fun String.isCdUp() = startsWith("$ cd") && split(" ")[2] == ".."
fun String.isCdDown() = startsWith("$ cd") && split(" ")[2] != ".."
fun String.isCommand() = startsWith("$")

fun main() {
    var current: Dir
    val root = Dir("/", parent = null)

    // cd /
    current = root

    val lines = lines(7, Main).iterator()
    while (lines.hasNext()) {
        var line = lines.next()

        if (line.isCdRoot()) continue

        if (line.isLs()) {
            while (lines.hasNext()) {
                line = lines.next()

                if (line.isCommand()) break

                val (e, name) = line.split(" ")
                current.children += if (e == "dir") Dir(name, current) else File(name, current, e.toInt())
            }
        }

        if (line.isCdDown()) {
            current = current.find(line.split(" ")[2]) ?: fail()
        }

        if (line.isCdUp()) {
            if (current.parent != null) {
                current = current.parent ?: fail()
            }
        }
    }

    // part 1
    println(root.subDirs().filter { it.size() < 100_000 }.sumOf { it.size() })

    // part 2
    val used = root.size()
    val unused = 70_000_000 - used
    val toBeFreed = 30_000_000 - unused

    println(root.subDirs().filter { it.size() >= toBeFreed }.minOf { it.size() })
}

interface Node {
    val name: String
    val parent: Node?
    fun size(): Int
}

data class Dir(override val name: String, override val parent: Dir?) : Node {
    val children: MutableList<Node> = mutableListOf()

    override fun size() = children.sumOf { it.size() }

    private fun directSubDirs() = children.filterIsInstance<Dir>()

    fun subDirs(): List<Dir> = directSubDirs() + directSubDirs().flatMap { it.subDirs() }

    fun find(dir: String) = directSubDirs().find { it.name == dir }
}

data class File(override val name: String, override val parent: Node?, private val size: Int) : Node {
    override fun size() = size
}

fun fail(): Nothing = throw NotImplementedError()
