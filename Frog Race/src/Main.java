import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> posicoes = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        List<Sapo> sapos = new ArrayList<>();
        sapos.add(new Sapo("Alcinho"));
        sapos.add(new Sapo("Bolinha"));
        sapos.add(new Sapo("Caramelo"));
        sapos.add(new Sapo("Diamante"));
        sapos.add(new Sapo("Escarlate"));

        for (Sapo sapo : sapos) {
            sapo.start();
        }

        //Esperando os sapos pararem de pular
        Thread.sleep(5000);
        
        int winPosition = 1;
        System.out.println("\nOrdem de chegada: ");
        for (String pos : posicoes) {
            System.out.println(winPosition + "º lugar: " + pos);
            winPosition++;
        }
    }
}
