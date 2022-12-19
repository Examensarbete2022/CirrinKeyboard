package com.example.examensarbete

import kotlin.math.pow
import kotlin.math.sqrt

class Circle(var x: Float, var y: Float, var radius: Float) {
    fun contains(x: Float, y: Float): Boolean {
        return sqrt((x - this.x).toDouble().pow(2.0) + (y - this.y).toDouble().pow(2.0)) < radius
    }

    // assign x and y to the corresponding property to the circle
    fun offsetTo(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    infix fun intersects(other: Circle): Boolean {
        return sqrt((other.x - this.x).toDouble().pow(2.0) + (other.y - this.y).toDouble().pow(2.0)) < radius + other.radius
    }

    //skapa en funtion som använder intersects men som funkar som en onMouseEnter och onMouseLeave

}