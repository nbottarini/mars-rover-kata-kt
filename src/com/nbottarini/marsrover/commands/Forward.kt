package com.nbottarini.marsrover.commands

import com.nbottarini.marsrover.Directions
import com.nbottarini.marsrover.Position

class Forward : Command() {
    override fun nextPosition(position: Position, direction: Directions) = position.movedIn(direction)
}
