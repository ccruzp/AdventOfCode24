package me.advent.tools

import java.io.File

class FileReader {
    companion object {
        /**
         * Read the contents of a file.
         *
         * filename name of the file to be read.
         * @return content of the file.
         */
        fun readFile(filename: String) = File(filename).readText()

        /**
         * Get the contents of a file split by lines.
         *
         * @param filename name of the file to be read.
         * @return list that contains the content read split by line.
         */
        fun readLines(filename: String) = readFile(filename).split('\n')
    }
}