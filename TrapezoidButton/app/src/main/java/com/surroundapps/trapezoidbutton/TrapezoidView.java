package com.surroundapps.trapezoidbutton;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;

/**
 * Created by Murtuza Rahman on 4/1/19.
 */
public class TrapezoidView extends View {
    private ShapeDrawable mTrapezoid;

    public TrapezoidView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(100.0f, 0.0f);
        path.lineTo(200.0f, 100.0f);
        path.lineTo(0.0f, 100.0f);
        path.lineTo(0.0f, 0.0f);

        mTrapezoid = new ShapeDrawable(new PathShape(path, 200.0f, 100.0f));
        mTrapezoid.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        mTrapezoid.getPaint().setStrokeWidth(1.0f);
        mTrapezoid.getPaint().setColor(Color.GREEN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mTrapezoid.setBounds(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mTrapezoid.draw(canvas);
    }
}
