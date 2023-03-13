import java.io.IOException;
import java.net.*;

public class ClientThread extends Thread {
    private int id;
    private Integer timestamp;
    private DatagramSocket bcs;
    private DatagramPacket pkg;
    private Clock clock;

    public ClientThread(int id, DatagramSocket bcs, Integer timestamp) throws SocketException {
        this.id = id;
        this.bcs = bcs;
        this.clock = new Clock(timestamp, "Client " + id);
        this.clock.start();
        this.timestamp = this.clock.getTimestamp();
    }

    public void run() {
        SendTimestampToServer();
        GetSynchronizedTimestampFromServer();
    }

    private void GetSynchronizedTimestampFromServer() {
        while (true) {
            try {
                this.pkg = new DatagramPacket(new byte[256], 256);
                bcs.receive(this.pkg);
                String data = new String(this.pkg.getData(), 0, this.pkg.getLength());
                this.clock.setTimestamp(Integer.parseInt(data));
                this.timestamp = this.clock.getTimestamp();
                System.out.println("Timestamp atualizado: " + this.timestamp + " pela thread " + id);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void SendTimestampToServer() {
        byte[] timeStampInBytes = this.timestamp.toString().getBytes();

        System.out.println("Enviando timestamp " + this.timestamp + " pela thread " + id);
        try {
            this.pkg = new DatagramPacket(timeStampInBytes, timeStampInBytes.length, InetAddress.getLocalHost(), 6000);
        } catch (UnknownHostException e) {
            System.err.println("Não foi possível encontrar o host");
        }

        try {
            this.bcs.send(this.pkg);
        } catch (IOException e) {
            System.err.println("Não foi possível enviar o pacote");
        }
    }
}