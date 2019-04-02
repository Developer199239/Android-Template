package com.surroundapps.trapezoidbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.widget.Button;

/**
 * Created by Murtuza Rahman on 4/1/19.
 */
public class CustomButton extends AppCompatButton {
    public CustomButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private Drawable rightDrawable;
}
