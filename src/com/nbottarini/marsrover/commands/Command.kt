package com.nbottarini.marsrover.commands

import com.nbottarini.marsrover.Directions
import com.nbottarini.marsrover.Position

abstract class Command {
    open fun nextPosition(position: Position, direction: Directions) = position
    open fun nextDirection(position: Position, direction: Directions) = direction
}
