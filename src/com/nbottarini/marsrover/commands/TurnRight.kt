package com.nbottarini.marsrover.commands

import com.nbottarini.marsrover.Directions
import com.nbottarini.marsrover.Position

class TurnRight: Command() {
    override fun nextDirection(position: Position, direction: Directions) = direction.turnedRight()
}
