package com.jdal.asus.superhero

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_super.*

val arrayList = ArrayList<String>()

class SuperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super)

        arrayList.addAll(resources.getStringArray(R.array.sh))

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        listSuper.adapter = arrayAdapter

        listSuper.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SHActivity::class.java)
            tvSuper.text = listSuper.getItemAtPosition(position).toString()
            intent.putExtra("pos", position)
            startActivity(intent)
        }
    }

}
