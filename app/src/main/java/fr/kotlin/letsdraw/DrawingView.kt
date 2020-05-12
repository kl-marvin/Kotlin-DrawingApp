package fr.kotlin.letsdraw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.round

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs){

    private var drawPath : CustomPath? = null
    private var danvasBitmap: Bitmap? = null
    private var drawPaint : Paint? = null
    private var canvasPaint: Paint? = null
    private var brunshSize: Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas: Canvas? = null


    init {

    }

    private fun setUpDrawing(){
        drawPaint = Paint()
        drawPath = CustomPath(color, brunshSize)
        drawPaint!!.color = color
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND   // les extremintés des lignes
        drawPaint!!.strokeCap = Paint.Cap.ROUND // fin de ligne
        canvasPaint = Paint(Paint.DITHER_FLAG) // copy bits from one part of computer's graphical mem to anither
        brunshSize = 20.toFloat()
    }

    // variables accessible depuis DrawningView
    internal inner class CustomPath(var color: Int, var brushThickness: Float) : Path() {



    }


}


