package com.example.examensarbete

import android.app.Activity
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.core.app.NotificationCompat.getAction
import androidx.core.hardware.display.DisplayManagerCompat
import androidx.core.view.accessibility.AccessibilityEventCompat.getAction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.swipe.view.*
import kotlin.math.log


open class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CustomView(this))


    }
}
