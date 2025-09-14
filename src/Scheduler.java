public class Scheduler {
    private ListaDeProcessos lista_alta_prioridade;
    private ListaDeProcessos lista_media_prioridade;
    private ListaDeProcessos lista_baixa_prioridade;
    private ListaDeProcessos lista_bloqueados;

    private int contador_ciclos_alta_prioridade;

    public Scheduler() {
        this.lista_alta_prioridade = new ListaDeProcessos();
        this.lista_media_prioridade = new ListaDeProcessos();
        this.lista_baixa_prioridade = new ListaDeProcessos();
        this.lista_bloqueados = new ListaDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }

    public void executarCicloCPU() {
        Processos processoParaExecutar = null;
        if (contador_ciclos_alta_prioridade >= 5) {
            if (lista_media_prioridade.tamanho > 0){
                processoParaExecutar = lista_media_prioridade.removerDoInicio();
                contador_ciclos_alta_prioridade = 0;
            }
            else if (lista_baixa_prioridade.tamanho > 0) {
                processoParaExecutar = lista_baixa_prioridade.removerDoInicio();
                contador_ciclos_alta_prioridade = 0;
            }
        }
        else if (lista_alta_prioridade.tamanho > 0) {
    processoParaExecutar = lista_alta_prioridade.removerDoInicio();
    contador_ciclos_alta_prioridade++;
        }
        else if(lista_media_prioridade.tamanho > 0){
            processoParaExecutar = lista_media_prioridade.removerDoInicio();
            contador_ciclos_alta_prioridade = 0;
            }
        else if(lista_baixa_prioridade.tamanho > 0){
            processoParaExecutar = lista_baixa_prioridade.removerDoInicio();
            contador_ciclos_alta_prioridade = 0;
        }
    }

}

