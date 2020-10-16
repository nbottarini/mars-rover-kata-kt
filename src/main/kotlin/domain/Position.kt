package domain

class Position(val x: Int, val y: Int) {
    private fun changedBy(deltaX: Int, deltaY: Int) = Position(x + deltaX, y + deltaY)

    fun movedIn(direction: Direction): Position {
        return when(direction) {
            Direction.East -> changedBy(1, 0)
            Direction.West -> changedBy(-1, 0)
            Direction.North -> changedBy(0, 1)
            Direction.South -> changedBy(0, -1)
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
}
