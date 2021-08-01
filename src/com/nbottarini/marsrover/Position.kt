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

    override fun equals(other: Any?) = other is Position && other.x == x && other.y == y

    override fun hashCode() = 31 * x + y

    override fun toString() = "Position($x, $y)"
}
