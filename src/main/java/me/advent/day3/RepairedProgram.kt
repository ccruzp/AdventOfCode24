package me.advent.day3

import me.advent.tools.FileReader.Companion.readLines

/**
 * Program to calculate the sum of products of some numbers.
 */
class RepairedProgram {
    companion object {

        /**
         * Regex to match to obtain values.
         */
        private val pattern = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

        /**
         * Calculates the result of the program.
         *
         * @param line [String] to be parsed and processed.
         * @return Sum of the products of this line.
         */
        fun calculateSum(line: String): Long {

            return extractValues(line)
                .map { product(it) }
                .reduce { acc, elem -> acc + elem }

        }

        /**
         * Parse and process a line of the input program.
         *
         * @param line input line.
         * @return List that contains the elements to be multiplied.
         */
        private fun extractValues(line: String): List<Pair<Int, Int>> {
            val values = mutableListOf<Pair<Int, Int>>()

            val matches = pattern.findAll(line)
            matches.forEach { matchResult ->
                val (first, second) = matchResult.destructured
                values.add(Pair(first.toInt(), second.toInt()))
            }
            return values
        }

        /**
         * Calculate the product of a pair.
         *
         * @param values [Pair] that contains the values to be multiplied.
         * @return product of the elements of the pair.
         */
        private fun product(values: Pair<Int, Int>) = (values.first * values.second).toLong()
    }
}

fun main() {
    val path = "src/main/inputs/day3/"
    val filename = "input.txt"

    val lines = readLines(path + filename)
        .filter { it.isNotBlank() }

    val sum = lines.sumOf { RepairedProgram.calculateSum(it) }
    println("Total $sum")
}