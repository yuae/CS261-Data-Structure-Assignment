/**
 * Created by agodbout on 2017-10-04.
 */
public class Course {

    private String department;
    private int number;

    public Course(String dept, int num) {
        number = num;
        department = dept;
    }

    public int getCourseNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    public String toString() {
        String s = department + " " + number;
        return s;
    }
}
