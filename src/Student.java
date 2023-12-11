/*
19050111022, FURKAN DEMİR
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
 */

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
