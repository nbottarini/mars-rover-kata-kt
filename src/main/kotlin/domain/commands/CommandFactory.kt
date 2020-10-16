package domain.commands

class CommandFactory {
    fun create(commandKey: String): Command {
        return when(commandKey) {
            "F" -> ForwardCommand()
            "B" -> BackwardCommand()
            "R" -> TurnRightCommand()
            "L" -> TurnLeftCommand()
            else -> NoopCommand()
        }
    }
}
