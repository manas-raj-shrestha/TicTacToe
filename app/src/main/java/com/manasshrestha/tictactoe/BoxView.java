package com.manasshrestha.tictactoe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Manas on 11/9/2015.
 */
public class BoxView extends View {
    RectF rtBox;
    Boolean draw = false;


    public BoxView(Context context) {
        super(context);
        initializeBox();
    }


    public BoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeBox();
    }

    public BoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeBox();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        if (draw) {
            if (BoxGroup.playerOneTurn)
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.o), null, rtBox, paint);
            else
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.x), null, rtBox, paint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) rtBox.width(), (int) rtBox.height());
    }

    private void initializeBox() {
        rtBox = new RectF();
        rtBox.set(0, 0, px(95), px(100));
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

    SendTouchEvents sendTouchEvents;

    public void setUpInterface(SendTouchEvents sendTouchEvents) {
        this.sendTouchEvents = sendTouchEvents;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sendTouchEvents.boxInfo(getTag().toString());

        if (!isSelected()) {
            draw = true;
            invalidate();
            setSelected(true);
        }

        return super.onTouchEvent(event);

    }

    public interface SendTouchEvents {
        public void boxInfo(String tag);
    }
}
