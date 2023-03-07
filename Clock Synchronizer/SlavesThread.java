import java.time.Clock;
import java.time.Instant;
import java.io.IOException;
import java.net.*;

public class SlavesThread extends Thread {
    public int id;
    private int maxMinDiff = 10;
    private int maxSeconds = 60 * maxMinDiff;
    private int randomSecondsGenerated;
    private boolean randomSignalGenerated;
    private DatagramSocket bcs;
    private DatagramPacket pkg;

    public SlavesThread(int id, DatagramSocket bcs) throws SocketException {
        this.id = id;
        this.bcs = bcs;
        this.pkg = new DatagramPacket(new byte[256], 256);

        Clock clock = Clock.systemUTC();
        // System.out.println("Thread " + id + " criada em " + Utils.clockFormatString(clock.instant()));

        // Gera horários aleatórios dentro de um intervalo de 30 minutos do horário
        // atual
        this.randomSecondsGenerated = (int) (Math.random() * this.maxSeconds + 1);

        // Gera um sinal aleatório para inverter o sinal do tempo aleatório gerado, para
        // que o tempo aleatório gerado possa ser antes ou depois do momento atual
        this.randomSignalGenerated = Math.random() < 0.5;
        if (this.randomSignalGenerated) {
            this.randomSecondsGenerated *= -1;
        }

        // System.out.println("Thread " + id + " tempo aleatorio: "
        //         + Utils.clockFormatString(clock.instant().plusSeconds(this.randomSecondsGenerated)) + " segundos");

    }

    public void run() {
        
        while (true) {
            System.out.println("Thread " + id + " rodando");
            try {
                bcs.receive(pkg);
                String data = new String(pkg.getData(), 0, pkg.getLength());
                System.out.println("Dados recebidos: " + Utils.clockFormatString(Instant.parse(data)) + " pela thread " + id);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

}