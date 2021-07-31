Mars Rover Kata
===============

## Instructions

A squad of robotic rovers are to be landed by NASA on a plateau on Mars.

This plateau, which is curiously rectangular, must be navigated by the rovers so that their onboard cameras can get a complete view of the surrounding terrain to send back to Earth.

Your task is to develop an API that moves the rovers around on the plateau.

In this API, the plateau is represented as a 10x10 grid, and a rover has state consisting of two parts:

- its position on the grid (represented by an X,Y coordinate pair)
- the compass direction it's facing (one of `N`, `S`, `E`, `W`)

### Requirements

- You are given the initial starting point (x,y) of a rover and the direction (N, S, E, W) it is facing.
- The rover receives a list of commands.
- Implement commands that move the rover forward/backward (**F, B**).
- Implement commands that turn the rover left/right (**L, R**).
- Implement wrapping at edges. But be careful, planets are spheres. Connect the x edge to the other x edge, so (0, 0) for x - 1 is (9, 0), and (0, 0) for y - 1 is (0, 9).
- Implement obstacle detection before each move to a new cell. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

## Examples

- Given a grid with no obstacles, initial position (0, 0) and initial direction N, input **FFRFFLF** gives output **2:3:N:OK**
- Given a grid with no obstacles, initial position (0, 0) and initial direction N, input **FFFFFFFFFF** gives output **0:0:N:OK** (because of wrapping)
- Given a grid with an obstacle at (0,3), initial position (0,0) and initial direction N, input **FFFF** gives output **0:2:N:ERROR**
