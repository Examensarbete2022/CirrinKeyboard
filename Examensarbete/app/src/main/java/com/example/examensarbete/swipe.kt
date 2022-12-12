package com.example.examensarbete

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.swipe.*



class swipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.swipe)

        var str: String = ""
        var x = 0
        var y = 0

        swipe.setOnTouchListener(object : View.OnTouchListener {
             override fun onTouch(v: View, event: MotionEvent?): Boolean {
                when(event?.actionMasked){
                    MotionEvent.ACTION_DOWN -> {
                        x = event.x.toInt()
                        y = event.y.toInt()
                        Log.d("Touch", "X: $x, Y: $y")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val x2 = event.x.toInt()
                        val y2 = event.y.toInt()
                        Log.d("Touch", "X: $x2, Y: $y2")
                        if (x2 > x) {
                            str = "Right"
                        } else if (x2 < x) {
                            str = "Left"
                        } else if (y2 > y) {
                            str = "Down"
                        } else if (y2 < y) {
                            str = "Up"
                        }
                        Log.d("Touch", str)
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.d("Touch", "Up")
                    }
                }
                return true
            }
       })


    }
}
