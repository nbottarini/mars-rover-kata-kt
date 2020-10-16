package domain.commands

import domain.Direction
import domain.Position

class BackwardCommand : Command() {
    override fun nextPosition(position: Position, direction: Direction) = position.movedIn(direction.opposite())
}