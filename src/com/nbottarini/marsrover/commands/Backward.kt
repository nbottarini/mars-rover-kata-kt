package com.nbottarini.marsrover.commands

import com.nbottarini.marsrover.Directions
import com.nbottarini.marsrover.Position

class Backward : Command() {
    override fun nextPosition(position: Position, direction: Directions) = position.movedIn(direction.opposite())
}
