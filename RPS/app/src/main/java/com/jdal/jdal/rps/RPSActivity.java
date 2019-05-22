package com.jdal.jdal.rps;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class RPSActivity extends AppCompatActivity {
    Random rnd = new Random();
    private int max = 0;
    private int c = 0;
    private int p = 1;
    private int ps = 0;
    private int cs = 0;
    final int[] draw = new int[]{R.drawable.rock, R.drawable.paper, R.drawable.scissors};
    final String[] items = new String[]{"Rock", "Paper", "Scissors"};
    String winner = "NULL";
    int x=1;
    boolean isNotDone =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps);

        findViewById(R.id.rbPaper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView) findViewById(R.id.imgPlayer)).setImageResource(draw[1]);
                p = 2;
                findViewById(R.id.btnPlay).setEnabled(true);
            }
        });
        findViewById(R.id.rbRock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView) findViewById(R.id.imgPlayer)).setImageResource(draw[0]);
                p = 1;
                findViewById(R.id.btnPlay).setEnabled(true);
            }
        });
        findViewById(R.id.rbSci).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView) findViewById(R.id.imgPlayer)).setImageResource(draw[2]);
                p = 3;
                findViewById(R.id.btnPlay).setEnabled(true);
            }
        });
        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = rnd.nextInt(3)+1;
                ((ImageView) findViewById(R.id.imgComp)).setImageResource(draw[c-1]);
                Scoring(p, c++);
            }
        });



        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New();
            }
        });
        findViewById(R.id.btnPlay).setEnabled(false);
        builder();
    }

    void Scoring(int p, int c) {
        if (isNotDone) {
            if (p == 1 && c == 3) {
                ps++;
                winner = "Player won!";
            } else if (p == 3 && c == 1) {
                cs++;
                winner = "Computer won!";
            } else if (p == 1 && c == 2) {
                cs++;
                winner = "Computer won!";
            } else if (p == 2 && c == 1) {
                ps++;
                winner = "Player won!";
            } else if (p == 2 && c == 3) {
                cs++;
                winner = "Computer won!";
            } else if (p == 3 && c == 2) {
                ps++;
                winner = "Player won!";
            }
            else{
                winner = "IT'S A TIE!";
            }
            ((TextView) findViewById(R.id.txtPScore)).setText(ps + "");
            ((TextView) findViewById(R.id.txtCScore)).setText(cs + "");
            ((TextView) findViewById(R.id.txtMatch)).setText("Round " + x + "\n" + items[p - 1] + " VS " + items[c - 1] + "\n" + winner);
            x++;
        }
        if(max==ps || max==cs){
            isNotDone =false;
            if (ps > cs) {
                ((TextView) findViewById(R.id.txtMatch)).setText(getString(R.string.pwon));
            } else {
                ((TextView) findViewById(R.id.txtMatch)).setText(getString(R.string.compwon));
            }
            findViewById(R.id.btnPlay).setEnabled(false);
        }
    }

    void New() {
        builder();
        max = 0;
        c = 0;
        p = 1;
        ps = 0;
        cs = 0;
        x=1;
        ((RadioButton)findViewById(R.id.rbPaper)).setChecked(false);
        ((RadioButton)findViewById(R.id.rbRock)).setChecked(false);
        ((RadioButton)findViewById(R.id.rbSci)).setChecked(false);
        ((TextView) findViewById(R.id.txtCScore)).setText(0+"");
        ((TextView) findViewById(R.id.txtPScore)).setText(0+"");
        ((TextView) findViewById(R.id.txtMax)).setText(0+"");
        ((TextView) findViewById(R.id.txtMatch)).setText(getString(R.string.playrps));
        ((ImageView) findViewById(R.id.imgPlayer)).setImageResource(R.drawable.question_mark);
        ((ImageView) findViewById(R.id.imgComp)).setImageResource(R.drawable.question_mark);
        isNotDone =true;

    }
    void builder(){
        //alert bes
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setTitle("Enter Winning Score : ");
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                max = Integer.parseInt(input.getText().toString());
                ((TextView) findViewById(R.id.txtMax)).setText(input.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
