package designed.pattern.creational.factorymethod;

/**
 * ClassName: PythonCourse
 * Package: designed.pattern.creational.simplefactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/16 22:16
 * @Version 1.0
 */
public class PythonCourse extends Course {
    @Override
    public void make() {
        System.out.println("创建Python课程");
    }
}
