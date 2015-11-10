package com.stofstik.androidcolorbot;

/**
 * Created by stofstik on 18-2-15.
 */
public class ColorChangeTimer extends Thread {

    public int color = 0;
    private boolean upDown = true;
    private boolean running = true;

    AndroidBotView v;

    public void setRunning(boolean running){
        this.running = running;
    }

    public ColorChangeTimer(AndroidBotView v) {
        this.v = v;
    }

    @Override
    public void run(){
        try {
            while (running) {
                if (upDown) {
                    color += 15;
                    v.setColor(color);
                    if (color >= 255) upDown = false;
                } else {
                    color -= 15;
                    v.setColor(color);
                    if (color <= 0) upDown = true;
                }
                v.postInvalidate();
                Thread.sleep(17);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
