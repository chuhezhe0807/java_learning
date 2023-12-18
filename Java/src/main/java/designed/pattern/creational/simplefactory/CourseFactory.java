package designed.pattern.creational.simplefactory;

/**
 * ClassName: CourseFactory
 * Package: designed.pattern.creational.simplefactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/16 22:19
 * @Version 1.0
 */
public class CourseFactory {
    public Course getCourse(String course) {
        if("java".equalsIgnoreCase(course)) {
            return new JavaCourse();
        }
        else if("python".equalsIgnoreCase(course)) {
            return new PythonCourse();
        }
        else {
            return null;
        }
    }
}
