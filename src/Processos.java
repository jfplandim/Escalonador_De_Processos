public class Processos {
    private int id;
    private String nome;
    private int prioridade; // 1-Alta, 2-Média e 3-Baixa
    private int ciclosNecessarios;
    private String recursoNecessario;
    private boolean recursoSolicitado;

    //construtor
    public Processos (int id, String nome, int prioridade, int ciclosNecessarios, String recursoNecessario){
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclosNecessarios = ciclosNecessarios;
        this.recursoNecessario = recursoNecessario;
    }

    //verifica se o processo solicita recurso e evita ser bloqueado outrs vezes
    public boolean precisaRecurso(String recurso) {
        return recurso.equals(recursoNecessario) && !recursoSolicitado;
    }

    public void marcarRecurso() {
        this.recursoSolicitado = true;
    }

    //metodo de execução
    public void executar() {
        this.ciclosNecessarios--;
    }

    public int getId() {
        return id;
    }


    public int getCiclosNecessarios() {
        return ciclosNecessarios;
    }


    public int getPrioridade() {
        return prioridade;
    }


//metodo string da classe processos
    @Override
    public String toString() {
        return String.format("P%d(%s,Pr:%d,Ciclos:%d)", id, nome, prioridade, ciclosNecessarios);
    }
}
