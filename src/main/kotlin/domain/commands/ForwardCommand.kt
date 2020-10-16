package domain.commands

import domain.Direction
import domain.Position

class ForwardCommand : Command() {
    override fun nextPosition(position: Position, direction: Direction) = position.movedIn(direction)
}
