package junedelmar.smu.nv.ph.takenotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_notes_view.*

class NotesView : AppCompatActivity(), View.OnClickListener {

    var idHolder=0
    private val crud = NotesCrud(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_view)
        val items= arrayOf("Unfinished","Finished")
        val aa=ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        status_spinner.adapter=aa
        val id=intent.getIntExtra("id",0)
        if(id>0){
            idHolder=id
            getData(id)
        }
        btnSave.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        btnClear.setOnClickListener(this)
    }
    override fun onClick(v:View){
        when (v.id) {
            R.id.btnSave -> {
                saveNote()
            }
            R.id.btnClear->{
                Reset()
            }
            btnDelete.id-> {
                if (crud.Delete(idHolder)) {
                    idHolder = 0
                    Reset()
                    Toast.makeText(this, "DELETED!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Not DELETED!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun saveNote(){
        if(etContent.text.toString().isNotEmpty() && etTitle.text.toString().isNotEmpty()){
            val nt=Notes()
            nt.title=etTitle.text.toString()
            nt.content=etContent.text.toString()
            nt.date=etDate.text.toString()
            if(idHolder>0){
                nt.noteID=idHolder
                crud.Update(nt)
            }else{
                crud.Insert(nt)
                Reset()
            }

        }
    }
    private fun Reset(){
        etTitle.setText("")
        etDate.setText("")
        etContent.setText("")
        idHolder=0
    }
    private fun getData(id:Int){
        val list=crud._getList(id)
        etTitle.setText(list[0]["title"])
        etContent.setText(list[0]["content"])
        etDate.setText(list[0]["date"])
//        if(list[0]["status"].toString()=="Unfinished"){
//            status_spinner.setSelection(0)
//        }else{
//            status_spinner.setSelection(1)
//        }
    }
}
