package designed.pattern.creational.factorymethod;

/**
 * 相较于简单工厂，不通过一个类去管理需要创建什么对象，而是创建什么类就需要new什么工厂
 *
 * ClassName: Test
 * Package: designed.pattern.creational.simplefactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/16 22:46
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        Course course = javaCourseFactory.getCourse();
        course.make();

        PythonCourseFactory pythonCourseFactory = new PythonCourseFactory();
        Course pythonCourse = pythonCourseFactory.getCourse();
        pythonCourse.make();
    }
}
