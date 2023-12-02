import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public String lastName;
    public int ID;
    public String department;
    public String faculty;

    @Override
    public String toString() {
        return String.format("%d %s %s %s %s", ID, name, lastName, department, faculty);
    }
}
