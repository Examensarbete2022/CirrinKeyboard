package com.example.examensarbete

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
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

            val clear = drawRect(200f, 1900f, 20f, 2000f, paint)

            paint.textSize = 75f
            Paint.Style.FILL
            canvas.drawText(str, 10f, 100f, paint)

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

/*fun swype(input: String): String {
    val inputLetters = input.toLowerCase().toCharArray()
    var result = ""

    // loop through each letter in the input
    for (i in 0 until inputLetters.size) {
        val currentLetter = inputLetters[i]
        val nextLetter = if (i + 1 < inputLetters.size) inputLetters[i + 1] else ' '

        // check if the current letter and the next letter are adjacent on the keyboard
        if (isAdjacent(currentLetter, nextLetter)) {
            // if they are, add the current letter to the result and skip the next letter
            result += currentLetter
            i++
        } else {
            // if they are not adjacent, add the current letter to the result
            result += currentLetter
        }
    }

    return result
}

fun isAdjacent(current: Char, next: Char): Boolean {
    // define the keyboard layout as a 2D array of characters
    val keyboard = arrayOf(
        charArrayOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
        charArrayOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        charArrayOf('z', 'x', 'c', 'v', 'b', 'n', 'm')
    )

    // find the current and next letters on the keyboard
    val currentPosition = keyboard.map { it.indexOf(current) }.filter { it >= 0 }.firstOrNull()
    val nextPosition = keyboard.map { it.indexOf(next) }.
        filter { it >= 0 }.firstOrNull()

    // if the current letter is not on the keyboard, return false
    if (currentPosition == null) {
        return false
    }


    */

/*class SwipeKeyboard {
    private val keyboard = CirrinKeyboard()

    // map of swipe directions to the corresponding key on the keyboard
    private val swipeMap = mapOf(
        SwipeDirection.UP to keyboard.keyUp,
        SwipeDirection.DOWN to keyboard.keyDown,
        SwipeDirection.LEFT to keyboard.keyLeft,
        SwipeDirection.RIGHT to keyboard.keyRight
    )

    // list of swipe input
    private val swipeInput = mutableListOf<SwipeDirection>()

    // max length of swipe input to consider
    private val maxSwipeLength = 4

    fun onSwipe(swipeDirection: SwipeDirection) {
        // add swipe direction to input list
        swipeInput.add(swipeDirection)

        // if swipe input is longer than max length, remove first item
        if (swipeInput.size > maxSwipeLength) {
            swipeInput.removeAt(0)
        }

        // get corresponding key for current swipe input
        val key = swipeMap[swipeInput]

        // if key is not null, simulate a press of the key
        key?.let {
            it.simulatePress()
        }
    }
}
*///}