package com.example.examensarbete

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.logging.Handler


class CustomView(context: Context) : View(context) {

    var touched = false
   // val circle1 = Circle(500f, 1200f, 25f)
    var selectedCircle: Circle? = null
    var latestInteraction : Circle? = null

    //create 26 circles that sits in a circle
    val circleList = mutableListOf<Circle>()
    //create list of all the letters in the alphabet
    var alphabet = arrayOf('x', 'p', 'j', 'v', 'z', 'c', 'g', 'w', 'm', 's', 'a', 't', 'h', 'e', 'r', 'o', 'n', 'i', 'f', 'l', 'd', 'u', 'k', 'b', 'y', 'q')

    init {
        for (i in 0..25) {
            val angle = i * 2 * Math.PI / 26
            val x = 450 + 450 * Math.cos(angle)
            val y = 450 + 450 * Math.sin(angle)
            circleList.add(Circle(x.toFloat() + 80, y.toFloat() + 1000, 50f))
        }
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    val paint2 = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        strokeWidth = 5f
    }

    val clean : Boolean = false
    var str = ""

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply{
            circleList.forEach {
                drawCircle(it.x, it.y, it.radius, paint)
            }
            for (i in 0..25) {
                drawText(alphabet[i].toString(), circleList[i].x - 13, circleList[i].y + 10, paint)
                paint.textSize = 40f
            }

            drawCircle(530f, 1450f, 500f, paint)
            drawCircle(530f, 1450f, 400f, paint)
            Paint.Style.FILL

            drawText("Clear", 50f, 1970f, paint)
            drawRect(200f, 1900f, 20f, 2000f, paint)

            drawText("Backspace", 800f, 1970f, paint)
            drawRect(1010f, 1900f, 790f, 2000f, paint)

            paint2.textSize = 75f
            Paint.Style.FILL
            drawText(str, 10f, 100f, paint2)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    var isInsideCenterCircle = (x - 530) * (x - 530) + (y - 1450) * (y - 1450) < 400 * 400
                    selectedCircle = circleList.firstOrNull { it.contains(x, y) }
                    // Check if any of the circles in circleList intersect with the selected circle
                    if (selectedCircle != null && circleList.any { circle -> circle intersects selectedCircle!! }) {
                        // Set touched flag to true and invalidate the view
                        touched = true
                        invalidate()
                        if(!isInsideCenterCircle){
                            Log.d("Touched", "Touched move at: $x, Y: $y")
                            for (i in 0..25) {
                                if (selectedCircle!! intersects circleList[i] && latestInteraction != circleList[i]) {
                                        str += alphabet[i]
                                        latestInteraction = selectedCircle
                                    Log.d("Touched", "Touched move at: $str")
                                }
                            }
                        }else{
                            latestInteraction = null
                        }
                    } else {
                        // Set touched flag to false and invalidate the view
                        touched = false
                        invalidate()
                    }
                    if(x > 200f && x < 20f && y > 1900f && y < 2000f){

                        str = ""
                        Log.d("clear", str)
                        invalidate()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (str != "") {
                        str += " "
                        latestInteraction = null
                    }

                }
                else -> return super.onTouchEvent(event)
            }


        }
        return true
    }
}