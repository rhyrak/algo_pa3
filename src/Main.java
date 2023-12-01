
public class Main {
    public static void main(String[] args) {
        System.out.println(RandomStudentGenerator.getStudents().get(0).name + " "
                + RandomStudentGenerator.getStudents().get(0).lastName);
        
        printStats();
    }

    private static void printStats() {
        int[] departmentSize = new int[10];
        int[] facultySize = new int[82];
        int[] randSize = new int[1000];
        int[] yearsSize = new int[24];

        for (Student s : RandomStudentGenerator.getStudents()) {
            // YY-FF-DD-NNN
            randSize[s.ID % 1000]++;
            departmentSize[s.ID / 1000 % 100]++;
            facultySize[s.ID / 100000 % 100]++;
            yearsSize[s.ID / 10000000 % 100]++;
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
    }
}