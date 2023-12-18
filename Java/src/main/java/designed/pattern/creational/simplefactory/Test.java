package designed.pattern.creational.simplefactory;

/**
 * 有一个类管理需要创建什么对象
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
        CourseFactory courseFactory = new CourseFactory();
        Course java = courseFactory.getCourse("Java");
        java.make();
    }
}
