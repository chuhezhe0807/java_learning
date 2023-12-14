package designed.principle.openclose;

/**
 * ClassName: Student
 * Package: designed.principle
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/12 23:34
 * @Version 1.0
 */
public class Student implements Human{
    @Override
    public void eat() {
        System.out.println("学生在吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("学生在睡觉");
    }

    public void study() {
        System.out.println("学生在学习");
    }
}
