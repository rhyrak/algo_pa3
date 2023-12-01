import java.util.ArrayList;

public class RandomStudentGenerator {
    private final static ArrayList<Student> students;
    static {
        students = new ArrayList<>(81000);
        students.add(new Student());
        students.get(0).name = "Bing";
        students.get(0).lastName = "Chilling";
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
}
