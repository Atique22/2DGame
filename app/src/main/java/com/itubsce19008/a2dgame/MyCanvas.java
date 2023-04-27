package com.itubsce19008.a2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCanvas extends View {

    boolean gameover = false;
    boolean showbrick1 = true;
    int score=0;
    int ballx, bally, ballw, ballh, bdeltax, bdeltay;
    int tabx, taby, tabw, tabh, tabdeltax, tabdeltay;
    int brx, bry, brw, brh;

    int width, height;
    public MyCanvas(Context context) {
        super(context);
        ballx = bally =100;
        ballw =100;
        ballh =100;

        bdeltax =5;
        bdeltay =5;

        tabx =100;
        taby =100;
        tabw =400;
        tabh =50;
        gameover = false;

        brx =100;
        bry =600;
        brw =200;
        brh =100;

        tabdeltax =0;
        tabdeltay =0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
         height = canvas.getHeight();
         width = canvas.getWidth();

        taby = height-200;

        Paint red = new Paint();
        red.setColor(Color.RED);

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);

        Paint textpaint = new Paint();
        textpaint.setColor(Color.CYAN);

        Paint textgameover  = new Paint();
        textgameover.setColor(Color.BLACK);

        Paint brickpaint = new Paint();
        brickpaint.setColor(Color.MAGENTA);

        canvas.drawCircle(ballx,bally,ballw/2,red);
        canvas.drawRect(tabx,taby,tabx+tabw,taby+tabh,blue);
        if(showbrick1==true)
            canvas.drawRect(brx,bry,brx+brw,bry+brh,brickpaint);
        textpaint.setTextSize(60);
        textgameover.setTextSize(100);
        canvas.drawText("Score = "+score,50, 100,textpaint);

        if(gameover==true)
            canvas.drawText("Game Over",100, 500,textgameover);

    }
}
