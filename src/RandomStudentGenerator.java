/*
19050111022, FURKAN DEMİR
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
 */

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

        long start, end;
        start = System.nanoTime();
        int[][] rands = new int[810][999]; // ~3MB
        for (int i = 0; i < 999; i++)
            rands[0][i] = i + 1;
        for (int i = 1; i < 810; i++)
            System.arraycopy(rands[0], 0, rands[i], 0, 999);
        end = System.nanoTime();
        // ~1ms
        // System.out.println("[Time] create NNN array: " + (end - start));

        start = System.nanoTime();
        for (int i = 0; i < 81000; i++) {
            int department = i % 9 + 1;
            int faculty = i % 81 + 1;
            int year = 14 + i % 10;
            // LCM(9,81,10) = 810
            // i = 0  -> dep: 1 fac: 1 year: 14
            // i = 810 -> dep: 1 fac: 1 year: 14
            // dep = year = fac iff i % 810 = 0

            int availables = 999 - i / 810;
            int randIndex = (int) (Math.random() * availables);
            int rand = rands[i % 810][randIndex];
            rands[i % 810][randIndex] = rands[i % 810][availables - 1];

            Student student = new Student();
            student.name = fnames.get(i % fnames.size());
            student.lastName = lnames.get(i);
            student.department = String.format("Department #%02d", department);
            student.faculty = String.format("Faculty #%02d", faculty);
            student.ID = Integer.parseInt(String.format("%d%02d%02d%03d", year, faculty, department, rand));
            generated.add(student);
        }
        Collections.shuffle(generated);
        end = System.nanoTime();

        // ~500ms
        // System.out.println("[Time] generate db: " + (end - start));

        return generated;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
}
