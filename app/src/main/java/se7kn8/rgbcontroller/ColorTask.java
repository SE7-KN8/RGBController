package se7kn8.rgbcontroller;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by sebkn on 15.01.2018.
 */

public class ColorTask extends AsyncTask<Void, Void, Void> {

    public static final int PIN_RED = 5;
    public static final int PIN_GREEN = 26;
    public static final int PIN_BLUE = 6;

    private int pin;
    private int color;

    private ColorTask(int pin, int color) {
        this.pin = pin;
        this.color = color;
    }

    @Override
    protected Void doInBackground(Void... objects) {

        try{
            Connection.init();
            Connection.getStream().write(pin);
            Connection.getStream().write(color);
            Connection.getStream().flush();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public static void updateRed(int red){
        new ColorTask(PIN_RED, red).execute();
    }

    public static void updateGreen(int green){
        new ColorTask(PIN_GREEN, green).execute();
    }

    public static void updateBlue(int blue){
        new ColorTask(PIN_BLUE, blue).execute();
    }

}
