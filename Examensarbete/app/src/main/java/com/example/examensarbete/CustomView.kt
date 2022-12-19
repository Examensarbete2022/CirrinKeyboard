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

    //create 26 circles that sits in a circle
    val circleList = mutableListOf<Circle>()
    //create list of all the letters in the alphabet
    var alphabet = arrayOf('x', 'p', 'j', 'v', 'z', 'c', 'g', 'w', 'm', 's', 'a', 't', 'h', 'e', 'r', 'o', 'n', 'i', 'f', 'l', 'd', 'u', 'k', 'b', 'y', 'q')



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


            /*if (touched) {
                drawColor(Color.BLUE)
            } else {
                drawColor(Color.WHITE)
            }*/
            //drawCircle(circle1, paint)
            //drawCircle(circle2, paint)
            circleList.forEach {
                drawCircle(it.x, it.y, it.radius, paint)
            }
            for (i in 0..25) {
                drawText(alphabet[i].toString(), circleList[i].x - 13, circleList[i].y + 10, paint)
                paint.textSize = 40f
            }

            drawCircle(550f, 1400f, 450f, paint)
            drawCircle(550f, 1400f, 350f, paint)

            paint.textSize = 75f
            canvas.drawText(str, 10f, 100f, paint)

        }
    }
    /*
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
                    when (selectedCircle) {
                        circle1 -> circle1.offsetTo(x, y)
                        else -> return true
                    }

                    if(circleList.any { circle -> circle1 intersects circle }) {
                        touched = true
                        invalidate()

                        for (i in 0..25) {
                            if (circle1 intersects circleList[i]) {
                                if (str.isEmpty()) {
                                    str += alphabet[i]
                                } else if (str.last() == alphabet[i]) {
                                    // Do nothing if the last character in the string is the same as the current character
                                } else {
                                    str += alphabet[i]
                                }
                                Log.d("Touched", "Touched move at: $str")
                            }
                        }
                    } else {
                        touched = false
                        invalidate()
                    }

                }
                MotionEvent.ACTION_UP -> {
                    //if str isnt  empty, add to dynamicTextView and after empty str
                    Log.d("Touched", "Touched up at: $str")
                    selectedCircle = null
                    return true

                    /*if (str != "") {
                        dynamicTextView.text = str
                        str = ""
                    }*/

                }
                else -> return super.onTouchEvent(event)
            }

        }
        return true
    }



     */


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("Touched", "Touched down at: $x, Y: $y")
                    // Find the circle that was touched
                    selectedCircle = circleList.firstOrNull { it.contains(x, y) }
                    return true
                }

                MotionEvent.ACTION_MOVE -> {
                    Log.d("Touched", "Touched move at: $x, Y: $y")
                    // Check if any of the circles in circleList intersect with the selected circle
                    if (selectedCircle != null && circleList.any { circle -> circle intersects selectedCircle!! }) {
                        // Set touched flag to true and invalidate the view
                        touched = true
                        invalidate()

                            for (i in 0 until circleList.size) {
                                if (selectedCircle!! intersects circleList[i]) {
                                    if (str.isEmpty()) {
                                        str += alphabet[i]
                                    } else if (str.last() == alphabet[i]) {
                                        // Do nothing if the last character in the string is the same as the current character
                                    } else {
                                        str += alphabet[i]
                                    }
                                    Log.d("Touched", "Touched move at: $str")
                            }
                        }

                    } else {
                        // Set touched flag to false and invalidate the view
                        touched = false
                        invalidate()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    selectedCircle = null
                    return true
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
*/