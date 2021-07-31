package com.nbottarini.marsrover

enum class Directions {
    South {
        override fun turnedRight() = West
        override fun turnedLeft() = East
        override fun opposite() = North
    },
    East {
        override fun turnedRight() = South
        override fun turnedLeft() = North
        override fun opposite() = West
    },
    North {
        override fun turnedRight() = East
        override fun turnedLeft() = West
        override fun opposite() = South
    },
    West {
        override fun turnedRight() = North
        override fun turnedLeft() = South
        override fun opposite() = East
    };

    abstract fun turnedRight(): Directions
    abstract fun turnedLeft(): Directions
    abstract fun opposite(): Directions
}
