import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // Verifica se o nome do arquivo foi fornecido como argumento de linha de comando.
        if (args.length == 0) {
            System.err.println("Erro: Por favor, forneça o nome do arquivo de dados como argumento.");
            return; // Encerra o programa se nenhum argumento for fornecido.
        }

        String nomeArquivo = args[0];
        Scheduler scheduler = new Scheduler();

        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(",");

                if (dados.length < 4) {
                    System.err.println("Aviso: Linha ignorada devido a formato incorreto: " + linha);
                    continue; // Pula para a próxima linha se não houver dados suficientes.
                }

                try {
                    int id = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    int prioridade = Integer.parseInt(dados[2].trim());
                    int ciclosNecessarios = Integer.parseInt(dados[3].trim());
                    String recursoNecessario = null;
                    if (dados.length > 4) {
                        recursoNecessario = dados[4].trim();
                    }

                    Processos novoProcesso = new Processos(id, nome, prioridade, ciclosNecessarios, recursoNecessario);
                    scheduler.adicionar_Processo(novoProcesso); // Adiciona o processo ao escalonador.
                } catch (NumberFormatException e) {
                    System.err.println("Erro de formato de número na linha: " + linha);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo '" + nomeArquivo + "' não encontrado.");
            return;
        }
        System.out.println("Processos carregados com sucesso. Iniciando simulação...");
        scheduler.execucaoCompleta(); // Chama o metodo para iniciar a simulação completa.
    }
}