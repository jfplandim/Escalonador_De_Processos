public class ProcessosBloqueados {
    private Node head;
    private Node tail;
    private int tamanho;

    public ProcessosBloqueados() {
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }

    // Metodo para adicionar um processo bloqueado no final da lista
    public void adicionar(Processos novoProcesso) {
        Node novoNo = new Node(novoProcesso);

        if (head == null) {
            // Se a lista estiver vazia, o novo nó é o head e o tail
            head = novoNo;
            tail = novoNo;
        } else {
            // Adiciona o novo nó no final da lista
            tail.next = novoNo;
            novoNo.anterior = tail;
            tail = novoNo;
        }
        tamanho++;
        System.out.println("Processo " + novoProcesso.getId() + " adicionado à lista de bloqueados.");
    }

    // Metodo para remover o primeiro processo da lista
    public Processos removerPrimeiro() {
        if (head == null) {
            System.out.println("A lista de processos bloqueados está vazia.");
            return null;
        }

        Processos processoRemovido = head.processo;
        head = head.next;
        if (head != null) {
            head.anterior = null;
        } else {
            // Se a lista ficar vazia após a remoção
            tail = null;
        }
        tamanho--;
        System.out.println("Processo " + processoRemovido.getId() + " removido da lista de bloqueados.");
        return processoRemovido;
    }

    // Metodo para verificar se a lista está vazia
    public boolean estaVazia() {
        return head == null;
    }

    // Metodo para obter o tamanho da lista
    public int getTamanho() {
        return tamanho;
    }

    // Metodo para imprimir a lista (exemplo de travessia)
    public void imprimirLista() {
        if (head == null) {
            System.out.println("A lista de processos bloqueados está vazia.");
            return;
        }

        Node atual = head;
        System.out.println("--- Lista de Processos Bloqueados ---");
        while (atual != null) {
            System.out.println("ID: " + atual.processo.getId() + ", Nome: " + atual.processo.getNome());
            atual = atual.next;
        }
        System.out.println("-------------------------------------");
    }
}