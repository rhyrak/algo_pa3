import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    ArrayList<Integer> primes;
    public static void main(String[] args) {
        for (int i = 0; i < 81; i++) {
            System.out.println(RandomStudentGenerator.getStudents().get(i));
        }

        printStats();
    }

    private static void printStats() {
        int[] departmentSize = new int[10];
        int[] facultySize = new int[82];
        int[] randSize = new int[1000];
        int[] yearsSize = new int[24];

        int idCollisionCount = 0;
        HashMap<Integer, Student> map = new HashMap<>(81000);

        for (Student s : RandomStudentGenerator.getStudents()) {
            // YY-FF-DD-NNN
            randSize[s.ID % 1000]++;
            departmentSize[s.ID / 1000 % 100]++;
            facultySize[s.ID / 100000 % 100]++;
            yearsSize[s.ID / 10000000 % 100]++;
            if (map.containsKey(s.ID))
                idCollisionCount++;
            else
                map.put(s.ID, s);
        }

        System.out.println("Department population:");
        for (int i = 1; i < departmentSize.length; i++)
            System.out.print(departmentSize[i] + " ");
        System.out.println();
        System.out.println("Faculty population:");
        for (int i = 1; i < facultySize.length; i++)
            System.out.print(facultySize[i] + " ");
        System.out.println();
        System.out.println("Year population:");
        for (int i = 14; i < yearsSize.length; i++)
            System.out.print(yearsSize[i] + " ");
        System.out.println();
        System.out.println("rand population:");
        for (int i = 1; i < randSize.length; i++)
            System.out.print(randSize[i] + " ");
        System.out.println();
        System.out.println("ID collisions: " + idCollisionCount);
    }

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

    ArrayList<Integer> genPrimes(int size) {
        ArrayList<Integer> arr = new ArrayList<>(size);
        int z = 0;
        for (int i = 1; z < size; i++) {
            if (checkPrimality(i)) {
                arr.add(i);
                z++;
            }
        }
        return arr;
    }

}
