package designed.pattern.creational.factorymethod;

/**
 * ClassName: JavaCourseFactory
 * Package: designed.pattern.creational.factorymethod
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/16 23:42
 * @Version 1.0
 */
public class JavaCourseFactory extends CourseFactory{
    @Override
    public Course getCourse() {
        return new JavaCourse();
    }
}
