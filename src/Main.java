import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = RandomStudentGenerator.getStudents();
        printStats();
        System.out.println();
        ChainedHashMap chainedHashMap = new ChainedHashMap(students);
        System.out.println("Random students from chaining approach:");
        chainedHashMap.printStudents(100);
        System.out.println("\n**********************************************************\n");

        ProbedHashMap probedHashMap = new ProbedHashMap(students);
        System.out.println("Random students from probing approach:");
        probedHashMap.printStudents(100);

        /*
        System.out.println();
        System.out.println("Number of students in our database: " + students.size());
        */

        /* sanity check if any student wasn't inserted into Probed HashMap */

        for(int i = 0; i < students.size(); i++){
            if(!probedHashMap.getTable().contains(students.get(i))){
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

}
