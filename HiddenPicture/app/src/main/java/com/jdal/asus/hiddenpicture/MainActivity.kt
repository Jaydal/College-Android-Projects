package com.jdal.asus.hiddenpicture

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val arrayList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrayList.addAll(resources.getStringArray(R.array.category))

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, HiddenActivity::class.java)
            intent.putExtra("cat", position)
            startActivity(intent)


        }
    }
}
