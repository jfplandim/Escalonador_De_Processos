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

