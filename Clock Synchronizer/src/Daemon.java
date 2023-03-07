import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

public class Mestre {

    public static void main(String[] args) {
        // Salvando a hora atual do mestre
        Clock clock = Clock.systemUTC();
        Instant instant = clock.instant();
        System.out.println(Utils.clockFormatString(instant));

        // create a list of Instant objects
        List<Instant> instants = List.of(instant);

        try {
            byte[] time = instant.toString().getBytes();
            // Definindo o endere�o de envio do pacote neste caso o endere�o de broadcast
            InetAddress addr = InetAddress.getByName("255.255.255.255");
            DatagramPacket pkg = new DatagramPacket(time, time.length, addr, 6004);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pkg);// enviando pacote broadcast
        } catch (Exception e) {
            System.out.println("Nao foi possivel enviar a mensagem");
        }
    }
}