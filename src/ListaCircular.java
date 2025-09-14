public class ListaCircular {
    private Node atual;
    private int tamanho;

    public void adicionarInicio(Processos processo){
        Node novo = new Node(processo);
        if(atual == null){
            atual = novo;
            atual.next = atual;
            atual.anterior = atual;
        }
        else{
            Node ultimo = atual.anterior;
            ultimo.next = novo;
            novo.anterior = ultimo;
            novo.next = atual;
            atual.anterior = novo;
            atual = novo;
        }
        tamanho++;
    }
    public void adicionarFim(Processos processo){
        Node novo = new Node(processo);
        if (atual == null){
            atual = novo;
            atual.next = atual;
            atual.anterior = atual;
        }
        else{
            Node ultimo = atual.anterior;
            ultimo.next = novo;
            novo.anterior = ultimo;
            novo.next = atual;
            atual.anterior = novo;
        }
        tamanho++;
    }
    public Processos getAtual(){
        return atual != null ? atual.processo : null;
    }
    public void avancar(){
        if(atual != null) atual = atual.next;
    }
    public Processos removerAtual(){
        if(atual == null) return null;
        Processos processos = atual.processo;
        if(atual.next==atual){
            atual=null;
        }
        else{
            Node prox = atual.next;
            atual.anterior.next = prox;
            prox.anterior = atual.anterior;
            atual = prox;
        }
        tamanho--;
        return processos;
    }
    public int getTamanho(){
        return tamanho;
    }

    // Metodo para verificar se a lista está vazia
    public boolean estaVazia() {
        return atual == null;
    }
    public void imprimirLista() {
        if (atual == null) {
            System.out.println("Vazia");
            return;
        }
        Node primeiro = atual;
        do {
            System.out.print(primeiro.processo.getId() + " ");
            primeiro = primeiro.next;
        } while (primeiro != atual);
        System.out.println();
    }
}