import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

	public static void main(String[] args) {

		// Criando socket de servidor
		ServerSocket server;
		try {
			server = new ServerSocket(9000);
			System.out.println("Servidor Iniciado!");
			while (true) {
				// Aguardando conexao do cliente
				Socket connectedSocket = server.accept();
				System.out.println("Cliente Conectado!");
				// Criando thread para atender o cliente
				Thread threadSocket = new ServerThreads(connectedSocket);
				threadSocket.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
