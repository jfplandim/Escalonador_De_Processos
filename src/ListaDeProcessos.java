public class ListaDeProcessos {
    private Node head;
    private Node tail;
    private int tamanho;

    public ListaDeProcessos(){
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }

    //adiciona no final
    public void adicionarNoFinal(Processos processo){
        Node newNode = new Node(processo);

        if(head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.anterior = tail;
            tail = newNode;
        }
        tamanho++;
    }

    //remove do inicio
    public Processos removerDoInicio(){
        if(head == null){
            return null; //nada para remover
        }

        Processos processoRemovido = head.processo;

        //lista com apenas um elemento
        if(head == tail){
            head = null;
            tail = null;
        } else {
            //remover o ultimo
            head=head.next;//muda a referencia
            head.anterior = null;
        }
        tamanho--;
        return processoRemovido;
    }
    public boolean estaVazia() {
        return head == null;
    }
    public int getTamanho() {
        return this.tamanho;
    }
    public void imprimirLista() {
        if (head == null) {
            System.out.println("Vazia");
            return;
        }
        Node atual = head;
        while (atual != null) {
            System.out.print(atual.processo.getId() + " ");
            atual = atual.next;
        }
        System.out.println();
    }
}
