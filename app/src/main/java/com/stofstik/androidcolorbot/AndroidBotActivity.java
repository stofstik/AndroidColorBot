package com.stofstik.androidcolorbot;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class AndroidBotActivity extends Activity {

    private final static String LOG = "AndroidBotActivity";

    private SeekBar seekBar;
    private ToggleButton tbStartStop;
    private Button bTime, bTransition;
    private ColorChangeTimer colorChanger;
    private DayTimeColorChanger dtColorChanger;
    private ImageView iv;
    private boolean running;

    AnimationDrawable animationDrawable = new AnimationDrawable();
    private static AndroidBotView androidBot;
    ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#151a08"), Color.parseColor("#a4c63c"));
    private static int mProgress;

    public static int getmProgress() {
        return mProgress;
    }

    public static void setmProgress(int mProgress) {
        AndroidBotActivity.mProgress = mProgress;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_bot);
        init();
    }

    private void init() {

        androidBot = (AndroidBotView) findViewById(R.id.vAndroidBot);
        tbStartStop = (ToggleButton) findViewById(R.id.tbStartStop);
        bTime = (Button) findViewById(R.id.bTime);
        bTransition = (Button) findViewById(R.id.bXMLTransition);

        seekBar = (SeekBar) findViewById(R.id.sbSeekBar);
        seekBar.setMax(5000);
        seekBar.setProgress(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueAnimator.setDuration(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tbStartStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    killOther();


                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            PorterDuff.Mode mMode = PorterDuff.Mode.MULTIPLY;
                            androidBot.getBackground().setColorFilter((int) animation.getAnimatedValue(), mMode);
                        }
                    });
                    valueAnimator.setDuration(seekBar.getProgress());
                    valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                    valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
                    valueAnimator.start();
                } else {
                    valueAnimator.end();
                }
            }
        });

        bTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set toggle button to off
                tbStartStop.setChecked(false);
                killOther();
                dtColorChanger = new DayTimeColorChanger(androidBot);
                dtColorChanger.start();
            }
        });

        bTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(AndroidBotActivity.this, ColorPickerActivity.class);
                startActivity(i);*/
                ColorPickerFragment colorPickerFragment = new ColorPickerFragment();
                colorPickerFragment.show(getFragmentManager(), "ColorPickerFragment");
            }
        });
    }

    private void killOther() {
        // try to stop other threads
        try {
            colorChanger.setRunning(false);
        } catch (NullPointerException e) {
            Log.d("sjih", "colorChanger was not running");
        }
        try {
            dtColorChanger.setRunning(false);
        } catch (NullPointerException e) {
            Log.d("sjih", "dtColorChanger was not running");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_android_bot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
