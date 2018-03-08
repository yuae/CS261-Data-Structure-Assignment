import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by agodbout on 2017-10-04.
 */
public class Task1 {

    public static void coursePrinter(Predicate<Course> p, List<Course> courses) {

        for(Course course: courses ){
            if(p.test(course)) {
                System.out.println(course);
            }
        }
    }

    public static void main(String[]args) {
        //Create some courses
        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(new Course("CS", 2610));
        courses.add(new Course("CS", 1910));
        courses.add(new Course("CS", 1920));
        courses.add(new Course("CS", 2130));
        courses.add(new Course("CS", 2820));
        courses.add(new Course("CS", 3410));
        courses.add(new Course("CS", 4820));
        courses.add(new Course("MATH", 1920));
        courses.add(new Course("MATH", 1920));
        courses.add(new Course("ENG", 2010));
        courses.add(new Course("ECON", 2910));


        //TODO 1: uncomment the following line and add a lambda expression as the first parameter
        //if x's department equals to "CS" and its number is less than 3000
        coursePrinter(x->x.getDepartment().equals("CS")&&x.getCourseNumber()<3000, courses);

    }


}
