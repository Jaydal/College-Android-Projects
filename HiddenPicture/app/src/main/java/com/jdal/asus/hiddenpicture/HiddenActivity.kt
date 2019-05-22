package com.jdal.asus.hiddenpicture

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_hidden.*
import java.util.Random;

class HiddenActivity : AppCompatActivity() {
    val ani = arrayOf(R.drawable.antelope,R.drawable.bear,R.drawable.dog,R.drawable.elephant,R.drawable.flamingo,R.drawable.giraffe,R.drawable.lion,R.drawable.monkey,R.drawable.owl,R.drawable.zebra)
    val fru=arrayOf(R.drawable.apple,R.drawable.banana,R.drawable.coconut,R.drawable.grapes,R.drawable.guava,R.drawable.mango,R.drawable.orange,R.drawable.pineapple,R.drawable.strawberry,R.drawable.watermelon)
    val spo=arrayOf(R.drawable.badminton,R.drawable.baseball,R.drawable.basketball,R.drawable.billiard,R.drawable.bowling,R.drawable.chess,R.drawable.golf,R.drawable.lawntennis,R.drawable.skateboarding,R.drawable.soccer)
    var cat=0
    var id=0
    var count=0
    var num= ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hidden)
        var rn=(0..9).random()
        num.add(rn)
       cat=intent.getIntExtra("cat", 0)
       setImage(rn)
        btnSubmit.setOnClickListener {
            if(etGuess.text.toString().toLowerCase()==resources.getResourceEntryName(id).toString().toLowerCase()){
                Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Incorrect!",Toast.LENGTH_SHORT).show()
            }
        }
        btnNext.setOnClickListener {
            count++
            rn=(0..9).random()
            num.add(rn)
            for(i in 1..20){
                if(num.contains(rn)){
                    rn=(0..9).random()
                    num.add(rn)
                }
            }
            if(count<5){
                setImage(rn)
            }else{
                Toast.makeText(this,"No More!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun IntRange.random()=Random().nextInt((endInclusive+1)-start)+start
    private fun setImage(i:Int){
        id = when (cat) {
            0 -> {
                imageView.setImageResource(ani[i])
                ani[i]
            }
            1 -> {
                imageView.setImageResource(fru[i])
                fru[i]
            }
            else -> {
                imageView.setImageResource(spo[i])
                spo[i]
            }
        }
        var hint=""
        for(char in resources.getResourceEntryName(id).toString()){
            hint += " _"
        }
        txtHint.text= hint
    }
}
