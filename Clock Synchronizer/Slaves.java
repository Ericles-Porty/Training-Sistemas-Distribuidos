import java.io.IOException;
import java.net.*;

public class Slaves {
    public static void main(String[] args) throws IOException {
        DatagramSocket bcs = new DatagramSocket(6004);
        for (int i = 0; i < 3; i++) {
            SlavesThread t = new SlavesThread(i, bcs);
            t.start();
        }

    }
}
