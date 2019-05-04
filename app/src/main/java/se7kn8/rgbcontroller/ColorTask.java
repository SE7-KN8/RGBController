package se7kn8.rgbcontroller;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by sebkn on 15.01.2018.
 */

public class ColorTask extends AsyncTask<Void, Void, Void> {

    private int r;
    private int g;
    private int b;

    private String ip;
    private int port;

    private static final NumberFormat format = new DecimalFormat("000");

    public ColorTask(int r, int g, int b, String ip, int port) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.ip = ip;
        this.port = port;
    }

    private DatagramSocket socket;

    @Override
    protected Void doInBackground(Void... objects) {

        try {
            String message = "led" + format.format(r) + format.format(g) + format.format(b) + "\n";

            if (socket == null) {
                socket = new DatagramSocket();
            }

            byte[] sendData = message.getBytes(Charset.forName("US-ASCII"));
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip), port);
            socket.send(sendPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateColor(int r, int g, int b, String ip, int port) {
        new ColorTask(r, g, b, ip, port).execute();
    }

}