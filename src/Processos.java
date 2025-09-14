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

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCiclosNecessarios() {
        return ciclosNecessarios;
    }

    public void setCiclosNecessarios(int ciclosNecessarios) {
        this.ciclosNecessarios = ciclosNecessarios;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getRecursoNecessario() {
        return recursoNecessario;
    }

    public void setRecursoNecessario(String recursoNecessario) {
        this.recursoNecessario = recursoNecessario;
    }
}
