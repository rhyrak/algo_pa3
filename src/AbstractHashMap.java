import java.util.ArrayList;

public abstract class AbstractHashMap<K, V> {

    int capacity;
    int size;

    ArrayList<Node<K, V>> nodes;

    /* Override this in the concrete implementations */
    AbstractHashMap(){}

    /* Override this in the concrete implementations */
    public void insertNode(K key, V value){}

    /* Override this in the concrete implementations */
    public void deleteNode(K key, V value){}

    /* Override this in the concrete implementations */
    public V getNode(K key){return null;}

    public boolean isEmpty(){return size == 0;}

    /* Override this in the concrete CHAINING implementations */
    public int hashCode(K key) throws Exception {
        return super.hashCode();
    }

    /* Override this in the concrete OPEN-ADDRESSING implementations */
    public int hashCode(K key, int i) throws Exception {
        return super.hashCode();
    }
}
