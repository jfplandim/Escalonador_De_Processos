import java.sql.SQLOutput;

public class Scheduler {
    private ListaDeProcessos lista_alta_prioridade;
    private ListaDeProcessos lista_media_prioridade;
    private ListaDeProcessos lista_baixa_prioridade;
    private ListaDeProcessos lista_bloqueados;
    private ListaCircular listaExecucao; //lista circular de execução

    private int contador_ciclos_alta_prioridade;
    private int ciclo_atual;

    /*public ListaDeProcessos getLista_alta_prioridade() {
        return lista_alta_prioridade;
    }

    public void setLista_alta_prioridade(ListaDeProcessos lista_alta_prioridade) {
        this.lista_alta_prioridade = lista_alta_prioridade;
    }

    public ListaDeProcessos getLista_media_prioridade() {
        return lista_media_prioridade;
    }

    public void setLista_media_prioridade(ListaDeProcessos lista_media_prioridade) {
        this.lista_media_prioridade = lista_media_prioridade;
    }

    public ListaDeProcessos getLista_baixa_prioridade() {
        return lista_baixa_prioridade;
    }

    public void setLista_baixa_prioridade(ListaDeProcessos lista_baixa_prioridade) {
        this.lista_baixa_prioridade = lista_baixa_prioridade;
    } */

    public Scheduler() {

        this.lista_alta_prioridade = new ListaDeProcessos();
        this.lista_media_prioridade = new ListaDeProcessos();
        this.lista_baixa_prioridade = new ListaDeProcessos();
        this.lista_bloqueados = new ListaDeProcessos();
        this.listaExecucao = new ListaCircular();
        this.contador_ciclos_alta_prioridade = 0;
        this.ciclo_atual = 0;
    }

    //adiciona o processo na fila correta de prioridade
    public void adicionar_Processo(Processos processo) {
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

    //desbloqueia processo mais antigo
    private void desbloquearProcesso() {
        //removeno da lista de desbloqueado
        if (lista_bloqueados.getTamanho() > 0) {
            Processos processoDesbloqueado = lista_bloqueados.removerDoInicio();

            //adicionando na lista de prioridade original
            if (processoDesbloqueado.getPrioridade() == 1) {
                lista_alta_prioridade.adicionarNoFinal(processoDesbloqueado);
            } else if (processoDesbloqueado.getPrioridade() == 2) {
                lista_media_prioridade.adicionarNoFinal(processoDesbloqueado);
            } else if (processoDesbloqueado.getPrioridade() == 3) {
                lista_baixa_prioridade.adicionarNoFinal(processoDesbloqueado);
            }
        }
    }

    //move para lista circular
    private void moverProcessosParaExecucao() {
        //Regra anti-inanição
        //Processos processoParaExecutar = null;
        if (contador_ciclos_alta_prioridade >= 5) {
            if (lista_media_prioridade.getTamanho() > 0) {
                //execução de media
                Processos processo = lista_media_prioridade.removerDoInicio();
                listaExecucao.adicionarInicio(processo);
                //execução de baixa
            } else if (lista_baixa_prioridade.getTamanho() > 0) {
                Processos processo = lista_baixa_prioridade.removerDoInicio();
                listaExecucao.adicionarInicio(processo);
            }
            //zera o contador
            contador_ciclos_alta_prioridade = 0;

        //execução padrao
        } else{
            if (lista_alta_prioridade.getTamanho() > 0) {
                Processos processo = lista_alta_prioridade.removerDoInicio();
                listaExecucao.adicionarInicio(processo);
                contador_ciclos_alta_prioridade++;

            } else if (lista_media_prioridade.getTamanho() > 0) {
                Processos processo = lista_media_prioridade.removerDoInicio();
                listaExecucao.adicionarInicio(processo);

            } else if (lista_baixa_prioridade.getTamanho() > 0) {
                Processos processo = lista_baixa_prioridade.removerDoInicio();
                listaExecucao.adicionarInicio(processo);
            }
        }
    }

    //executa o processo atual
    private void executarProcessoAtual() {
        if (listaExecucao.estaVazia()) {
            System.out.println("Nenhum processo na lista de execução.");
            return;
        }

        Processos processoAtual = listaExecucao.getAtual();
        System.out.println("Tentando executar: " + processoAtual);

        //verificando se precisa de DISCO
        if (processoAtual.precisaRecurso("DISCO")) {
            System.out.println("Processo " + processoAtual + " bloqueado por necessitar de DISCO");

            //remove da lista e adiciona nos bloqueados
            listaExecucao.removerAtual();
            lista_bloqueados.adicionarNoFinal(processoAtual);

            //marcar processo para n bloquear novamente
            processoAtual.marcarRecurso();
            return;
        }

        //executa o processo
        processoAtual.executar();
        System.out.println("Executado: " + processoAtual +
                " (Ciclos restantes: " + processoAtual.getCiclosNecessarios() + ")");

        // Verificar se processo terminou
        if (processoAtual.getCiclosNecessarios() <= 0) {
            System.out.println("Processo terminado: " + processoAtual);
            listaExecucao.removerAtual();

        } else {
            System.out.println("Processo continua na lista circular: " + processoAtual);
            listaExecucao.avancar();
        }
    }

    //imprimir estado
    private void imprimirEstado(){
        System.out.println("\n--- ESTADO DO SISTEMA ---");

        System.out.print("Lista Alta Prioridade: ");
        lista_alta_prioridade.imprimirLista();

        System.out.print("Lista Média Prioridade: ");
        lista_media_prioridade.imprimirLista();

        System.out.print("Lista Baixa Prioridade: ");
        lista_baixa_prioridade.imprimirLista();

        System.out.print("Lista Bloqueados: ");
        lista_bloqueados.imprimirLista();

        System.out.print("Lista Execução Circular: ");
        listaExecucao.imprimirLista();

        System.out.println("Contador Alta Prioridade: " + contador_ciclos_alta_prioridade);
        System.out.println("Ciclo Atual: " + ciclo_atual);
    }

    //executa um ciclo
    public void executarCicloDeCPU(){
        ciclo_atual++;
        System.out.println("CICLO: " + ciclo_atual);

        desbloquearProcesso();

        moverProcessosParaExecucao();

        executarProcessoAtual();

        imprimirEstado();
    }

    public boolean temProcesso(){
        return  !lista_alta_prioridade.estaVazia() ||
                !lista_media_prioridade.estaVazia() ||
                !lista_baixa_prioridade.estaVazia() ||
                !lista_bloqueados.estaVazia() ||
                !listaExecucao.estaVazia();
    }

    //execução completa
    public void execucaoCompleta(){
        System.out.println("\n INICIANDO ESCALONADOR DE PROCESSOS");

        while (temProcesso()){
            executarCicloDeCPU();
        }

        System.out.println("ESCALONADOR FINALIZADO");
        System.out.println("Total de ciclos executados: " +ciclo_atual);
    }

}





