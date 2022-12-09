package com.example.examensarbete

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View

class CustomView(context: Context) : View(context) {

    var touched = false
     val circle1 = Circle(200f, 200f, 100f)
     val circle2 = Circle(200f, 700f, 100f)
     var selectedCircle: Circle? = null

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply{

            if (touched) {
                drawColor(Color.RED)
            } else {
                drawColor(Color.BLUE)
            }
            drawCircle(circle1, paint)
            drawCircle(circle2, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("Touched", "Touched down at: $x, Y: $y")
                    /*
                    selectedCircle = if (circle1.contains(x, y)) {
                        circle1
                    } else if (circle2.contains(x, y)) {
                        circle2
                    } else {
                        null
                    }
                    */
                    selectedCircle = when {
                        circle1.contains(x, y) -> circle1
                        circle2.contains(x, y) -> circle2
                        else -> null
                    }
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d("Touched", "Touched move at: $x, Y: $y")
                    when(selectedCircle) {
                        circle1 -> circle1.offsetTo(x, y)
                        circle2 -> circle2.offsetTo(x, y)
                        else -> return true
                    }
                    touched = circle1 intersects circle2
                    invalidate()
                    return true
                }
            }
        }
        return false
    }
}