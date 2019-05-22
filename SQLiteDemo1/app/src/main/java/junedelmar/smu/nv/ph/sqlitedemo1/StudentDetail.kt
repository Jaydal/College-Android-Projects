package junedelmar.smu.nv.ph.sqlitedemo1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_student_detail.*

class StudentDetail : AppCompatActivity(), View.OnClickListener {
    val crud = StudentCRUD(this)
    var idHolder=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)
        btnSave.setOnClickListener(this)
        btnClose.setOnClickListener(this)
        btnDelele.setOnClickListener(this)
        val id=intent.getIntExtra("id",0)
        if(id>0){
            GetData(id)
        }
        findViewById<ConstraintLayout>(R.id.container_inhe).setBackgroundResource(R.color.bgColor)
    }
    override fun onClick(v: View){
        when (v.id){
            btnSave.id->{
               saveMe()
            }
            btnClose.id->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            btnDelele.id->{
                if(crud.Delete(idHolder)){
                    idHolder=0
                    Reset()
                    Toast.makeText(this, "DELETED!",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
    private fun saveMe(){
        if(etName.text.toString()!="" && etEmail.text.toString()!="" &&
            etAge.text.toString()!=""){
            val st=Student()
            st.name=etName.text.toString()
            st.email=etEmail.text.toString()
            st.age=(etAge.text.toString()).toInt()
            st.studentID=idHolder
            if(idHolder>0){
                crud.Update(st)
                GetData(idHolder)
            }else{
                crud.Insert(st)
                Reset()
            }
            Toast.makeText(this, "SAVED!",Toast.LENGTH_SHORT).show()
        }
    }
    private fun GetData(id:Int){
        val list=crud._getList(id)
        etName.setText(list[0]["name"])
        etEmail.setText(list[0]["email"])
        etAge.setText(list[0]["age"])
        idHolder= (list[0]["id"])!!.toInt()
    }
    private fun Reset(){
        etName.setText("")
        etEmail.setText("")
        etAge.setText("")
    }
}
