import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSide {

	public static void main(String[] args) {

		try {
			System.out.println("Digite o nome do arquivo: ");
			Scanner scan = new Scanner(System.in);
			String nomeArquivo = scan.nextLine();
			
			// Criando socket de cliente
			Socket clientSocket = new Socket("localhost", 9000);
			clientSocket.setSoTimeout(60000);
			// Criando canal de saida de dados (Texto)
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			// Enviando nome do arquivo para o servidor
			outToServer.writeBytes("arquivos/" + nomeArquivo+ "\n");
            System.out.println("Nome do arquivo enviado: " + nomeArquivo);
			// Criando arquivo que sera recebido pelo servidor
			FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
			
			// Criando canal de transferencia de dados
			InputStream socketIn = clientSocket.getInputStream();
			

			// Lendo o arquivo recebido pelo socket e gravando no  arquivo local
			System.out.println("Recebendo Arquivo: " + nomeArquivo);

			// Criando tamanho de leitura
			byte[] cbuffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = socketIn.read(cbuffer)) != -1) {
				fileOut.write(cbuffer, 0, bytesRead);
				System.out.println("Conteudo recebido: " + new String(cbuffer, 0, bytesRead));
			}
			fileOut.close();
			clientSocket.close();
			System.out.println("Arquivo Recebido: " + nomeArquivo);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
