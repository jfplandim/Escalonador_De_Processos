import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeArquivo {

    public static void lerEAdicionarProcessos(String caminhoDoArquivo, Escalonador escalonador) {

        // O bloco 'try-with-resources' garante que o arquivo será fechado automaticamente,
        // mesmo que ocorra um erro durante a leitura.
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            String linha;

            // Este laço de repetição lê o arquivo linha por linha até o final.
            // A condição `(linha = leitor.readLine()) != null` lê a linha e a atribui
            // à variável `linha`, verificando se ela não é nula (ou seja, se o arquivo não terminou).
            while ((linha = leitor.readLine()) != null) {

                // Divide a linha em um array de strings usando a vírgula como separador.
                // Exemplo: a linha "1,Processo A,1,10,recurso_X" se torna um array com 5 elementos.
                String[] dados = linha.split(",");

                // Verifica se a linha tem o número correto de colunas (5, neste caso).
                if (dados.length == 5) {
                    try {
                        // Converte a string do primeiro item do array (o ID) para um número inteiro.
                        // O método `trim()` remove espaços em branco extras, garantindo uma conversão correta.
                        int id = Integer.parseInt(dados[0].trim());

                        // Pega os outros dados do array. Eles já são strings, então não precisam de conversão.
                        String nome = dados[1].trim();

                        // Converte a prioridade (string) para um número inteiro.
                        int prioridade = Integer.parseInt(dados[2].trim());

                        // Converte a quantidade de ciclos necessários (string) para um número inteiro.
                        int ciclosNecessarios = Integer.parseInt(dados[3].trim());

                        String recursoNecessario = dados[4].trim();

                        // Cria um novo objeto `Processo` com os dados que foram lidos e convertidos.
                        Processo novoProcesso = new Processo(id, nome, prioridade, ciclosNecessarios, recursoNecessario);

                        // Adiciona o processo recém-criado diretamente ao escalonador. O Escalonador
                        // é responsável por colocá-lo na fila de prioridade correta.
                        escalonador.adicionarProcesso(novoProcesso);
                    } catch (NumberFormatException e) {
                        // Este bloco captura erros se algum dado do arquivo não puder ser convertido para um número.
                        System.err.println("Erro ao converter um número na linha: " + linha);
                    }
                }
            }
            // Esta mensagem é exibida no console após o carregamento bem-sucedido de todos os processos.
            System.out.println("Processos carregados com sucesso do arquivo.");
        } catch (IOException e) {
            // Este bloco captura erros se o arquivo não puder ser encontrado ou se houver um problema de leitura.
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}