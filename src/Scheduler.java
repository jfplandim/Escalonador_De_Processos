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
        this.contador_ciclos_alta_prioridade = 0;
    }

    public void executarCicloCPU() {
        if (contador_ciclos_alta_prioridade >= 5) {
            Processos processoParaExecutar = null;
            if (lista_media_prioridade.tamanho > 0){
                processoParaExecutar = lista_media_prioridade.removerDoInicio();
            }
            if(processoParaExecutar != null) {
                contador_ciclos_alta_prioridade = 0;
            }
        }
    }
}
