package jdal.smu.nv.ph.expensemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_expense.*
import java.util.ArrayList
import java.util.HashMap

class ExpenseActivity : AppCompatActivity() {
    val crud=ExpenseCrud(this)
    var arrList= ArrayList<HashMap<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)
        getExpense()


    }
    fun getExpense(){

        if(!crud.list.isEmpty()){
            arrList=crud.list
            val adapt= SimpleAdapter(this,crud.list,R.layout.expense_view,arrayOf("item","amount"), intArrayOf(R.id.tvItem,R.id.tvAmount))
            listExpense.adapter = adapt
            getTotal()
        }else{
            Toast.makeText(this,"NO DATA !", Toast.LENGTH_SHORT).show()
        }
    }
    fun getTotal(){
        val list=crud.GetExpenses()
        tvtTotal.text = list[0]["total"]
    }
}
