package nl.bramjanssens

import java.io.InputStream

enum class InputType(val filename: String) {
    Test("test"), Main("main")
}

fun lines(day: Int, type: InputType) =
    inputStream(day, type)?.bufferedReader()?.readLines() ?: listOf("no input...")

fun text(day: Int, type: InputType) =
    inputStream(day, type)?.bufferedReader()?.readText() ?: "no input..."

private fun inputStream(day: Int, type: InputType): InputStream? = {}.javaClass.getResourceAsStream("/day$day/${type.filename}.txt")


