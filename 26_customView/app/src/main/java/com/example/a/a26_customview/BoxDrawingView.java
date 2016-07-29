package com.example.a.a26_customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by a on 2016-07-29.
 */
public class BoxDrawingView extends View {
    ArrayList<Box> boxArray = new ArrayList<Box>();
    Box currentBox;

    public BoxDrawingView(Context context) {
        super(context);
    }

    Paint boxPaint;
    Paint backgroundPaint;

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        boxPaint = new Paint();
        boxPaint.setColor(0x22ff0000);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xfff0f0f0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(backgroundPaint);
        for(int i=0; i< boxArray.size(); i++){
            Box box = boxArray.get(i);

            float left = Math.min(box.pointOrigin.x, box.pointCurrent.x);
            float right = Math.max(box.pointOrigin.x, box.pointCurrent.x);
            float top = Math.min(box.pointOrigin.y, box.pointCurrent.y);
            float bottom = Math.max(box.pointOrigin.y, box.pointCurrent.y);

            canvas.drawRect(left, top, right, bottom, boxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF curr = new PointF(event.getX(), event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                currentBox = new Box(curr);
                boxArray.add(currentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                if(currentBox != null){
                    currentBox.pointCurrent = curr;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                currentBox = null;
                break;
        }
        return true;
    }

}
