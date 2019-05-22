package junedelmar.smu.nv.ph.sqlitedemo1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity(),View.OnClickListener {
    var arrList=ArrayList<HashMap<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent.setClass(this, StudentDetail::class.java)
        btnAdd.setOnClickListener(this)
        btnGetAll.setOnClickListener(this)
        lvStudent.setOnItemClickListener { parent, view, position, id ->
            intent.putExtra("id", (arrList[position]["id"])!!.toInt())
            Toast.makeText(this, arrList[position]["name"], Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        refreshMe()
        findViewById<ConstraintLayout>(R.id.container_parent).setBackgroundResource(R.color.bgColor)
    }
    override fun onClick(v: View){
        when (v.id){
            btnAdd.id->{
                intent.putExtra("id", 0)
                startActivity(intent)
            }
            btnGetAll.id->{
                refreshMe()
            }
        }
    }
    private fun refreshMe(){
        val crud=StudentCRUD(this)
        if(!crud.list.isEmpty()){
            arrList=crud.list
            val adapt=SimpleAdapter(this,crud.list,R.layout.view_student_entry,arrayOf("id","name"), intArrayOf(R.id.tvtStudentID,R.id.tvStudentName))
            lvStudent.adapter = adapt
        }else{
                    Toast.makeText(this,"NO DATA !", Toast.LENGTH_SHORT).show()
        }
    }

}
