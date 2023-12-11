/*
19050111022, FURKAN DEMİR
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
 */

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        /* generate primes upto 192007 (next biggest prime number after 81000)*/
        final ArrayList<Integer> primes = genPrimes(13142);
        long start, finish, timeSpent;
        ArrayList<Student> students = RandomStudentGenerator.getStudents();
        System.out.println("[INFO] Database statistics: ");
        printStats();

        System.out.println();
        start = System.currentTimeMillis();
        ChainedHashMap chainedHashMap = new ChainedHashMap(students);
        finish = System.currentTimeMillis();
        timeSpent = finish - start;
        System.out.println("Constructing HashMap with Chaining took: " + timeSpent + " ms");
        System.out.println();
        System.out.println("Random students from chaining approach:");
        start = System.currentTimeMillis();
        chainedHashMap.printStudents(100);
        finish = System.currentTimeMillis();
        timeSpent = finish - start;
        System.out.println();
        System.out.println("Fetching 100 random students from HashMap with Chaining took: " + timeSpent + " ms");
        System.out.println("\n**********************************************************\n");

        start = System.currentTimeMillis();
        ProbedHashMap probedHashMap = new ProbedHashMap(students, primes);
        finish = System.currentTimeMillis();
        timeSpent = finish - start;
        System.out.println("Constructing HashMap with OpenAddressing took: " + timeSpent + " ms");
        System.out.println();
        System.out.println("Random students from probing approach:");
        start = System.currentTimeMillis();
        probedHashMap.printStudents(100);
        finish = System.currentTimeMillis();
        timeSpent = finish - start;
        System.out.println();
        System.out.println("Fetching 100 random students from HashMap with OpenAddressing took: " + timeSpent + " ms");


        /*
        System.out.println();
        System.out.println("Number of students in our database: " + students.size());
        */

        /* sanity check if any student wasn't inserted into Probed HashMap */

        for (int i = 0; i < students.size(); i++) {
            if (!probedHashMap.getTable().contains(students.get(i))) {
                System.out.println("Student: " + students.get(i).ID + " at index: " + i + " is missing");
            }
        }


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

    /* check if a number is prime or not */
    private static boolean checkPrimality(int num) {
        /* Exit condition */
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / 2; i++) {
            if ((num % i) == 0)
                return false;
        }
        return true;
    }

    /* generate N prime numbers */
    private static ArrayList<Integer> genPrimes(int N) {
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

}
