import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ChainedHashMap extends AbstractHashMap {
    int a, b;
    int print;
    LinkedList<Student>[] table = new LinkedList[4001];

    public ChainedHashMap(ArrayList<Student> list, int print) {
        nodes = list;
        this.print = print;
        a = 3;
        b = 17;
        for(int i = 0; i < 4001; i++)
            table[i] = new LinkedList<Student>();
    }   

    @Override
    public void printStudents() {
        createTable();
        Random rand = new Random();
        for(int i = 0; i < print; i++) {
            System.out.println(this.getNode(nodes.get(rand.nextInt(nodes.size())).ID));
        }
    }
    @Override
    public void createTable()  {
        int index;
        for(Student s : nodes) {
            index = hashCode(s.ID);
            table[index].addFirst(s);
            size++;
        }
    }

    @Override
    public int hashCode(int key) {
        return (a*key + b)/1001 % 4001;
    }

    @Override
    public Student getNode(int key) {
        int index = hashCode((int) key);
        for(Student s : table[index]) {
            if(((Student) s).ID == key)
                return s;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

}
