package designed.principle.openclose;

/**
 * ClassName: HighSchoolStudent
 * Package: designed.principle.openclose
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/12 23:40
 * @Version 1.0
 */
public class HighSchoolStudent extends Student{
    @Override
    public void eat() {
        System.out.println("高中生在吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("高中生在睡觉");
    }

    @Override
    public void study() {
        System.out.println("高中生在学习");
    }

    public void doSomething() {
        System.out.println("我是一个高中生");
    }
}
