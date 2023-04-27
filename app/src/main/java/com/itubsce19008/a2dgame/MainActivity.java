package com.itubsce19008.a2dgame;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.SensorPrivacyManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCanvas c = new MyCanvas(getApplicationContext());

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent){
                float [] v= sensorEvent.values;
                if(v[0]<-2) {
                    if(c.tabdeltax + c.tabw < c.width)
                        c.tabdeltax = 10;
                    else
                        c.tabdeltax =0;
                }
                if(v[0]>2) {
                    if(c.tabx >10)
                        c.tabdeltax = -10;
                    else
                        c.tabdeltax =0;
                }
                if(v[0]<2 && v[0]>-2) {
                     c.tabdeltax =0;
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Handle changes to sensor accuracy, if needed
            }
        }, accel, sm.SENSOR_DELAY_GAME);


        setContentView(c);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    c.tabx = c.tabx+c.tabdeltax;
                    c.ballx = c.ballx + c.bdeltax;
                    if(c.ballx+c.ballw/2 > c.width)
                        c.bdeltax *= -1;

                    if (c.ballx<0)
                        c.bdeltax *= -1;


                    c.bally = c.bally + c.bdeltay;
                    if(c.bally> c.height)
                        c.gameover=true;
                    else
                        c.gameover =false;

                    if (c.bally+c.ballh<0)
                        c.bdeltay *= -1;

                    if(c.bally+c.ballh/2 >= c.taby && c.bally-c.ballh/2 < c.taby+c.tabh) {

                        if (c.ballx > c.tabx && c.ballx < (c.tabx + c.tabw)) {
                            if( c.gameover==false)
                                c.score+=1;
                            c.bdeltay *= -1;
                        }
                    }
                    if(c.bally+c.ballh/2 >= c.bry && c.bally-c.ballh/2 < c.bry+c.brh) {

                        if (c.ballx > c.brx && c.ballx < (c.brx + c.brw)) {
                            if(c.showbrick1 == true)
                                c.score+=20;
                            c.showbrick1 = false;
                        }
                    }

                    c.invalidate();
                }
            }
        });
        t.start();
    }
}