import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThreads extends Thread {
    private Socket socket;

    ServerThreads(Socket socket) {
        this.socket = socket;
        System.out.println("Conexao com o cliente: " + socket.getInetAddress() + "-"
                + socket.getPort());
    }

    public void run() {
        System.out.println("Iniciando Thread: " + Thread.currentThread().getName());
        try {
            // Criando canal de entrada
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream()));
            // Setando timeout de 60 segundos até conexão ser fechada
            this.socket.setSoTimeout(60000);
            // Recebendo nome do arquivo
            System.out.println("Esperando nome do arquivo...");
            String arquivo = inFromClient.readLine();
            System.out.println("Recebido nome do arquivo: " + arquivo);
            System.out.println("Nome Arquivo: " + arquivo);
            // Importando arquivo para memoria
            FileInputStream fileIn = new FileInputStream(arquivo);
            // Criando canal de saida de dados
            OutputStream socketOut = this.socket.getOutputStream();
            // Criando tamanho de leitura
            byte[] cbuffer = new byte[1024];
            int bytesRead;

            // Lendo arquivo e enviando para o canal de transferencia
            System.out.println("Enviando Arquivo: " + arquivo);
            while ((bytesRead = fileIn.read(cbuffer)) != -1) {
                socketOut.write(cbuffer, 0, bytesRead);
                socketOut.flush();
            }
            fileIn.close();
            this.socket.close();
            System.out.println("Arquivo Enviado!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.stop();
        }
    }
}
