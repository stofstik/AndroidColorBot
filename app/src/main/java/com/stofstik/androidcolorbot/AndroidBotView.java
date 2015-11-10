package com.stofstik.androidcolorbot;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * A View with an Android bot that changes color
 */
public class AndroidBotView extends View {

    private int color;
    private boolean upDown = true;
    public boolean running = true;

    public void setColor(int color) {
        this.color = color;
    }

    public AndroidBotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground(getResources().getDrawable(R.drawable.android_bot_crop));
    }


}
