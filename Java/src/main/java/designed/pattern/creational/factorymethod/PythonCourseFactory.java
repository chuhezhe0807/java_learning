package designed.pattern.creational.factorymethod;

/**
 * ClassName: PythonFactory
 * Package: designed.pattern.creational.factorymethod
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/16 23:44
 * @Version 1.0
 */
public class PythonCourseFactory extends CourseFactory{
    @Override
    public Course getCourse() {
        return new PythonCourse();
    }
}
