public class Scheduler {
    private ListaDeProcessos lista_alta_prioridade;
    private ListaDeProcessos lista_media_prioridade;
    private ListaDeProcessos lista_baixa_prioridade;
    private ListaDeProcessos lista_bloqueados;
    private ListaCircular listaExecucao; //lista circular de execução

    private int contador_ciclos_alta_prioridade;

    public Scheduler() {
        this.lista_alta_prioridade = new ListaDeProcessos();
        this.lista_media_prioridade = new ListaDeProcessos();
        this.lista_baixa_prioridade = new ListaDeProcessos();
        this.lista_bloqueados = new ListaDeProcessos();
        this.listaExecucao = new ListaCircular();
        this.contador_ciclos_alta_prioridade = 0;
    }

    //adiciona o processo na fila correta de prioridade
    public void adicionarProcesso(Processos processo) {
        switch (processo.getPrioridade()) {
            case 1:
                lista_alta_prioridade.adicionarNoFinal(processo);
                break;
            case 2:
                lista_media_prioridade.adicionarNoFinal(processo);
                break;
            case 3:
                lista_baixa_prioridade.adicionarNoFinal(processo);
                break;
        }
    }


    public void executarCicloCPU() {
        if(lista_bloqueados.getTamanho() > 0){
            Processos processoDesbloqueado = lista_bloqueados.removerDoInicio();
            if (processoDesbloqueado.getPrioridade() == 1){
                lista_alta_prioridade.adicionarNoFinal(processoDesbloqueado);
            } else if (processoDesbloqueado.getPrioridade() == 2) {
                lista_media_prioridade.adicionarNoFinal(processoDesbloqueado);
            } else if (processoDesbloqueado.getPrioridade() == 3) {
                lista_baixa_prioridade.adicionarNoFinal(processoDesbloqueado);
            }
        }
        Processos processoParaExecutar = null;
        if (contador_ciclos_alta_prioridade >= 5) {
            if (lista_media_prioridade.getTamanho() > 0){
                processoParaExecutar = lista_media_prioridade.removerDoInicio();
                contador_ciclos_alta_prioridade = 0;
            }
            else if (lista_baixa_prioridade.getTamanho() > 0) {
                processoParaExecutar = lista_baixa_prioridade.removerDoInicio();
                contador_ciclos_alta_prioridade = 0;
            }
        }
        else if (lista_alta_prioridade.getTamanho() > 0) {
            processoParaExecutar = lista_alta_prioridade.removerDoInicio();
            contador_ciclos_alta_prioridade++;
        }
        else if(lista_media_prioridade.getTamanho() > 0){
            processoParaExecutar = lista_media_prioridade.removerDoInicio();
            contador_ciclos_alta_prioridade = 0;
        }
        else if(lista_baixa_prioridade.getTamanho() > 0){
            processoParaExecutar = lista_baixa_prioridade.removerDoInicio();
            contador_ciclos_alta_prioridade = 0;
        }
        if (processoParaExecutar != null){
            if ("Disco".equals(processoParaExecutar.getRecursoNecessario())){ //logica de bloqueio;
                lista_bloqueados.adicionarNoFinal(processoParaExecutar);
                System.out.println("Processo " + processoParaExecutar.getId() + " bloqueado.");
                processoParaExecutar = null;
            }
        }
        if (processoParaExecutar != null){
            processoParaExecutar.getCiclosNecessarios();
            if(processoParaExecutar.getCiclosNecessarios() == 0){
                System.out.println("Processo " + processoParaExecutar.getId() + " finalizado.");
            }
            else{
                if(processoParaExecutar.getPrioridade() == 1){
                    lista_alta_prioridade.adicionarNoFinal(processoParaExecutar);
                } else if (processoParaExecutar.getPrioridade() == 2) {
                    lista_media_prioridade.adicionarNoFinal(processoParaExecutar);
                } else if (processoParaExecutar.getPrioridade() == 3) {
                    lista_baixa_prioridade.adicionarNoFinal(processoParaExecutar);
                }
            }
        }
    }

}

