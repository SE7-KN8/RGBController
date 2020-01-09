package se7kn8.rgbcontroller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by sebkn on 15.01.2018.
 */

public class Connection {
    private static Socket socket;
    private static OutputStream stream;

    private static boolean init = false;

    public static void init() {
        if (!init) {
            try {
                socket = new Socket("192.168.178.100", 24578);
                stream = socket.getOutputStream();
                init = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static OutputStream getStream() {
        return stream;
    }
}
