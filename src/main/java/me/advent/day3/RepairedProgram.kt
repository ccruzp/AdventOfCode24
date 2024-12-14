package me.advent.day3

import me.advent.tools.FileReader

/**
 * Program to calculate the sum of products of some numbers.
 */
class RepairedProgram {
    companion object {

        /**
         * Regex to match to obtain values.
         */
        private val pattern = """do\(\)|don't\(\)|mul\(\d{1,3},\d{1,3}\)""".toRegex()

        /**
         * Process the instructions received from the input.
         *
         * @param line [String] to be processed.
         * @return Sum of the products calculated from the instructions.
         */
        fun processInstructions(line: String): Long {
            val instructions = parseInstructions(line)

            var sum: Long = 0
            var enabled = true

            for (instruction in instructions) {
                if ("do()" == instruction.value) {
                    println("do() -> Processing enabled")
                    enabled = true

                } else if ("don't()" == instruction.value) {
                    println("don't() -> Processing disabled")
                    enabled = false

                } else if (instruction.value.contains("mul")){
                    print("${instruction.value} -> ")
                    if (enabled) {
                        val num = """\d{1,3}""".toRegex()
                        val product = num.findAll(instruction.value)
                            .map { it.value.toLong() }
                            .reduce { acc, value -> product(acc, value) }
                        println(product)
                        sum += product

                    } else {
                        println("ignored")
                    }
                }
            }
            return sum
        }

        /**
         * Parse and process a line of the input program.
         *
         * @param line input line.
         * @return List that contains the elements to be multiplied.
         */
        private fun parseInstructions(line: String): Sequence<MatchResult> = pattern.findAll(line)

        /**
         * Calculate the product of a pair.
         *
         * @param values [Pair] that contains the values to be multiplied.
         * @return product of the elements of the pair.
         */
        private fun product(first: Long, second: Long) = (first * second)
    }
}

fun main() {
    val path = "src/main/inputs/day3/"
    val filename = "input.txt"

    val content = FileReader.readFile(path + filename)
        .replace("\n".toRegex(), "")

    val sum = RepairedProgram.processInstructions(content)
    println("Total $sum")
}