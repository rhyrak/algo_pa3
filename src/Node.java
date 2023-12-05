public class Node<K, V> {
    K key;
    V value;
    int hash;
    private Node<K, V> next;

    Node(K key, V value){
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public void updateNext(Node<K, V> next){
        this.next = next;
    }

    public Node<K, V> getNext(){
        return this.next;
    }
}
