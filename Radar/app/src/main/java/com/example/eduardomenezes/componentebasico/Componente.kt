package com.example.eduardomenezes.componentebasico

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import java.util.*

class Componente:View {

    var widthLine = 0
    var heightLine = 0
    var numericalSpacing = 0
    var padding = 0
    var handTruncation = 0
    var radius = 0
    var started : Boolean = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = getWidth()
        val hieght = getHeight()
        val paint: Paint = Paint()
        val path = Path()

        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        paint.style = Paint.Style.FILL_AND_STROKE

        val paint2: Paint = Paint()

        paint2.color = Color.GREEN
        paint2.strokeWidth = 2f
        paint2.style = Paint.Style.STROKE

        val arcOval = RectF(((width / 2) - 314).toFloat(), ((hieght / 2) - 314).toFloat(), ((width / 2) + 314).toFloat(), ((hieght / 2) + 314).toFloat())
        canvas.drawArc(arcOval, 180f, 180f, true, paint)

        for (f in 0..9) {
            val arcOval = RectF(((width / 2) - (f * 35)).toFloat(), ((hieght / 2) - (f * 35)).toFloat(), ((width / 2) + (f * 35)).toFloat(), ((hieght / 2) + (f * 35)).toFloat())
            canvas.drawArc(arcOval, 180f, 180f, true, paint2)
        }

        paint.color = Color.GREEN
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE

//--------------------------linhas do radar
//radar com muitas linhas
        /*
        var coordenadaX : IntArray = intArrayOf(314,314,305,295,275,255,235,215,185,155,120,80,40,0)
        var coordenadaY : IntArray = intArrayOf(0,40,80,120,155,185,215,235,255,275,295,305,314,314)

        for (f in 0..13){
            canvas.drawLine((width / 2).toFloat(), (hieght / 2).toFloat(), ((width / 2) - coordenadaX[f]).toFloat(), ((hieght / 2) - coordenadaY[f]).toFloat(), paint)
        }
        for (f in 0..13){
            canvas.drawLine((width / 2).toFloat(), (hieght / 2).toFloat(), ((width / 2) + coordenadaX[f]).toFloat(), ((hieght / 2) - coordenadaY[f]).toFloat(), paint)
        }
*/
        var coordenadaX : IntArray = intArrayOf(305,275,235,185,120,40)
        var coordenadaY : IntArray = intArrayOf(80,155,215,255,295,314)

        for (f in 0..5){
            canvas.drawLine((width / 2).toFloat(), (hieght / 2).toFloat(), ((width / 2) - coordenadaX[f]).toFloat(), ((hieght / 2) - coordenadaY[f]).toFloat(), paint)
        }
        for (f in 0..5){
            canvas.drawLine((width / 2).toFloat(), (hieght / 2).toFloat(), ((width / 2) + coordenadaX[f]).toFloat(), ((hieght / 2) - coordenadaY[f]).toFloat(), paint)
        }

//------------------------
/*
        path.moveTo((width / 2).toFloat(), (hieght / 2).toFloat())
        for (f in 0..180){

            path.lineTo((width / 2).toFloat(), Math.sin(Math.toRadians(f.toDouble())).toFloat())
            canvas.drawPath(path, paint)

        }*/


    }
    fun drawLineToMove( canvas: Canvas){
        var calendar: Calendar = Calendar.getInstance()
        moveLine(canvas, calendar.get(Calendar.SECOND)*6)
    }


    fun moveLine(canvas: Canvas, loc: Int){
        var angle : Double = Math.PI * loc / 30 - Math.PI / 2

        var handRadius = radius - handTruncation

        val brush2 = Paint()
        brush2.setARGB(255,0,0,0)
        brush2.setStyle(Paint.Style.STROKE)
        brush2.strokeWidth = 8.0f

        canvas.drawLine((widthLine/2).toFloat(),(heightLine / 2).toFloat(),
                (widthLine / 2 + Math.cos(angle) * handRadius).toFloat(),
                (heightLine/2 + Math.sin(angle) * handRadius).toFloat(),
                brush2)



    }

    fun startRadar(){
        heightLine = getHeight()
        widthLine = getWidth()
        padding =  numericalSpacing + 50
        var min = Math.min(heightLine, widthLine)
        radius = min /2 - padding
        handTruncation = min / 20
        started = true
    }

}