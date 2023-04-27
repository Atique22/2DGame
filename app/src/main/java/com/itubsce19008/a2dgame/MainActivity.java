package com.itubsce19008.a2dgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCanvas c = new MyCanvas(getApplicationContext());

        setContentView(c);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(42);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    c.ballx = c.ballx + c.bdeltax;
                    if(c.ballx+c.ballw/2 > c.width){
                        c.bdeltax *= -1;
                    }
                    if (c.ballx<0)
                    {
                        c.bdeltax *= -1;
                    }

                    c.bally = c.bally + c.bdeltay;
//                    if(c.bally+c.ballh/2 > c.height){
//                        c.bdeltay *= -1;
//                    }
                    if(c.bally> c.height){
                        c.gameover=true;
                    }else { c.gameover =false;}
                    if (c.bally+c.ballh<0)
                    {
                        c.bdeltay *= -1;
                    }
                    if(c.bally+c.ballh/2 >= c.taby && c.bally-c.ballh/2 <c.taby+c.tabh) {
//
                        if (c.ballx > c.tabx && c.ballx < (c.tabx + c.tabw)) {
                            c.bdeltay *= -1;
                        }
                    }

                    c.invalidate();
                }
            }
        });
        t.start();
    }
}