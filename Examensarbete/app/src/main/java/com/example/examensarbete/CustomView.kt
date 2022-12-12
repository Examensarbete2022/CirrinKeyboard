package com.example.examensarbete

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.swipe.view.*

class CustomView(context: Context) : View(context) {

    var touched = false
    val circle1 = Circle(100f, 100f, 25f)
    var selectedCircle: Circle? = null

    //create 26 circles that sits in a circle
    val circleList = mutableListOf<Circle>()
    //create list of all the letters in the alphabet
    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"


    init {
        for (i in 0..25) {
            val angle = i * 2 * Math.PI / 26
            val x = 400 + 400 * Math.cos(angle)
            val y = 400 + 400 * Math.sin(angle)
            circleList.add(Circle(x.toFloat() + 150, y.toFloat() + 1000, 50f))

        }
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    var str = ""

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply{
            if (touched) {
                drawColor(Color.BLUE)
            } else {
                drawColor(Color.WHITE)
            }
            drawCircle(circle1, paint)
            //drawCircle(circle2, paint)
            circleList.forEach {
                drawCircle(it.x, it.y, it.radius, paint)
            }
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
                        //circle2.contains(x, y) -> circle2
                        else -> null
                    }
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d("Touched", "Touched move at: $x, Y: $y")
                    when(selectedCircle) {
                        circle1 -> circle1.offsetTo(x, y)
                        else -> return true
                    }
                    when(circle1 intersects circleList[0] || circle1 intersects circleList[1] || circle1 intersects circleList[2] || circle1 intersects circleList[3] || circle1 intersects circleList[4] || circle1 intersects circleList[5] || circle1 intersects circleList[6] || circle1 intersects circleList[7] || circle1 intersects circleList[8] || circle1 intersects circleList[9] || circle1 intersects circleList[10] || circle1 intersects circleList[11] || circle1 intersects circleList[12] || circle1 intersects circleList[13] || circle1 intersects circleList[14] || circle1 intersects circleList[15] || circle1 intersects circleList[16] || circle1 intersects circleList[17] || circle1 intersects circleList[18] || circle1 intersects circleList[19] || circle1 intersects circleList[20] || circle1 intersects circleList[21] || circle1 intersects circleList[22] || circle1 intersects circleList[23] || circle1 intersects circleList[24] || circle1 intersects circleList[25]) {
                        true -> {
                            touched = true
                            invalidate()
                        }
                        false -> {
                            touched = false
                            invalidate()
                        }
                    }
                    return true
                }
            }
        }
        return false
    }
}