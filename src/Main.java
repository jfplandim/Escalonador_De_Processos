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
                Processos novoProcesso = new Processos(id, nome, prioridade,ciclosNecessarios, recursoNecessario); //ajeitar, pq tem isso tanto na classe scheduler quanto na main
                switch (prioridade){
                    case 1: scheduler.getLista_alta_prioridade().adicionarNoFinal(novoProcesso);
                    case 2: scheduler.getLista_media_prioridade().adicionarNoFinal(novoProcesso);
                    case 3: scheduler.getLista_baixa_prioridade().adicionarNoFinal(novoProcesso);
                    break;
                }
            }
        }
        catch (FileNotFoundException e){
            System.err.println("Arquivo não encontrado.");
            e.printStackTrace();
            return;
        }
        System.out.println("Simulação do escalonar iniciada.");
        System.out.println("Simulação finalizada.");


    }
}