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

    public void printStudents(int count) {}

    /* Override this in the concrete CHAINING implementations */
    public int hashCode(int key){
        return super.hashCode();
    }

    /* Override this in the concrete OPEN-ADDRESSING implementations */
    public int hashCode(int key, int i) {
        return super.hashCode();
    }
}
