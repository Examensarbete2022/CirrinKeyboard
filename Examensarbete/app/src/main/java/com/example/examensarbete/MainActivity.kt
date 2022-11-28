package com.example.examensarbete

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat.getAction
import androidx.core.hardware.display.DisplayManagerCompat
import androidx.core.view.accessibility.AccessibilityEventCompat.getAction
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap = Bitmap.createBitmap(900, 1800, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // canvas background color
        canvas.drawARGB(1, 255, 255, 255)

        val paint = Paint()
        paint.color = Color.parseColor("#000000")
        paint.strokeWidth = 10F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.isDither = true

        // get device dimensions
        screenValue()

        val rect = RectF(50f, 900f, 850f, 1700f)

        val degree = 13.84f
        var currentAngle = 0f
        for (i in 0 until 26) {
            if (i % 2 == 0)
                paint.color = Color.BLACK
            canvas.drawArc(rect, currentAngle, degree, true, paint)
            currentAngle += degree
        }
        var shapeDrawable: ShapeDrawable

        var left = 150
        var top = 1000
        var right = 750
        var bottom = 1600

        shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.setBounds(left, top, right, bottom)
        shapeDrawable.paint.color = Color.parseColor("#000000")
        shapeDrawable.draw(canvas)

        left = 160
        top = 1010
        right = 740
        bottom = 1590

        shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.setBounds(left, top, right, bottom)
        shapeDrawable.paint.color = Color.parseColor("#FFFFFF")
        shapeDrawable.draw(canvas)

        imageV.background = BitmapDrawable(resources, bitmap)

        val btnArray =
            arrayOf(d, u, k, b, y, q, x, p, j, v, z, c, g, w, m, s, a, t, h, e, r, o, n, i, f, l)


        /*button.setOnTouchListener { view, motionEvent ->
            // Controlling the button color.
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                button.setTextColor(0xff000ff)
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                button.setTextColor(0x00000000)
            }
            return@setOnTouchListener false;
        }*/
    }

    private fun screenValue() {

        val defaultDisplay =
            DisplayManagerCompat.getInstance(this).getDisplay(Display.DEFAULT_DISPLAY)
        val displayContext = createDisplayContext(defaultDisplay!!)

        val width = displayContext.resources.displayMetrics.widthPixels
        val height = displayContext.resources.displayMetrics.heightPixels

        Log.e("hello", "width (ANDROID R/ABOVE): $width")
        Log.e("hello", "height (ANDROID R/ABOVE) : $height")

    }
}
