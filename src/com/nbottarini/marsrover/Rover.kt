@file:Suppress("PrivatePropertyName")

package com.nbottarini.marsrover

import com.nbottarini.marsrover.commands.Command
import com.nbottarini.marsrover.commands.CommandFactory

class Rover(initialPosition: Position, initialDirection: Directions, private val plateau: Plateau) {
    private var position = initialPosition
    private var direction = initialDirection
    private val commandFactory = CommandFactory()
    private val ERROR = "ERROR"
    private val OK = "OK"

    fun execute(commands: String): String {
        try {
            processCommands(commands)
        } catch (e: ObstacleFoundError) {
            return generateResult(ERROR)
        }
        return generateResult(OK)
    }

    private fun processCommands(commands: String) {
        commandList(commands).forEach { processCommand(it) }
    }

    private fun commandList(commands: String) = commands.map { it.toString() }

    private fun processCommand(commandKey: String) {
        val command = commandFactory.create(commandKey)
        updatePosition(command)
        updateDirection(command)
    }

    private fun updateDirection(command: Command) {
        direction = command.nextDirection(position, direction)
    }

    private fun updatePosition(command: Command) {
        val nextPosition = calculateNextPosition(command)
        if (plateau.hasObstacleAt(nextPosition)) throw ObstacleFoundError()
        position = nextPosition
    }

    private fun calculateNextPosition(command: Command) =
        command.nextPosition(position, direction).wrappedBy(plateau.size)

    private fun generateResult(status: String) = "${position.result()}:${direction.result()}:${status}"

    private fun Position.result() = "${this.x}:${this.y}"

    private fun Directions.result() = this.toString()[0]
}
