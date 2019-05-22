package seenlabgames.smu.nv.ph.tanungero

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(),View.OnClickListener {
    var active="asd"
    private lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tvContent.visibility= INVISIBLE
        btnStart.setOnClickListener(this)
        btnEx.setOnClickListener(this)
        btnCredits.setOnClickListener(this)
        btnMech.setOnClickListener(this)
        mp=MediaPlayer.create(this,R.raw.opening)
        mp.isLooping = true
        mp.start()
    }
    override fun onDestroy () {
        super.onDestroy ()
        mp.release ()
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
    override fun onClick(v:View){
        when (v.id){
            R.id.btnStart->{
                mp.release ()
                intent.setClass(this,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.btnEx->{
                finishAffinity()
            }
            R.id.btnCredits->{
                if(tvContent.visibility==VISIBLE && active=="credits"){
                    tvContent.visibility= INVISIBLE
                }else{
                    tvContent.text="Project in Mobile Computing \n under MR. Rogie Taborda,MIT \n\n\n" +
                            "Developed by: \n\n June Delmar A. Labitoria,BSIT \n" +
                            "Marie Chantal V. Seenivasagam,BSIT \n\n" +
                            "All Rights Reserved 2018"
                    tvContent.visibility= VISIBLE
                }
                active="credits"
            }
            R.id.btnMech->{
                if(tvContent.visibility==VISIBLE && active=="mech"){
                    tvContent.visibility= INVISIBLE
                }else{
                    tvContent.text="Nasaan ako?\n" +
                            "\tIkaw ay natagpuan sa lugar kung saan pinatay ang isang mag-aaral. Idinala ka sa estasyon ng pulis upang ikaw ay tanungin tungkol sa naganap na pangyayari. \n" +
                            "Ikaw ay Inaasahan na:\n" +
                            "1.\tSagutin ang mga tanong ng mabilisan.\n" +
                            "2.\tMagbigay ng parehas na sagot sa kaparehas na mga tanong.\n" +
                            "3.\tTanggapin ang iyong kapalaran kapag ito ay natapos na.\n" +
                            "\n" +
                            "Ano Ako?\n" +
                            "\n" +
                            "Ikaw ang salarin. Aminin mo na at nang matapos na ito.\n" +
                            "\n" +
                            "Hindi, Inosente ako. (Ipagpatuloy ang laro)/ Sasabihin ko ang lahat. Itigil niyo na ‘to.(Itigil ang laro)  \n"
                    tvContent.visibility= VISIBLE
                }
                active="mech"
            }
        }
    }

}
