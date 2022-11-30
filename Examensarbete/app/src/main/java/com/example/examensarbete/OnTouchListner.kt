package com.example.examensarbete

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class OnTouchListner : MainActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myLayout.setOnTouchListener{view, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    val x = motionEvent.x
                    val y = motionEvent.y
                    val button = Button(this)
                    button.text = "Button"
                    button.x = x
                    button.y = y
                    myLayout.addView(button)
                }
            }
            true
        }

        /*val array = arrayOf<String>()

        d.findViewById<Button>(R.id.d).setOnTouchListener { v: View, m: MotionEvent ->
            print(array)
            true
        }
        u.findViewById<Button>(R.id.u).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        k.findViewById<Button>(R.id.k).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        b.findViewById<Button>(R.id.b).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        y.findViewById<Button>(R.id.y).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        q.findViewById<Button>(R.id.q).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        x.findViewById<Button>(R.id.x).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        p.findViewById<Button>(R.id.p).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        j.findViewById<Button>(R.id.j).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        v.findViewById<Button>(R.id.v).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        z.findViewById<Button>(R.id.z).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        c.findViewById<Button>(R.id.c).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        g.findViewById<Button>(R.id.g).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        w.findViewById<Button>(R.id.w).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        m.findViewById<Button>(R.id.m).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        s.findViewById<Button>(R.id.s).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        a.findViewById<Button>(R.id.a).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        t.findViewById<Button>(R.id.t).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        h.findViewById<Button>(R.id.h).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        e.findViewById<Button>(R.id.e).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        r.findViewById<Button>(R.id.r).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        o.findViewById<Button>(R.id.o).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        n.findViewById<Button>(R.id.n).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        i.findViewById<Button>(R.id.i).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        f.findViewById<Button>(R.id.f).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }
        l.findViewById<Button>(R.id.l).setOnTouchListener { v: View, m: MotionEvent ->
            true
        }*/
    }
}

/*val btnArray =
            arrayOf(d, u, k, b, y, q, x, p, j, v, z, c, g, w, m, s, a, t, h, e, r, o, n, i, f, l)*/
/*R.layout.activity_main.setOnClickListener{ v: View, m: MotionEvent ->
            val pointerCount = m.pointerCount
            val pointerId = m.getPointerId(0)
            true

        }*/