public class Node {
    Processos processo;
    Node next;
    Node anterior;
    public Node(Processos processo){
        this.processo = processo;
        this.next = null;
        this.anterior = null;
    }
}