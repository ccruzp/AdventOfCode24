package me.advent.day2

import me.advent.tools.FileReader
import kotlin.math.abs

class ReportAnalyzer(data: String) {
    internal val report: List<Int>

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
     * @param [isDampenable] if there are dampeners for this report. False by default.
     * @return true if the report is safe; otherwise returns false.
     */
    fun isSafe(isDampenable: Boolean = false): Boolean {
        val stable = checkStability(report)
        val levelDifference = checkLevelDifference(report)
        var safe = levelDifference && stable

        if (!safe && isDampenable) {
            for (i in report.indices) {
                val dampenedReport = report.toMutableList()
                dampenedReport.removeAt(i)
                safe = checkLevelDifference(dampenedReport) && checkStability(dampenedReport)

                if (safe) {
                    return true
                }
            }
            return false
        }
        return true
    }

    companion object {
        /**
         * Check if a report is stable.
         *
         * @param report Report to be checked.
         * @return true if the report is stable; otherwise returns false.
         */
        private fun checkStability(report: List<Int>): Boolean {
            return stablyIncreasing(report) || stablyDecreasing(report)
        }

        /**
         * Check if the given Report is stably increasing.
         *
         * @param [report] Report to be checked.
         * @return true if the report is stably increasing; otherwise returns false.
         */
        private fun stablyIncreasing(report: List<Int>): Boolean {
            var stable = true
            for (i in report.indices) {
                if (i == 0) {
                    continue
                }
                stable = stable && (report[i-1] < report[i])
            }
            return stable
        }

        /**
         * Check if the given report is stably decreasing.
         *
         * @param [report] Report to be checked.
         * @return true if the report is stably decreasing; otherwise returns false.
         */
        private fun stablyDecreasing(report: List<Int>): Boolean {
            var stable = true
            for (i in report.indices) {
                if (i == 0) {
                    continue
                }
                stable = stable && (report[i-1] > report[i])
            }
            return stable
        }
    }

    /**
     * Check the level differences on a report.
     *
     * @param [report] Report to be checked.
     * @return true if the level difference is in the acceptable range; otherwise returns false.
     */
    private fun checkLevelDifference(report: List<Int>): Boolean {
        var inRange = true
        for (i in report.indices) {
            if (i == 0) {
                continue
            }

            val difference = abs(report[i - 1] - report[i])
            inRange = inRange && (difference > 0) && (difference < 4)
        }

        return inRange
    }
}

fun main() {
    val path = "src/main/inputs/day2/"
    val filename = "input.txt"
    val reportAnalyzers = FileReader.readFile(path + filename)
        .lines()
        .filter { it.isNotBlank() }
        .map { ReportAnalyzer(it) }

    val totalSafe = reportAnalyzers
        .map { it.isSafe(isDampenable = true) }
        .count { it }
    println("There are a total of $totalSafe safe reports")
}