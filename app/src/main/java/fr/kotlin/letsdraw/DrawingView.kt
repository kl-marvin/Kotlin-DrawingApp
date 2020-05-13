package fr.kotlin.letsdraw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.round


// view occupies a rectangular area in the screen and is responsible for drawing and event handling
class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs){

    private var mDrawPath : CustomPath? = null // traits
    private var mCanvasBitmap: Bitmap? = null // holds the pixels
    private var mDrawPaint : Paint? = null
    private var mCanvasPaint: Paint? = null
    private var mBrunshSize: Float = 0.toFloat()
    private var color = Color.BLACK
    private var mPaths = ArrayList<CustomPath>()



    /**

     *
     *The Canvas class holds the "draw" calls.
     * To draw something, 4 basic components are needed:
     * A Bitmap to hold the pixels,
     * a Canvas to host the draw calls (writing into the bitmap),
     * a drawing primitive (e.g. Rect, Path, text, Bitmap),
     * and a paint (to describe the colors and styles for the drawing)
     */
    private var canvas: Canvas? = null  // holds the draw calls



    init {
    setUpDrawing()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // setting up bitmap height width and color options
        mCanvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(mCanvasBitmap!!)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for(path in mPaths){ // on parcours le tableau de traits
            // on applique le "theme"
            mDrawPaint!!.strokeWidth = path.brushThickness
            mDrawPaint!!.color = path.color
            // on le dessine
            canvas.drawPath(path, mDrawPaint!!);
        }

        canvas.drawBitmap(mCanvasBitmap!!, 0f, 0f, mCanvasPaint)// top left corner & and style defined

        if(!mDrawPath!!.isEmpty){
            mDrawPaint!!.strokeWidth = mDrawPath!!.brushThickness
            mDrawPaint!!.color = mDrawPaint!!.color
            canvas.drawPath(mDrawPath!!, mDrawPaint!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val touchX = event?.x
        val touchY = event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                mDrawPath!!.color = color
                mDrawPath!!.brushThickness = mBrunshSize

                mDrawPath!!.reset()
                mDrawPath!!.moveTo(touchX!!, touchY!!)
            }
            MotionEvent.ACTION_MOVE -> {
                mDrawPath!!.lineTo(touchX!!, touchY!!)
            }
            MotionEvent.ACTION_UP -> {
                mDrawPath = CustomPath(color, mBrunshSize)
                mPaths.add(mDrawPath!!)//ajoute le trait pour le persister sur l'écran
            }
            else -> return false
        }
        invalidate()// Invalidate the whole view
        return true

    }

   // initializes the attributes
    private fun setUpDrawing(){
        mDrawPaint = Paint()
        mDrawPath = CustomPath(color, mBrunshSize)
        mDrawPaint!!.color = color
        mDrawPaint!!.style = Paint.Style.STROKE
        mDrawPaint!!.strokeJoin = Paint.Join.ROUND   // les extremintés des lignes
        mDrawPaint!!.strokeCap = Paint.Cap.ROUND // fin de ligne
        mCanvasPaint = Paint(Paint.DITHER_FLAG) // copy bits from one part of computer's graphical mem to anither
        mBrunshSize = 20.toFloat()
    }

    // variables accessible depuis DrawningView
    internal inner class CustomPath(var color: Int, var brushThickness: Float) : Path() {



    }


}


