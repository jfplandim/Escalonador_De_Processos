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
        if(lista_bloqueados.getTamanho() > 0){
            Processos processoDesbloqueado = lista_bloqueados.removerDoInicio();
            if (processoDesbloqueado.prioridade == 1){
                lista_alta_prioridade.adicionarNoFinal(processoDesbloqueado);
            } else if (processoDesbloqueado.getPrioridade() == 2) {
                lista_media_prioridade.adicionarNoFinal(processoDesbloqueado);
            } else if (processoDesbloqueado.getPrioridade() == 3) {
               lista_baixa_prioridade.adicionarNoFinal(processoDesbloqueado);
            }
        }
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
        if (processoParaExecutar != null){
            if ("Disco".equals(processoParaExecutar.recuso_necessario)){ //logica de bloqueio;
                lista_bloqueados.adicionarNoFinal(processoParaExecutar);
                System.out.println("Processo " + processoParaExecutar.id + " bloqueado.");
                processoParaExecutar = null;
            }
        }
        if (processoParaExecutar != null){
            processoParaExecutar.ciclos_necessarios--;
            if(processoParaExecutar.ciclos_necessarios == 0){
                System.out.println("Processo " + processoParaExecutar.id + " finalizado.");
            }
            else{
                if(processoParaExecutar.prioridade == 1){
                    lista_alta_prioridade.adicionarNoFinal(processoParaExecutar);
                } else if (processoParaExecutar.prioridade == 2) {
                    lista_media_prioridade.adicionarNoFinal(processoParaExecutar);
                } else if (processoParaExecutar.prioridade == 3) {
                    lista_baixa_prioridade.adicionarNoFinal(processoParaExecutar);
                }
            }
        }
    }

}

