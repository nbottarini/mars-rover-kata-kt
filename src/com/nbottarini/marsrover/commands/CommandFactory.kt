package com.nbottarini.marsrover.commands

class CommandFactory {
    fun create(commandKey: String) = when(commandKey) {
        "F" -> Forward()
        "B" -> Backward()
        "R" -> TurnRight()
        "L" -> TurnLeft()
        else -> throw Throwable("Unsupported command $commandKey")
    }
}
