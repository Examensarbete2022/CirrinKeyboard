package com.example.examensarbete

import android.graphics.Canvas
import android.graphics.Paint

fun Canvas.drawCircle(circle: Circle, paint: Paint) {
    drawCircle(circle.x, circle.y, circle.radius, paint)
}