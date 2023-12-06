import java.util.ArrayList;

public class ProbedHashMap extends AbstractHashMap {
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

    public void printStudents() {
        System.out.println("I am probed approach!");
    }

    @Override
    public int hashCode(int key, int i) throws Exception {
            return hashFunc0(key) + ( i * hashFunc1(key));
    }

    @Override
    public void createTable() {
        // TODO Auto-generated method stub
        super.createTable();
    }

    @Override
    public Student getNode(int key) {
        // TODO Auto-generated method stub
        return super.getNode(key);
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return super.isEmpty();
    }

}
