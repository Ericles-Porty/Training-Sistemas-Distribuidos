import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) throws IOException {
        Integer timestamp = Configs.clientInitialTimestamp;
        int incrementTimestamp = Configs.incrementTimestamp;
        int socketPortStart = Configs.socketPortStart;
        int numberOfSockets = Configs.numberOfSockets;

        ArrayList<Integer> sockets = new ArrayList<Integer>();

        for (int s = socketPortStart; s < (socketPortStart + numberOfSockets); s++) {
            sockets.add(s);
        }

        for (int i = 0; i < numberOfSockets; i++) {
            DatagramSocket socket = new DatagramSocket(sockets.get(i));
            ClientThread t = new ClientThread(i, socket, timestamp);
            timestamp = timestamp + incrementTimestamp;
            t.start();
        }
    }
}
