package com.jdal.jdal.myfirstandroidproject

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jdal.jdal.myfirstandroidproject.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnComp.setOnClickListener {
            //            (it as Button).text = "PRESSED"
//            etFirstNumber.text
//            var calc=Calculator();
//            val total = etFirstNumber.text.toString().toInt() + etSecondNumber.text.toString().toInt()
//            tvResult.text = calc.add
        }
    }
}

class  Calculator{
    fun add(n1:Int,n2:Int):Int{
        return n1+n2
    }
}