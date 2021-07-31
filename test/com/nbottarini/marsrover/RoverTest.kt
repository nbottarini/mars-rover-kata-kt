package com.nbottarini.marsrover

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RoverTest {
    @ParameterizedTest
    @CsvSource(
        "East, 3:5:E:OK",
        "West, 1:5:W:OK",
        "North, 2:6:N:OK",
        "South, 2:4:S:OK"
    )
    fun `F command moves the rover 1 step forward in the current direction`(direction: String, expected: String) {
        val rover = rover(Position(2, 5), direction)

        val result = rover.execute("F")

        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "East, 1:5:E:OK",
        "West, 3:5:W:OK",
        "North, 2:4:N:OK",
        "South, 2:6:S:OK"
    )
    fun `B command moves the rover 1 step backward in the current direction`(direction: String, expected: String) {
        val rover = rover(Position(2, 5), direction)

        val result = rover.execute("B")

        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "FFF, 8:4:E:OK",
        "BBBB, 1:4:E:OK",
        "FBFB, 5:4:E:OK",
        "FLF, 6:5:N:OK",
        "BBRFF, 3:2:S:OK",
        "RRLL, 5:4:E:OK"
    )
    fun `multiple commands`(commands: String, expected: String) {
        val rover = rover(Position(5, 4), Directions.East)

        val result = rover.execute(commands)

        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "North, E",
        "East, S",
        "South, W",
        "West, N"
    )
    fun `R turns direction right`(direction: String, expected: String) {
        val rover = rover(Position(3, 2), direction)

        val result = rover.execute("R")

        assertThat(result).isEqualTo("3:2:$expected:OK")
    }

    @ParameterizedTest
    @CsvSource(
        "North, W",
        "West, S",
        "South, E",
        "East, N"
    )
    fun `L turns direction left`(direction: String, expected: String) {
        val rover = rover(Position(3, 2), direction)

        val result = rover.execute("L")

        assertThat(result).isEqualTo("3:2:$expected:OK")
    }

    @ParameterizedTest
    @CsvSource(
        "East, 9:0, 0:0:E:OK",
        "North, 0:9, 0:0:N:OK",
        "West, 0:0, 9:0:W:OK",
        "South, 0:0, 0:9:S:OK"
    )
    fun `when rover moves at one edge of the plateau it appears at the other`(direction: String, position: String, expected: String) {
        val rover = rover(position, direction, Plateau(Size(10, 10)))

        val result = rover.execute("F")

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `when an obstacle is found the remaining commands are aborted and an ERROR is returned`() {
        plateau.addObstacleAt(Position(3, 0))
        val rover = rover(Position(1, 0), Directions.East)

        val result = rover.execute("FFFF")

        assertThat(result).isEqualTo("2:0:E:ERROR")
    }

    private fun rover(position: Position, direction: String) = rover(position, Directions.valueOf(direction))

    private fun rover(position: Position, direction: Directions) = Rover(position, direction, plateau)

    private fun rover(position: String, direction: String, plateau: Plateau) =
        Rover(positionFromString(position), Directions.valueOf(direction), plateau)

    private fun positionFromString(position: String): Position {
        val parts = position.split(":").map { it.toInt() }
        return Position(parts[0], parts[1])
    }

    private val plateauSize = Size(10, 10)
    private val plateau = Plateau(plateauSize)
}
