package me.advent.day4

/**
 * Types of movements available.
 */
enum class MovementType {
    // Vertical
    UPPER_VERTICAL {
        override fun move(start: Coordinate) = Coordinate(x = start.x, y = start.y - 1)
    },

    LOWER_VERTICAL {
        override fun move(start: Coordinate) = Coordinate(x = start.x, y = start.y + 1)
    },

    // Horizontal
    LEFT_HORIZONTAL {
        override fun move(start: Coordinate) = Coordinate(x = start.x - 1, y = start.y)
    },

    RIGHT_HORIZONTAL {
        override fun move(start: Coordinate) = Coordinate(x = start.x + 1, y = start.y)
    },

    // Diagonals
    LEFT_UPPER_DIAGONAL {
        override fun move(start: Coordinate) = Coordinate(x = start.x - 1, y = start.y - 1)
    },

    RIGHT_UPPER_DIAGONAL {
        override fun move(start: Coordinate) = Coordinate(x = start.x + 1, y = start.y - 1)
    },

    LEFT_LOWER_DIAGONAL {
        override fun move(start:Coordinate) = Coordinate(x = start.x - 1, y = start.y + 1)
    },

    RIGHT_LOWER_DIAGONAL {
        override fun move(start: Coordinate) = Coordinate(x = start.x + 1, y = start.y + 1)
    };

    /**
     * Move one space.
     */
    abstract fun move(start: Coordinate): Coordinate
}
