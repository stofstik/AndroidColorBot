package com.stofstik.androidcolorbot;

import java.util.Calendar;

/**
 * Created by stofstik on 18-2-15.
 */
public class DayTimeColorChanger extends Thread {

    private AndroidBotView v;
    public int color = 0;
    private boolean running = true;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public DayTimeColorChanger(AndroidBotView v) {
        this.v = v;
    }

    @Override
    public void run() {
        try {
            while (running) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);

                long midnight = cal.getTimeInMillis();
                long now = Calendar.getInstance().getTimeInMillis();
                long millisSinceMidnight = now - midnight;
                long oneDay = 86400000;
                double dSleep = oneDay / 255; // as we only have 255 color levels
                long sleep = (int) dSleep;

                double ratio = (double) millisSinceMidnight / (double) oneDay;

                double dColor = 255 * ratio;
                color = (int) dColor;

                v.setColor(color);
                v.postInvalidate();

                Thread.sleep(sleep);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
