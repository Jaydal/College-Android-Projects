package com.jdal.jdal.doomsday;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

public class DoomsActivity extends AppCompatActivity {
private int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dooms);

        final Spinner spMonth=(Spinner)findViewById(R.id.spMonth);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.Month));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonth.setAdapter(adapter);

        spDay(1,(Calendar.getInstance().get(Calendar.YEAR)));

        ArrayList<String> yearList=new ArrayList<String>();

        for(int d=0;d<101;d++){
            yearList.add((Calendar.getInstance().get(Calendar.YEAR)-d) +"");
        }

        final Spinner spYear=(Spinner)findViewById(R.id.spYear);
        ArrayAdapter<String>adapterYear=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,yearList);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYear.setAdapter(adapterYear);


        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    spDay(spMonth.getSelectedItemPosition()+1,Integer.parseInt(spYear.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spDay(spMonth.getSelectedItemPosition()+1,Integer.parseInt(spYear.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //do here
                Compute(((Spinner)findViewById(R.id.spDay)).getSelectedItemPosition()+1,spMonth.getSelectedItemPosition()+1,Integer.parseInt(spYear.getSelectedItem().toString()));
            }
        });

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spMonth.setSelection(0);
                spYear.setSelection(0);
                spDay(1,(Calendar.getInstance().get(Calendar.YEAR)));

                ((TextView)findViewById(R.id.txtRes)).setText("");
            }
        });

        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    ArrayList addDayItems(int currMonth, int currYear){
        ArrayList<String> daylist=new ArrayList<String>();
        if(currMonth==2 || currMonth==4 || currMonth==6 || currMonth==9 || currMonth==11) {

                if(currMonth==2){

                    if(currYear%4==0){
                        for(int d=1;d<30;d++) {

                            daylist.add(d + "");
                        }
                    }else{
                        for(int d=1;d<29;d++) {

                            daylist.add(d + "");
                        }
                    }

                }
                else{
                    for(int d=1;d<31;d++) {
                        daylist.add(d + "");
                    }
                }

        }else
        {
            for(int d=1;d<32;d++){
                daylist.add(d+"");
            }
        }
        return daylist;
    }
    void spDay(int currMonth,int currYear){
        Spinner spDay=(Spinner)findViewById(R.id.spDay);
        ArrayAdapter<String>adapterDay=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,addDayItems(currMonth,currYear));
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDay.setAdapter(adapterDay);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void Compute(int day, int month, int yr){
        int a,b,c,d,w,x,y,z,r,newyear;
        if(month==1 || month==2){
            a=month+10;
            newyear=yr-1;
        }else{
            a=month-2;
            newyear=yr;
        }
        b=day;
        c=newyear%100;
        d=newyear/100;
        w=(13*a-1)/5;
        x=c/4;
        y=d/4;
        z=w+x+y+b+c-2*d;
        r=z%7;
        if(r<0){
            r+=7;

        }

        String[] arr=getResources().getStringArray(R.array.day);
        ((TextView)findViewById(R.id.txtRes)).setText("The day you were born is "+arr[r]+"\n"+getAge(day,month,yr));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static String getAge(int d, int m, int y){

        LocalDate birthDate = LocalDate.of(Integer.parseInt(y+""), Integer.parseInt(m+""), Integer.parseInt(d+""));
        LocalDate now = LocalDate.now();
        Period diff=Period.between(birthDate,now);
        return "You are already "+diff.getYears()+" Years "+diff.getMonths()+" Months "+diff.getDays()+" Days Old";
    }
}
