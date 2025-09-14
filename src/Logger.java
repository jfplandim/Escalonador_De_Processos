import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    // Nome do arquivo de log. Ele será criado na mesma pasta do seu programa.
    private static final String LOG_FILE = "log.txt";
    public static void log(String mensagem) {
        System.out.println(mensagem);


        try (FileWriter fw = new FileWriter(LOG_FILE, true); // O 'true' indica que a escrita será APPEND (adicionar ao final)
             PrintWriter pw = new PrintWriter(fw)) {
            String tempo = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            pw.println("[" + tempo + "] " + mensagem);

        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
}