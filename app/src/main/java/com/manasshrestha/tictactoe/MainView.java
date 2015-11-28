package com.manasshrestha.tictactoe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Class that creates # and 9 boxes for X and O.
 */
public class MainView extends View {
    RectF rtMain;
    RectF rtBox1;
    RectF rtBox2;
    RectF rtBox3;
    RectF rtBox4;
    RectF rtBox5;
    RectF rtBox6;
    RectF rtBox7;
    RectF rtBox8;
    RectF rtBox9;

    float tempX = 0;
    float tempY = 0;

    public MainView(Context context) {
        super(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initializeRects();
    }

    /**
     * Method to initialize rectangles.
     */
    public void initializeRects() {
        rtMain = new RectF();
        rtMain.set(getWidth() / 2 - px(200), getHeight() / 2 - px(200), getWidth() / 2 + px(200), getHeight() / 2 + px(200));

        rtBox1 = new RectF();
        rtBox1.set(getWidth() / 2 - px(150), getHeight() / 2 - px(150), getWidth() / 2 - px(50), getHeight() / 2 - px(50));

        rtBox2 = new RectF();
        rtBox2.set(getWidth() / 2 - px(50) + px(4), getHeight() / 2 - px(150), getWidth() / 2 + px(50), getHeight() / 2 - px(50));

        rtBox3 = new RectF();
        rtBox3.set(getWidth() / 2 + px(50) + px(4), getHeight() / 2 - px(150), getWidth() / 2 + px(150), getHeight() / 2 - px(50));

        //second row boxes
        rtBox4 = new RectF();
        rtBox4.set(getWidth() / 2 - px(150), getHeight() / 2 - px(50) + px(4), getWidth() / 2 - px(50), getHeight() / 2 + px(50));

        rtBox5 = new RectF();
        rtBox5.set(getWidth() / 2 - px(50) + px(4), getHeight() / 2 - px(50) + px(4), getWidth() / 2 + px(50), getHeight() / 2 + px(50));

        rtBox6 = new RectF();
        rtBox6.set(getWidth() / 2 + px(50) + px(4), getHeight() / 2 - px(50) + px(4), getWidth() / 2 + px(150), getHeight() / 2 + px(50));

        //third row boxes
        rtBox7 = new RectF();
        rtBox7.set(getWidth() / 2 - px(150), getHeight() / 2 + px(50) + px(4), getWidth() / 2 - px(50), getHeight() / 2 + px(150));

        rtBox8 = new RectF();
        rtBox8.set(getWidth() / 2 - px(50) + px(4), getHeight() / 2 + px(50) + px(4), getWidth() / 2 + px(50), getHeight() / 2 + px(150));

        rtBox9 = new RectF();
        rtBox9.set(getWidth() / 2 + px(50) + px(4), getHeight() / 2 + px(50) + px(4), getWidth() / 2 + px(150), getHeight() / 2 + px(150));

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.hash), null, rtMain, paint);
//        canvas.drawRect(rtBox1, paint);
        paint.setColor(Color.RED);
//        canvas.drawRect(rtBox2, paint);
        paint.setColor(Color.GREEN);
//        canvas.drawRect(rtBox3, paint);
        paint.setColor(Color.RED);
//        canvas.drawRect(rtBox4, paint);
        paint.setColor(Color.GREEN);
//        canvas.drawRect(rtBox5, paint);
        paint.setColor(Color.RED);
//        canvas.drawRect(rtBox6, paint);
        paint.setColor(Color.GREEN);
//        canvas.drawRect(rtBox7, paint);
        paint.setColor(Color.RED);
//        canvas.drawRect(rtBox8, paint);
        paint.setColor(Color.GREEN);
//        canvas.drawRect(rtBox9, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(px(10));

//        while (tempX < rtBox1.right) {
//            canvas.drawLine(rtBox1.left, rtBox1.top, tempX, tempY,
//                    paint);
//            tempX++;
//            tempX++;
//
//            tempY++;
//            tempY++;
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }

    }

    /**
     * This method converts device specific pixels to density independent pixels.
     */
    public float px(float dp) {
        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
