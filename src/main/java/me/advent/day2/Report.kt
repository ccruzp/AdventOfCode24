package me.advent.day2

import me.advent.tools.FileReader
import kotlin.math.abs

class Report(data: String) {
    private var report: List<Int>

    init {
        report = if (data.isBlank()) {
            listOf()

        } else {
            data
                .replace("\\s+", " ")
                .split(' ')
                .map { it.toInt() }
        }
    }

    /**
     * Check if a report is safe.
     *
     * @return true if the report is safe; otherwise returns false.
     */
    fun isSafe(): Boolean {
        val stable = checkStability()
        val levelDifference = checkLevelDifference()
        return stable && levelDifference
    }

    /**
     * Check if a report is stable.
     *
     * @return true if the report is stable; otherwise returns false.
     */
    private fun checkStability(): Boolean {
        return increasingStably() || decreasingStably()
    }

    /**
     * Check if a report is stably increasing.
     *
     * @return true if the levels are stably increasing; otherwise returns false.
     */
    private fun increasingStably():Boolean {
        var accumulator = true
        for (i in report.indices) {
            if (i == 0) {
                continue
            }

            accumulator = accumulator && (report[i - 1] < report[i])
        }
        return accumulator
    }

    /**
     * Check if a report is stably decreasing.
     *
     * @return true if the levels are stably decreasing; otherwise returns false.
     */
    private fun decreasingStably(): Boolean {
        var accumulator = true
        for (i in report.indices) {
            if (i == 0) {
                continue
            }

            accumulator = accumulator && (report[i - 1] > report[i])
        }
        return accumulator
    }

    /**
     * Checks that the difference between levels is in the acceptable range.
     *
     * @return true if all the level differences are in the acceptable range; otherwise returns false.
     */
    private fun checkLevelDifference(): Boolean {
        for (i in 0..< (report.size - 1)) {
            val diff = abs(report[i] - report[i + 1])
            if (diff <= 0 || diff >= 4) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val path = "src/main/java/me/advent/day2/"
    val filename = "input.txt"
    val reports = FileReader.readFile(path + filename)
        .lines()
        .filter { it.isNotBlank() }
        .map { Report(it) }

    val totalSafe = reports
        .map { it.isSafe() }
        .count { it }
    println("There are a total of $totalSafe reports")
}