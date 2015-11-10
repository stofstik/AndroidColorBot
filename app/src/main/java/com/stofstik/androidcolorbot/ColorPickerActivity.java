package com.stofstik.androidcolorbot;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * A wrapper activity for our ColorPickerFragment
 */
public class ColorPickerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
    }
}
