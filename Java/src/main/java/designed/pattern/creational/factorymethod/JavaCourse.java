package designed.pattern.creational.factorymethod;

/**
 * ClassName: JavaCourse
 * Package: designed.pattern.creational.simplefactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/16 22:14
 * @Version 1.0
 */
public class JavaCourse extends Course {
    @Override
    public void make() {
        System.out.println("创建Java课程");
    }
}
