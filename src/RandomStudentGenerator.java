import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RandomStudentGenerator {
    private static ArrayList<Student> students = null;

    static {
        try (FileInputStream fileInputStream = new FileInputStream("students.db")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            students = (ArrayList<Student>) objectInputStream.readObject();
        } catch (Exception e) {
            students = generateStudents();

            try (FileOutputStream fileOutputStream = new FileOutputStream("students.db")) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(students);
            } catch (IOException ex) {
                System.out.println("Failed writing database to disk");
            }
        }

    }

    private static ArrayList<Student> generateStudents() {
        ArrayList<Student> generated = new ArrayList<>(81000);
        ArrayList<String> fnames = new ArrayList<>(7950);
        ArrayList<String> lnames = new ArrayList<>(81000);

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("female.txt"));
            while (scanner.hasNext())
                fnames.add(scanner.next());
            scanner = new Scanner(new File("male.txt"));
            while (scanner.hasNext())
                fnames.add(scanner.next());
            scanner = new Scanner(new File("last-names.txt"));
            while (scanner.hasNext())
                lnames.add(scanner.next());
        } catch (FileNotFoundException ex) {
            return null;
        }

        for (int i = 0; i < 81000; i++) {
            int department = i % 9 + 1;
            int faculty = i % 81 + 1;
            int year = 14 + i % 10;
            int rand = (int) (Math.random() * 999) + 1;
            Student student = new Student();
            student.name = fnames.get(i % fnames.size());
            student.lastName = lnames.get(i);
            student.department = String.format("Department #%02d", department);
            student.faculty = String.format("Department #%02d", faculty);
            student.ID = Integer.parseInt(String.format("%d%02d%02d%03d", year, faculty, department, rand));
            generated.add(student);
        }

        Collections.shuffle(generated);
        return generated;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
}
