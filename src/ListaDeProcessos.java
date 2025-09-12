public class ListaDeProcessos {
    private Node head;
    private Node tail;
    private int tamanho;

    public ListaDeProcessos(){
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }

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

    public Processos removerDoInicio(){
        if(head == null){
            return null;
        }

        Processos processoRemovido = head.processo;
        if(head == tail){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.anterior = null;
        }
        tamanho--;
        return processoRemovido;
    }
}
