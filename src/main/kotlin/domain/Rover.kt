package domain

import domain.commands.*

class Rover(initialPosition: Position, initialDirection: Direction, private val plateau: Plateau) {
    private var position = initialPosition
    private var direction = initialDirection
    private val commandFactory = CommandFactory()
    private val ERROR = "ERROR"
    private val OK = "OK"

    fun execute(commands: String): String {
        try {
            processCommands(commands)
        } catch (e: ObstacleFoundError) {
            return generateResponse(ERROR)
        }
        return generateResponse(OK)
    }

    private fun processCommands(commands: String) {
        commandList(commands).forEach { processCommand(it) }
    }

    private fun commandList(commands: String) = commands.split("")

    private fun processCommand(commandKey: String) {
        val command = createCommand(commandKey)
        updatePosition(command)
        updateDirection(command)
    }

    private fun createCommand(commandKey: String) = commandFactory.create(commandKey)

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

    private fun generateResponse(status: String) = positionResponse() + ":" + directionResponse() + ":" + status

    private fun positionResponse() = "${position.x}:${position.y}"

    private fun directionResponse() = direction.toString()[0]
}
