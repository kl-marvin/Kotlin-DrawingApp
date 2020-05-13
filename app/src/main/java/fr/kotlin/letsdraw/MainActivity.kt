package fr.kotlin.letsdraw

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drawing_view.setSizeforBrunsh(20.toFloat())

        iBtn_brush.setOnClickListener {
            showBrushSizeDialog()
        }



    }

    private fun showBrushSizeDialog(){

        val dialog = Dialog(this) // context in main activy
        dialog.setContentView(R.layout.dialog_brush_size)
        dialog.setTitle("Taille du pinceau :")

        val smallBtn = dialog.iBtn_small_brush
        val mediumBtn = dialog.iBtn_medium_brush
        val largeBtn = dialog.iBtn_large_brush

        smallBtn.setOnClickListener{
            drawing_view.setSizeforBrunsh(5.toFloat())
            dialog.dismiss()
        }
        mediumBtn.setOnClickListener{
            drawing_view.setSizeforBrunsh(15.toFloat())
            dialog.dismiss()
        }
        largeBtn.setOnClickListener{
            drawing_view.setSizeforBrunsh(25.toFloat())
            dialog.dismiss()
        }

        dialog.show()
    }

}
