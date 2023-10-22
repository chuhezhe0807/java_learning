package annotation;

/**
 * ClassName: Student
 * Package: com.chuhezhe.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/3 17:28
 * @Version 1.0
 */
public class Student extends Person{
    @Override
    public void overrideMethod() {
        super.overrideMethod();
    }

    public int returnInt() {
        return 1;
    }

    public int returnInt(Integer integer) {
        return integer;
    }
}
