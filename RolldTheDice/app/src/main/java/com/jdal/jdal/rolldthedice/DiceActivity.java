package com.jdal.jdal.rolldthedice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DiceActivity extends AppCompatActivity {

    final int[] img={R.drawable.sixsided_one,R.drawable.sixsided_two,R.drawable.sixsided_three,R.drawable.sixsided_four,
            R.drawable.sixsided_five,R.drawable.sixsided_six};
    Random rnd=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        findViewById(R.id.btnSix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //randomize Image Here
                Randomize(6);
            }
        });

        findViewById(R.id.btnEight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //randomize Image Here
                Randomize(8);
            }
        });

        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)findViewById(R.id.imgView)).setImageResource(R.drawable.cleverbaby);
            }
        });
    }
    void Randomize(int side){
        if(side==6)
        {
            SetImage();
        }else if(side==8){

        }
    }
    void SetImage(){
        int r =rnd.nextInt(img.length);
        ((ImageView)findViewById(R.id.imgView)).setImageResource(img[r]);
    }

}
