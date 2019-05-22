package com.jdal.asus.criminalintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_crime.*

class CrimeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)
        val fm:FragmentManager=supportFragmentManager
        var fragment: Fragment? = fm.findFragmentById(R.id.fragmentContainer)
        if(fragment==null){
            fragment=CrimeFragment()
            fm.beginTransaction()
                    .add(R.id.fragmentContainer,fragment)
                    .commit()
        }

    }
}
