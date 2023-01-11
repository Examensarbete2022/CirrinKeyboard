package com.example.examensarbete

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class CustomView(context: Context) : View(context) {

    var touched = false
    private var selectedCircle: Circle? = null
    private var latestInteraction : Circle? = null

    //create 26 circles that sits in a circle
    val circleList = mutableListOf<Circle>()
    //create list of all the letters in the alphabet
    var alphabet = arrayOf('x', 'p', 'j', 'v', 'z', 'c', 'g', 'w', 'm', 's', 'a', 't', 'h', 'e', 'r', 'o', 'n', 'i', 'f', 'l', 'd', 'u', 'k', 'b', 'y', 'q')
    // string holding the letters that the user has selected
    var str = ""

    init {
        for (i in 0..25) {
            val angle = i * 2 * Math.PI / 26
            val x = 450 + 450 * cos(angle)
            val y = 450 + 450 * sin(angle)
            circleList.add(Circle(x.toFloat() + 80, y.toFloat() + 1000, 50f))
        }
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val inputs = mutableListOf<String>()
    private var isButtonClicked = false
    var isDeleteLatestClicked = false
    private val backSpaceRect = Rect()
    private val buttonRect = Rect()

    // get screen with and height
    val screenWidth = resources.displayMetrics.widthPixels
    val screenHeight = resources.displayMetrics.heightPixels

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply{
            circleList.forEach {
                drawCircle(it.x, it.y, it.radius, paint)
            }
            paint.textSize = 40f
            for (i in 0..25) {
                drawText(alphabet[i].toString(), circleList[i].x - 13, circleList[i].y + 10, paint)
            }

            drawCircle(530f, 1450f, 500f, paint)
            drawCircle(530f, 1450f, 400f, paint)
            Paint.Style.FILL

            if(isButtonClicked){
                str = ""
                isButtonClicked = false
            }

            if(isDeleteLatestClicked){
                if(inputs.isNotEmpty()){
                    inputs.removeAt(inputs.size-1)
                    str = inputs.joinToString(separator = "")
                }
                isDeleteLatestClicked = false
            }


            paint.textSize = 75f
            Paint.Style.FILL
            canvas.drawText(str, 10f, 100f, paint)

            //clear button width and height
            val buttonWidth = 250
            val buttonHeight = 100

            //set the coordinates for the clear button and draw rect
            val clearBottomPadding = 200
            val clearBtnLeft = 30
            val clearBtnTop = screenHeight - buttonHeight - 200
            val clearBtnBottom = screenHeight - clearBottomPadding
            buttonRect.set(clearBtnLeft, clearBtnTop, buttonWidth, clearBtnBottom)
            canvas.drawRect(buttonRect, paint)

            // Clear Button Paint
            paint.color = Color.BLACK
            paint.textSize = 40f
            val text = "Clear"
            val textWidth = paint.measureText(text)
            val x = buttonRect.centerX() - textWidth / 2
            val y = buttonRect.centerY() + ((paint.descent() + paint.ascent()) / 2) + 25
            canvas.drawText(text, x, y, paint)

            //backspace button width and height
            val latestWidth = 250
            val latestHeight = 100
            //set the coordinates for the backspace button and draw rect
            val backSpaceBottomPadding = 200
            val backSpaceLeft = screenWidth - latestWidth
            val backSpaceTop = screenHeight - latestHeight - backSpaceBottomPadding
            val backSpaceRight = screenWidth - 30
            val backSpaceBottom = screenHeight - backSpaceBottomPadding
            backSpaceRect.set(backSpaceLeft, backSpaceTop, backSpaceRight, backSpaceBottom)
            canvas.drawRect(backSpaceRect, paint)

            //Backspace Button Paint
            paint.color = Color.BLACK
            paint.textSize = 40f
            val latestTextInput = "Backspace"
            val latestTextWidth = paint.measureText(latestTextInput)
            val latestX = backSpaceRect.centerX() - latestTextWidth / 2
            val latestY = backSpaceRect.centerY() + ((paint.descent() + paint.ascent()) / 2) + 25
            canvas.drawText(latestTextInput,latestX, latestY, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.apply {
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    val x = event.x
                    val y = event.y
                    if (buttonRect.contains(x.toInt(), y.toInt())) {
                        isButtonClicked = true
                        invalidate()
                    }
                    else if (backSpaceRect.contains(x.toInt(), y.toInt())) {
                        isDeleteLatestClicked = true
                        invalidate()
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    val isInsideCenterCircle = (x - 530) * (x - 530) + (y - 1450) * (y - 1450) < 400 * 400
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
                                        inputs.add(alphabet[i].toString())
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

