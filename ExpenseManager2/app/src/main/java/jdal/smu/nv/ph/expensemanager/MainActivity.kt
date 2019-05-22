package jdal.smu.nv.ph.expensemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
val crud=ExpenseCrud(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNEw.setOnClickListener(this)
        btnSave.setOnClickListener(this)
        btnEx.setOnClickListener(this)
        btnRes.setOnClickListener(this)
        btnYes.setOnClickListener(this)
        btnNo.setOnClickListener(this)
        btnNo.visibility=View.INVISIBLE
        btnYes.visibility=View.INVISIBLE
        intent.setClass(this, ExpenseActivity::class.java)
    }
    override fun onClick(v: View) {
        when (v.id) {
            btnNEw.id -> {
                clear()
                Toast.makeText(this, "NEW!", Toast.LENGTH_SHORT).show()
            }
            btnSave.id -> {
                save()
                Toast.makeText(this, "SAVED!",Toast.LENGTH_SHORT).show()
                clear()
            }
            btnEx.id -> {
                startActivity(intent)
            }
            btnRes.id -> {
                Toast.makeText(this, "ARE YOU SURE??",Toast.LENGTH_LONG).show()
                btnNo.visibility= VISIBLE
                btnYes.visibility= VISIBLE
                btnRes.visibility=View.INVISIBLE
            }
            btnYes.id->{
                crud.DeleteAll()
                Toast.makeText(this, "ALL DATA HAS BEEN RESET",Toast.LENGTH_SHORT).show()
                btnNo.visibility=View.INVISIBLE
                btnYes.visibility=View.INVISIBLE
                btnRes.visibility= VISIBLE
            }
            btnNo.id->{
                btnNo.visibility=View.INVISIBLE
                btnYes.visibility=View.INVISIBLE
                btnRes.visibility= VISIBLE
            }
        }
    }
    fun clear(){
        etAmount.text.clear()
        etItem.text.clear()
    }
    fun save(){
        if(etItem!=null && etAmount!=null){
            val ex=Expense();
            ex.item=etItem.text.toString()
            ex.amount=etAmount.text.toString().toDouble()
            crud.Insert(ex)
        }
    }
}
