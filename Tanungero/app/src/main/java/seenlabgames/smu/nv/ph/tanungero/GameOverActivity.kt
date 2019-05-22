package seenlabgames.smu.nv.ph.tanungero

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity(), View.OnClickListener {
    val crud=ScoreCRUD(this)
    private lateinit var mp: MediaPlayer
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val i=intent.getIntExtra("score",0)
        btnRetry.setOnClickListener(this)
        btnHome.setOnClickListener(this)
        tvScore.text=i.toString()
        if(!crud.BestScore().isEmpty()){
            val list=crud.BestScore()
            tvBestScore.text=list[0]["score"].toString()
        }else{
            tvBestScore.text=(0).toString()
        }
        val bs=tvBestScore.text.toString().toInt()
        if(i>bs){
            tvBestScore.text=i.toString()
            val sc=Score()
            sc.bestscore=i
            crud.DeleteAll()
            crud.Insert(sc)
        }

        MobileAds.initialize(this,
            "ca-app-pub-9395822733111301~4903264167")

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mp=MediaPlayer.create(this,R.raw.no)
        mp.start()
    }
    override fun onDestroy () {
        super.onDestroy ()
        mp.release ()
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            val _i=Intent()
            _i.setClass(applicationContext,HomeActivity::class.java)
            _i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(_i)
        }
        return super.onKeyDown(keyCode, event)
    }
    override fun onClick(v:View){
        when (v.id){
            R.id.btnRetry->{
                val _i=Intent()
                _i.setClass(applicationContext,MainActivity::class.java)
                _i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(_i)
            }
            R.id.btnHome->{
                intent.setClass(applicationContext,HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }

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
}
