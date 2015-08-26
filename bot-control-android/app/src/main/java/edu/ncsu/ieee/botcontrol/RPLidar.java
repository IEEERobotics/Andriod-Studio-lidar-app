package edu.ncsu.ieee.botcontrol;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.content.Context;

import java.util.Random;

/**
 * Created by Andrew on 7/23/2015.
 */
public class RPLidar extends View{
    private int points[] = new int[360];

    private float centerX = 0.f;   ///< Center X position
    private float centerY = 0.f;   ///< Center Y position

    public RPLidar(Context context){
        super(context);
        init(null, 0);
    }
    public RPLidar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }
    public RPLidar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        for(int i = 0; i < 360; i++) {
            points[i] = i * 300 / 360;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int loop;
        double d;
        for(loop = 0, d = 0; loop < 360; loop++, d += Math.PI/180.0) {
            double x = Math.cos(d) * points[loop];
            double y = Math.sin(d) * points[loop];

            float[] hsv = {points[loop], 1, 1};

            Paint ptPaint = new Paint();
            ptPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            ptPaint.setStyle(Paint.Style.FILL);
            ptPaint.setColor(Color.HSVToColor(hsv));
            canvas.drawCircle(centerX + (float)x, centerY + (float)y, 3.f, ptPaint);
        }
    }

    public void setPoints(int[] pts) {
        points = pts;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w == 0 || h == 0)
            return; // invalid width/height, nothing to do

        centerX = w / 2.f;
        centerY = h / 2.f;
    }
}
