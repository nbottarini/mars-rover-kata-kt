package domain.commands

import domain.Direction
import domain.Position

abstract class Command {
    open fun nextPosition(position: Position, direction: Direction) = position
    open fun nextDirection(position: Position, direction: Direction) = direction
}
