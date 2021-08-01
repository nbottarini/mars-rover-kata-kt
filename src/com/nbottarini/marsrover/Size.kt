package com.nbottarini.marsrover

class Size(val width: Int, val height: Int) {
    override fun equals(other: Any?) = other is Size && other.width == width && other.height == height

    override fun hashCode() = 31 * width + height

    override fun toString() = "Size($width, $height)"
}
