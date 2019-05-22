package junedelmar.smu.nv.ph.gametracker

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
var mycolor=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_02:Button=findViewById(R.id.btn_02)
        val btn_03:Button=findViewById(R.id.btn_03)
        val btn_01:Button=findViewById(R.id.btn_01)
        val msg:TextView=findViewById(R.id.lbl_message)
        val container_parent=findViewById<ConstraintLayout>(R.id.container_parent)
        btn_02.setOnClickListener(this)
        btn_03.setOnClickListener(this)
        btn_01.setOnClickListener(this)
    }

    override fun onClick(v:View){
        mycolor = when (v.id){
            R.id.btn_03->{
               lbl_message.text="Hello Button 3!"
               R.color.color1
            }
            R.id.btn_02->{
                lbl_message.text="Hello Button 2!"
                R.color.color2
            }
            R.id.btn_01->{
                lbl_message.text="Hello Button 1!"
                R.color.color3
            }
            else -> {
                R.color.color4
            }
        }
        container_parent.setBackgroundResource(mycolor)
    }

}
