import java.util.ArrayList;
import java.util.Random;

public class ProbedHashMap extends AbstractHashMap {
    /* global variables */
    private final int a0, a1, b0, b1, ratio;
    private ArrayList<Student> table;
    private final ArrayList<Integer> primes;

    /* Overridden Constructor */
    ProbedHashMap(ArrayList<Student> list){
        /* initialize all those variables */
        nodes = list;
        capacity = 401;
        size = 0;
        a0 = 3;
        b0 = 17;
        a1 = 19;
        b1 = 37;
        ratio = 80; /* double the table after it is 80% full */
        primes = genPrimes(13142); /* generate primes upto 192007 (next biggest prime number after 81000)*/
        table = new ArrayList<>(401);
    }


    public void printStudents(int count) {
        createTable();
        Random rand = new Random();
        for(int i = 0; i < count; i++) {
            System.out.println(this.getNode(nodes.get(rand.nextInt(nodes.size())).ID));
        }
    }

    /* create HashMap dataset from provided students list */
    @Override
    public void createTable() {
        initTable(); /* nullify all indices */
        /* iterate over students list */
        for(Student s : nodes){
            /* check fullness */
            if((size / capacity) * 100 > ratio){
                /* double the table if more than 80% full */
                doubleTable();
                return;
            }

            int index;
            index = hashCode(s.ID, 1);

            /* check collision */
            if(table.get(index) == null){
                table.set(index, s);
                size++;
            }
            else{
                int p = 1;
                /* check collision */
                while(table.get(index) != null && p < capacity){
                    index = hashCode(s.ID, ++p);
                }

                /* couldn't find any optimal slot, double the table */
                if(p >= capacity){
                    doubleTable();
                    return;
                }

                /* insert student at valid index */
                table.set(index, s);
                size++;
            }
        }
    }

    /* retrieve a student using their key */
    @Override
    public Student getNode(int key) {
        int index = hashCode(key, 1);
        /* check for null elements first, then check for collisions (avoids NPE) */
        if (table.get(index) == null || table.get(index) != null && table.get(index).ID != key) {
            int p = 1;
            boolean stop = false;
            /* iterate indefinitely until the student is found or until we iterate over the whole table */
            while (!stop) {
                if(table.get(index) == null && p < capacity){
                    index = hashCode(key, ++p);
                }
                else if(table.get(index) != null &&  table.get(index).ID != key && p < capacity){
                    index = hashCode(key, ++p);
                }
                else{
                    stop = true;
                }
            }

            /* student isn't in table */
            if (p >= capacity) {
                return null;
            }
        }
        /* return student */
        return table.get(index);
    }

    /* double the size of the table and rehash everything */
    public void doubleTable() {
        int i = 0;
        int newCapacity = capacity * 2;
        boolean stop = false;
        /* find next biggest prime */
        do{
            if(primes.get(i) > newCapacity){
                newCapacity = primes.get(i);
                stop = true;
            }
            i++;
        }while(!stop);

        //System.out.println("Doubled with new size: " + capacity);
        /* resize the table */
        capacity = newCapacity;
        size = 0;
        table = new ArrayList<>(capacity);
        /* rehash every element */
        createTable();
    }

    /* zero out the table */
    public void initTable(){
        for(int i = 0; i < capacity; i++){
            table.add(i, null);
        }
    }

    /* check if a number is prime or not */
    boolean checkPrimality(int num)
    {
        /* Exit condition */
        if(num <= 1)
        {
            return false;
        }
        for(int i = 2; i <= num / 2; i++)
        {
            if((num % i) == 0)
                return false;
        }
        return true;
    }

    /* generate N prime numbers */
    ArrayList<Integer> genPrimes(int N) {
        ArrayList<Integer> arr = new ArrayList<>(N);
        int z = 0;
        for (int i = 1; z < N; i++) {
            if (checkPrimality(i)) {
                arr.add(i);
                z++;
            }
        }
        return arr;
    }

    /* sub-hash function */
    int hashFunc0(int key){
        int x = ( ( a0 * key + b0 ) / 1001 ) % capacity;
        if(x < 0){
            x = x + capacity;
        }
        //System.out.println(x);
        return x;
    }

    /* sub-hash function */
    int hashFunc1(int key) {
        int x = ( ( a1 * key + b1 ) / 1001 ) % capacity;
        if(x < 0){
            x = x + capacity;
        }
        //System.out.println(x);
        return x;
    }

    /* THE hash function */
    @Override
    public int hashCode(int key, int i) {
        int x = (hashFunc0(key) + ( i * hashFunc1(key))) % capacity;
        if(x < 0){
            x = x + capacity;
        }
        //System.out.println(x);
        return x;
    }

    public ArrayList<Student> getTable(){
        return table;
    }

}