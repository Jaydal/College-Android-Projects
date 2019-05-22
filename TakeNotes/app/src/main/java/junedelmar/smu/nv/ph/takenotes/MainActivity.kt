package junedelmar.smu.nv.ph.takenotes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    var arrList= ArrayList<HashMap<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        refreshMe()
        intent.setClass(this, NotesView::class.java)
        fab.setOnClickListener { view ->
            intent.putExtra("id", 0)
            startActivity(intent)
        }
        lv_notes.setOnItemClickListener { parent, view, position, id ->
            intent.putExtra("id", (arrList[position]["id"])!!.toInt())
            Toast.makeText(this, arrList[position]["id"], Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        btnRefresg.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onClick(v: View){
        when (v.id){
            btnRefresg.id->{
                refreshMe()
            }

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_reset -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun refreshMe(){
        val crud=NotesCrud(this)
        if(!crud.list.isEmpty()){
            arrList=crud.list
            val adapt= SimpleAdapter(this,crud.list,R.layout.view_notes_layout,arrayOf("id","title"), intArrayOf(R.id.tvNoteID,R.id.tvTitle))
            lv_notes.adapter = adapt
        }else{
            Toast.makeText(this,"NO DATA !", Toast.LENGTH_SHORT).show()
        }
    }
}
