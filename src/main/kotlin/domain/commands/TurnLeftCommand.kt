package domain.commands

import domain.Direction
import domain.Position

class TurnLeftCommand: Command() {
    override fun nextDirection(position: Position, direction: Direction) = direction.turnedLeft()
}
