import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException {
        int socketPortStart = Configs.socketPortStart;
        int numberOfSockets = Configs.numberOfSockets;

        Clock clock = new Clock(Configs.serverInitialTimestamp, "Server");
        clock.start();

        ArrayList<Integer> socketNumbers = new ArrayList<Integer>();
        ArrayList<DatagramPacket> packets = new ArrayList<DatagramPacket>();

        // Criando os números de sockets de clientes começando a partir do 6001
        for (int s = socketPortStart; s < (socketPortStart + numberOfSockets); s++) {
            socketNumbers.add(s);
        }

        int timestamp;
        DatagramSocket ds = new DatagramSocket(6000);

        int recievedMessages = 0;
        int accumulatedTime = 0;

        while (recievedMessages < numberOfSockets) {
            DatagramPacket pkg = new DatagramPacket(new byte[256], 256);
            ds.receive(pkg);
            String data = new String(pkg.getData(), 0, pkg.getLength());
            System.out.println("Recieved Data: " + data);
            accumulatedTime += Integer.parseInt(data);
            recievedMessages++;
        }

        timestamp = clock.getTimestamp();

        int averageTime = (accumulatedTime + timestamp) / (numberOfSockets + 1);
        System.out.println("AverageTime: " + averageTime);

        Integer synchronizedTime = timestamp + averageTime;

        byte[] synchronizedTimeInBytes = synchronizedTime.toString().getBytes();
        System.out.println("synchronized time: " + synchronizedTime);

        InetAddress addr = InetAddress.getByName("255.255.255.255");

        // Criando os pacotes
        for (int i = 0; i < socketNumbers.size(); i++) {
            packets.add(new DatagramPacket(synchronizedTimeInBytes, synchronizedTimeInBytes.length, addr,
                    socketNumbers.get(i)));
        }

        clock.setTimestamp(synchronizedTime);
        // Enviando as mensagens
        for (DatagramPacket datagramPacket : packets) {
            System.out.println(
                    "Enviando mensagem '" + new String(datagramPacket.getData()) + "' para "
                            + datagramPacket.getPort());
            ds.send(datagramPacket);
        }
    }
}