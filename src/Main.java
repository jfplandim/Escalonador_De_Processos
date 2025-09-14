import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();

        if (args.length == 0){
            System.err.println("Erro ao fornecer nome do arquivo.");
        }
        String nomeArquivo = args[0];
        try(Scanner scanner = new Scanner(new File(nomeArquivo))){
            while(scanner.hasNextLine()){
                String linha = scanner.nextLine();
                String[] dados = linha.split(",");

                int id = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                int prioridade = Integer.parseInt(dados[2].trim());
                int ciclosNecessarios = Integer.parseInt(dados[3].trim());
                String recursoNecessario = null;
                if (dados.length > 4){
                    recursoNecessario= dados[4].trim();
                }


            }
        }

    }
}