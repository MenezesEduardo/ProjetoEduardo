package com.example.eduardomenezes.componentebasico

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Componente: View {

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

        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE

        val paint2: Paint = Paint()

        paint2.color = Color.BLACK
        paint2.strokeWidth = 2f
        paint2.style = Paint.Style.FILL_AND_STROKE

        //rosto
        canvas.drawCircle((width / 2).toFloat(), (hieght / 2).toFloat(), 250f, paint)

        //olho esquerdo
        val arcOval1 = RectF (((width / 2)-150).toFloat(), ((hieght / 2)-150).toFloat(), ((width / 2)-50).toFloat(), ((hieght / 2)-80).toFloat())
        canvas.drawArc(arcOval1, 180f, 180f, false, paint)
        canvas.drawCircle(((width / 2)-100).toFloat(), ((hieght / 2)-75).toFloat(), 40f, paint)
        canvas.drawCircle(((width / 2)-100).toFloat(), ((hieght / 2)-65).toFloat(), 25f, paint2)

        //olho direito

        val arcOva2 = RectF (((width / 2)+50).toFloat(), ((hieght / 2)-150).toFloat(), ((width / 2)+150).toFloat(), ((hieght / 2)-80).toFloat())
        canvas.drawArc(arcOva2, 180f, 180f, false, paint)
        canvas.drawCircle(((width / 2)+100).toFloat(), ((hieght / 2)-75).toFloat(), 40f, paint)
        canvas.drawCircle(((width / 2)+100).toFloat(), ((hieght / 2)-65).toFloat(), 25f, paint2)
        //boca

        val arcOval = RectF (((width / 2)-125).toFloat(), ((hieght / 2)-50).toFloat(), ((width / 2)+125).toFloat(), ((hieght / 2)+150).toFloat())
        canvas.drawArc(arcOval, 180f, -180f, false, paint)
    }
}