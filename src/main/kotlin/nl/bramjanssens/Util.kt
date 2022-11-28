package nl.bramjanssens

enum class InputType private constructor(val filename: String) {
    Test("test"), Main("main")
}

fun lines(day: Int, type: InputType) =
    {}.javaClass.getResourceAsStream("/day$day/${type.filename}.txt")?.bufferedReader()?.readLines() ?: listOf("no input...")



