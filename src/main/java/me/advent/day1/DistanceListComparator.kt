package me.advent.day1

import me.advent.tools.FileReader
import kotlin.math.abs

/**
 * Class that compares the lists parsed from the input data.
 *
 * @param [data] used to generate the lists
 */
class DistanceListComparator(data: String) {
    private val first = mutableListOf<String>()
    private val second = mutableListOf<String>()

    init {
        for (line in data.lines()) {
            if (line.isBlank()) {
                continue
            }

            val split = line
                .replace(regex = "\\s+".toRegex(), replacement = " ")
                .split(' ')
            if (split.size < 2) {
                continue
            }
            first.add(split[0])
            second.add(split[1])
        }
    }

    /**
     * Calculate the total distance between the two locationIds lists
     *
     * @return total distance between lists.
     */
    fun calculateTotalDistance(): Int {
        assert(first.size == second.size)

        val firstSorted = first.map { it.toInt() }.sorted()
        val secondSorted = second.map { it.toInt() }.sorted()

        val distances = firstSorted.zip(secondSorted) { f, s -> abs(f-s) }
        return distances.sum()
    }

    /**
     * @return similarity score between its lists
     */
    fun calculateSimilarityScore(): Int {
        var similarityScore = 0

        val locations = first.map { it.toInt() }
        val occurrence = second.map { it.toInt() }
        for (value in locations) {
            val times = occurrence.count { it == value }
            similarityScore += (value * times)
        }
        return similarityScore
    }
}

fun main() {
    val folder = "src/main/java/me/advent/day1/"
    val filename = "input.txt"
    val content = FileReader.readFile(folder + filename)
    val calculator = DistanceListComparator(data = content)
    println("The total distance of $filename is: ${calculator.calculateTotalDistance()}")
    println("The similarity score of $filename is: ${calculator.calculateSimilarityScore()}")
}