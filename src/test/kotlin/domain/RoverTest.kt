package domain

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
    fun `F command moves the rover 1 step forward in the current direction`(initialDirection: String, expectedResponse: String) {
        val rover = Rover(Position(2, 5), Direction.valueOf(initialDirection), plateau)

        val result = rover.execute("F")

        assertThat(result).isEqualTo(expectedResponse)
    }

    @ParameterizedTest
    @CsvSource(
        "East, 1:5:E:OK",
        "West, 3:5:W:OK",
        "North, 2:4:N:OK",
        "South, 2:6:S:OK"
    )
    fun `B command moves the rover 1 step backward in the current direction`(initialDirection: String, expectedResponse: String) {
        val rover = Rover(Position(2, 5), Direction.valueOf(initialDirection), plateau)

        val result = rover.execute("B")

        assertThat(result).isEqualTo(expectedResponse)
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
    fun `multiple commands moves the rover from initial position processing each command`(commands: String, expectedResponse: String) {
        val rover = Rover(Position(5, 4), Direction.East, plateau)

        val result = rover.execute(commands)

        assertThat(result).isEqualTo(expectedResponse)
    }

    @ParameterizedTest
    @CsvSource(
        "North, E",
        "East, S",
        "South, W",
        "West, N"
    )
    fun `R turns direction right`(initialDirection: String, expectedDirection: String) {
        val rover = Rover(Position(3, 2), Direction.valueOf(initialDirection), plateau)

        val result = rover.execute("R")

        assertThat(result).isEqualTo("3:2:$expectedDirection:OK")
    }

    @ParameterizedTest
    @CsvSource(
        "North, W",
        "West, S",
        "South, E",
        "East, N"
    )
    fun `L turns direction left`(initialDirection: String, expectedDirection: String) {
        val rover = Rover(Position(3, 2), Direction.valueOf(initialDirection), plateau)

        val result = rover.execute("L")

        assertThat(result).isEqualTo("3:2:$expectedDirection:OK")
    }

    @ParameterizedTest
    @CsvSource(
        "East, 9:0, 0:0:E:OK",
        "North, 0:9, 0:0:N:OK",
        "West, 0:0, 9:0:W:OK",
        "South, 0:0, 0:9:S:OK"
    )
    fun `when rover moves at one edge of the plateau it appears at the other`(initialDirection: String, position: String, expectedResponse: String) {
        val plateauSize = Size(10, 10)
        val plateau = Plateau(plateauSize)
        val rover = Rover(positionFromString(position), Direction.valueOf(initialDirection), plateau)

        val result = rover.execute("F")

        assertThat(result).isEqualTo(expectedResponse)
    }

    @Test
    fun `when an obstacle is found the remaining commands are aborted and an ERROR is returned`() {
        val plateau = Plateau(plateauSize)
        plateau.addObstacleAt(Position(3, 0))
        val rover = Rover(Position(1, 0), Direction.East, plateau)

        val result = rover.execute("FFFF")

        assertThat(result).isEqualTo("2:0:E:ERROR")
    }

    private fun positionFromString(position: String): Position {
        val parts = position.split(":")
        return Position(parts[0].toInt(), parts[1].toInt())
    }

    private val plateauSize = Size(10, 10)
    private val plateau = Plateau(plateauSize)
}
