package com.r4hs.photomemo.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


/**
 * TODO: document your custom view class.
 */
public class MyPaintView extends View implements View.OnTouchListener {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private ArrayList<Path> pathList = new ArrayList<Path>();
    private Path mPath;

    public MyPaintView(Context context) {
        super(context);
        init(null, 0);
    }


    private void init(AttributeSet attrs, int defStyle) {
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(13);

        for (int i=0; i < pathList.size(); i++){
            canvas.drawPath(pathList.get(i), paint);
        }
    }

    public void clear(){
        pathList =  new ArrayList<Path>();
        invalidate();
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                //線の描画開始
                mPath = new Path();
                mPath.moveTo(motionEvent.getX(), motionEvent.getY());
                pathList.add(mPath);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                mPath.lineTo(motionEvent.getX(), motionEvent.getY());
                break;
        }
        // 再描画
        invalidate();
        return true;
    }
}
