import java.util.ArrayList;

public class ProbedHashMap<K, V> extends AbstractHashMap<K, V>{
    /* global variables */
    private int a0, a1, b0, b1, h0, h1;

    /* Overridden Constructor */
    ProbedHashMap(int size){
        this.capacity = size;
        this.size = 0;
        nodes = new ArrayList<>(size);
    }

    int hashFunc0(int key) throws Exception {
        return ( ( a0 * key + b0 ) / 1001 ) & capacity;
    }

    int hashFunc1(int key) throws Exception {
            return ( ( a1 * key + b1 ) / 1001 ) & capacity;
    }

    @Override
    public int hashCode(K key, int i) throws Exception {
        if(key instanceof Integer){
            int k = (int)key;
            return hashFunc0(k) + ( i * hashFunc1(k));
        }
        else
            throw new Exception("Incompatible Generic Type Exception");
    }

}
