package com.itubsce19008.a2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCanvas extends View {

    int ballx, bally, ballw, ballh;
    int tabx, taby, tabw, tabh;

    public MyCanvas(Context context) {
        super(context);

        ballx = bally =100;
        ballw =100;
        ballh =100;

        tabx =100;
        taby =100;
        tabw =400;
        tabh =50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        taby = height-200;
        
        Paint red = new Paint();
        red.setColor(Color.RED);

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);

        canvas.drawCircle(ballx,bally,ballw/2,red);
        canvas.drawRect(tabx,taby,tabx+tabw,taby+tabh,blue);

    }
}
