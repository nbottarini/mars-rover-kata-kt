package domain

class Plateau(val size: Size) {
    private val obstacles = mutableListOf<Position>()

    fun addObstacleAt(position: Position) {
        obstacles.add(position)
    }

    fun hasObstacleAt(position: Position) = obstacles.contains(position)
}
