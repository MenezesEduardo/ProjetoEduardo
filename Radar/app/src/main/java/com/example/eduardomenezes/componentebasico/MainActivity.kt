package com.example.eduardomenezes.componentebasico

import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View


class MainActivity : AppCompatActivity() {

    var graus : Int = 0
    var grausantigo : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout_radar =  findViewById(R.id.tela) as ConstraintLayout
        val background = Componente(this)

        layout_radar.addView(background)
    }

    class Componente (context: Context): View(context) {

        var widthCanvas = 0
        var heightCanvas = 0
        var angulo: Int = 1
        var reverse: Boolean = false
        val paint: Paint = Paint()

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            heightCanvas = getHeight()
            widthCanvas = getWidth()

            // desenhar arco preto de fundo
            paint.color = Color.BLACK
            paint.strokeWidth = 5f
            paint.style = Paint.Style.FILL_AND_STROKE
            val arcoFundo = RectF(((widthCanvas / 2) - 450).toFloat(),
                    ((heightCanvas / 2) - 450).toFloat(),
                    ((widthCanvas / 2) + 450).toFloat(),
                    ((heightCanvas / 2) + 450).toFloat())
            canvas.drawArc(arcoFundo, 180f, 180f, true, paint)
            //-----------------

            //Desenhar arcos verdes horizontais
            paint.color = Color.GREEN
            paint.strokeWidth = 2f
            paint.style = Paint.Style.STROKE
            for (f in 0..10) {
                val arcosHorizontais = RectF(((widthCanvas / 2) - (f * 45)).toFloat(),
                        ((heightCanvas / 2) - (f * 45)).toFloat(),
                        ((widthCanvas / 2) + (f * 45)).toFloat(),
                        ((heightCanvas / 2) + (f * 45)).toFloat())
                canvas.drawArc(arcosHorizontais, 180f, 180f, true, paint)
            }
            //------------------------

            //Desenhar linhas horizontais nos angulos demarcados
            var angulo : IntArray = intArrayOf(30,60,90,120,150)
            for (f in 0..4){
                var angle : Double = Math.PI * angulo[f] / 180 - Math.PI / 2
                canvas.drawLine((widthCanvas / 2).toFloat(),
                        (heightCanvas / 2).toFloat(),
                        ((widthCanvas / 2) - Math.sin(angle) * 450).toFloat(),
                        ((heightCanvas / 2) - Math.cos(angle) * 450).toFloat(), paint)
            }
            //---------------------------
            moverPonteiro(canvas)
            postInvalidateDelayed(5)
            invalidate()
        }

        fun moverPonteiro(canvas: Canvas){
            //Define que o angulo do ponteiro vá de 0 a 180 e depois volte a 0
            if (angulo == 1){
                reverse =  false
            }
            if(angulo >= 1 && angulo !=180 && reverse == false){
                angulo = angulo +1
            }
            if(angulo == 180){
                reverse = true
            }
            if(angulo <= 180 && angulo !=1 && reverse == true){
                angulo = angulo -1
            }
            desenhoPonteiro(canvas, angulo)
        }

        fun desenhoPonteiro(canvas: Canvas, angulo: Int){
            var angle : Double = Math.PI * angulo / 180 - Math.PI / 2
            paint.color = Color.GREEN
            paint.setStyle(Paint.Style.STROKE)
            paint.strokeWidth = 8.0f

            canvas.drawLine((widthCanvas/2).toFloat(),
                    (heightCanvas / 2).toFloat(),
                    (widthCanvas / 2 + Math.sin(angle) * 450).toFloat(),
                    (heightCanvas/2 - Math.cos(angle) * 450).toFloat(),
                    paint)
        }
    }
}
