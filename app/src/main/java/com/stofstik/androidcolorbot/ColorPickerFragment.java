package com.stofstik.androidcolorbot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * A simple color picker for educational purposes
 */
public class ColorPickerFragment extends DialogFragment {

    private static final int RED = 1;
    private static final int GREEN = 2;
    private static final int BLUE = 3;

    public int iRed, iGreen, iBlue;

    private SeekBar sbRed, sbGreen, sbBlue;
    private ImageView ivRed, ivGreen, ivBlue, imageView;
    private View rootView;

    private void init() {
        sbRed = (SeekBar) rootView.findViewById(R.id.sbRed);
        sbGreen = (SeekBar) rootView.findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) rootView.findViewById(R.id.sbBlue);
        imageView = (ImageView) rootView.findViewById(R.id.ivColor);
        ivRed = (ImageView) rootView.findViewById(R.id.ivRed);
        ivGreen = (ImageView) rootView.findViewById(R.id.ivGreen);
        ivBlue = (ImageView) rootView.findViewById(R.id.ivBlue);

        sbRed.setMax(255);
        sbGreen.setMax(255);
        sbBlue.setMax(255);

        sbRed.setOnSeekBarChangeListener(new MySeekBarListener(RED));
        sbGreen.setOnSeekBarChangeListener(new MySeekBarListener(GREEN));
        sbBlue.setOnSeekBarChangeListener(new MySeekBarListener(BLUE));
    }

    class MySeekBarListener implements SeekBar.OnSeekBarChangeListener {

        int iColor;

        public MySeekBarListener(int iColor) {
            this.iColor = iColor;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (iColor) {
                case RED:
                    iRed = progress;
                    ivRed.setBackgroundColor(Color.argb(255, progress, 0, 0));
                    break;
                case GREEN:
                    iGreen = progress;
                    ivGreen.setBackgroundColor(Color.argb(255, 0, progress, 0));
                    break;
                case BLUE:
                    iBlue = progress;
                    ivBlue.setBackgroundColor(Color.argb(255, 0, 0, progress));
                    break;
            }
            imageView.setBackgroundColor(Color.argb(255, iRed, iGreen, iBlue));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        rootView = inflater.inflate(R.layout.fragment_color_picker, null);
        init();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(rootView)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

}
