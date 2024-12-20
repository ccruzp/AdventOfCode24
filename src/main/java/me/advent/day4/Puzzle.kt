package me.advent.day4

import me.advent.tools.FileReader

/**
 * Word puzzle.
 *
 * @param input to generate the puzzle
 */
class Puzzle(input: String) {
    private val puzzle: MutableList<String> = mutableListOf()

    init {
        input.lines().forEach { puzzle.add(it + '\n') }
    }

    /**
     * Solve this puzzle.
     *
     * @param word word to be searched.
     * @return times [word] appears on this Puzzle.
     */
    fun solve(word: String): Long {
        var total: Long = 0
        for (row in puzzle.indices) {
            for (column in puzzle[row].indices) {
                if (puzzle[row][column] != 'X') {
                    continue
                }

                val startingPoint = Coordinate(row, column)
                movement@ for (movement in MovementType.entries) {
                    var position = startingPoint
                    for (i in word.indices) {
                        if (!isLetterAt(letter = word[i], coordinates = position)) {
                            continue@movement
                        }
                        position = movement.move(position)
                    }
                    total += 1
                }
            }
        }
        return total
    }

    /**
     * Check if the [letter] at [coordinates] is valid.
     *
     * @param letter letter to be found.
     * @param coordinates position ([Pair]) to be checked.
     * @return  true if the given [coordinates] are valid and if on them there is the expected [letter]; otherwise
     *          returns false.
     */
    private fun isLetterAt(letter: Char, coordinates: Coordinate): Boolean {
        return validPosition(position = coordinates)
                && letter == puzzle[coordinates.x][coordinates.y]
    }

    /**
     * Check if the given [position] is valid.
     *
     * @param position position on the puzzle to be checked.
     * @return true if the given [position] is in the limits of the puzzle; otherwise returns false.
     */
    private fun validPosition(position: Coordinate): Boolean {
        val x = position.x
        val y = position.y
        return x >= 0 && x < puzzle.size
                && y >= 0 && y < puzzle[x].length
    }

    override fun toString(): String {
        return puzzle.reduce { acc, line -> acc + line }
    }
}

fun main() {
    val word = "XMAS"
    val filename = "input.txt"
    val inputPath = "src/main/inputs/day4/${filename}"

    val input = FileReader.readFile(inputPath)
    val puzzle = Puzzle(input = input)

    println("Puzzle read:\n $puzzle")
    val count = puzzle.solve(word = word)
    println("Found ${word}: $count times.")
}