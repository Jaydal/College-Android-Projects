package com.jdal.asus.superhero

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sh.*

class SHActivity : AppCompatActivity() {
    val img = arrayOf(R.drawable.super_gif, R.drawable.bat_gif, R.drawable.green_gif,
            R.drawable.wwman_gif, R.drawable.bwidow_gif, R.drawable.flash_gif, R.drawable.spider_gif, R.drawable.iron_gif, R.drawable.dr_gif, R.drawable.hulk_gif)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sh)

        val pos = intent.getIntExtra("pos", 0)
        gifSH.setImageResource(img[pos])
        val arr = resources.getStringArray(R.array.sh)
        tvHeroname.text = arr[pos]
    }
}
