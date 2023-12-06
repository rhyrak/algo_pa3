import java.util.ArrayList;

public abstract class AbstractHashMap {

    int capacity;
    int size;

    ArrayList<Student> nodes;

    /* Override this in the concrete implementations */
    AbstractHashMap(){}

    /* Override this in the concrete implementations */
    public Student getNode(int key){return null;}

    public boolean isEmpty(){return size == 0;}

    /* Override this in the concrete implementations */
    public void createTable() {}

    public void printStudents() {}

    /* Override this in the concrete CHAINING implementations */
    public int hashCode(int key) throws Exception {
        return super.hashCode();
    }

    /* Override this in the concrete OPEN-ADDRESSING implementations */
    public int hashCode(int key, int i) throws Exception {
        return super.hashCode();
    }
}
