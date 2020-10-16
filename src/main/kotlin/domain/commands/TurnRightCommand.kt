package domain.commands

import domain.Direction
import domain.Position

class TurnRightCommand: Command() {
    override fun nextDirection(position: Position, direction: Direction) = direction.turnedRight()
}
