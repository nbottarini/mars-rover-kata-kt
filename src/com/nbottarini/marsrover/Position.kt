package com.nbottarini.marsrover

class Position(val x: Int, val y: Int) {
    private fun changedBy(deltaX: Int, deltaY: Int) = Position(x + deltaX, y + deltaY)

    fun movedIn(direction: Directions): Position {
        return when(direction) {
            Directions.East -> changedBy(1, 0)
            Directions.West -> changedBy(-1, 0)
            Directions.North -> changedBy(0, 1)
            Directions.South -> changedBy(0, -1)
        }
    }

    fun wrappedBy(size: Size) = Position((x + size.width) % size.width , (y + size.height) % size.height)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString() = "Position($x, $y)"
}
