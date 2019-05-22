package seenlabgames.smu.nv.ph.tanungero

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mAdView : AdView
    val t=Timer()
    private lateinit var mp: MediaPlayer

    val timer = MyCounter(6000, 10)
    var ans=""
    var rnd=0
    var isOut=false
    var score=0
    var count=0
    var hold_ans= arrayOf("","","","","","","","","","","","","","","","","","")
    val arrAns= arrayOf(R.array.q1,R.array.q2,R.array.q3,
        R.array.q4,R.array.q5,R.array.q6,R.array.q7,R.array.q8,R.array.q9,R.array.q10,
        R.array.q11,R.array.q12,R.array.q13,R.array.q14,R.array.q15,R.array.q16,R.array.q17)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRandomQuestion()
        btnQ1.setOnClickListener(this)
        btnQ2.setOnClickListener(this)
        btnQ3.setOnClickListener(this)
        btnQ4.setOnClickListener(this)
        tvTimer.text=(0).toString()
        MobileAds.initialize(this,
            "ca-app-pub-9395822733111301~4903264167")
        mp=MediaPlayer.create(this,R.raw.opening)
        mp.isLooping = true
        mp.start()

    }
    override fun onDestroy () {
        super.onDestroy ()
        mp.release ()
    }
    fun getRandomQuestion(){

        mAdView = findViewById(R.id.adview1)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        timer.start()
        rnd=(0..16).random()
        val arr=resources.getStringArray(R.array.questions)
        tvQuestion.text=arr[rnd]
        val answers=resources.getStringArray(arrAns[rnd])
        val num = intArrayOf(0, 1, 2, 3)
        val x=num.toMutableList()
        x.shuffle()
        btnQ1.text=answers[x[0]]
        btnQ2.text=answers[x[1]]
        btnQ3.text=answers[x[2]]
        btnQ4.text=answers[x[3]]
    }
    override fun onClick(v:View){
        ans = when (v.id){
            R.id.btnQ1->{
               btnQ1.text.toString()
            }
            R.id.btnQ2->{
                btnQ2.text.toString()
            }
            R.id.btnQ3->{
                btnQ3.text.toString()
            }
            R.id.btnQ4->{
                btnQ4.text.toString()
            }
            else -> {
                ""
            }
        }
        if(hold_ans[rnd].isBlank()){
            hold_ans[rnd]=ans
        }
        if(hold_ans[rnd]==ans){
            score++
            getRandomQuestion()
            tvTimer.text=(score).toString()
        }else{
            mp.release ()
            t.cancel()
            t.purge()
            isOut=true
            this.setVisible(false)
            val _i=Intent()
            _i.setClass(applicationContext,GameOverActivity::class.java)
            _i.putExtra("score",score)
            _i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(_i)
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            val _i=Intent()
            _i.setClass(applicationContext,GameOverActivity::class.java)
            _i.putExtra("score",score)
            _i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(_i)
        }
        return super.onKeyDown(keyCode, event)
    }
    private fun IntRange.random()=Random().nextInt((endInclusive+1)-start)+start
    inner class MyCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onFinish() {
            println("Timer Completed.")
            pBar.progress=1000
            if(!isOut){
                mp.release ()
                t.cancel()
                t.purge()
                val _in=Intent()
                _in.setClass(applicationContext,GameOverActivity::class.java)
                _in.putExtra("score",score)
                _in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(_in)
            }

        }
        var time=0
        override fun onTick(millisUntilFinished: Long) {
            time= (millisUntilFinished / 10).toInt()
            pBar.progress=time
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
