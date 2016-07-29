package com.example.a.a26_customview;

import android.graphics.PointF;

/**
 * Created by a on 2016-07-29.
 */
public class Box {
    PointF pointOrigin;
    PointF pointCurrent;

    public Box(PointF origin){
        pointCurrent = pointOrigin = origin;
    }

}
