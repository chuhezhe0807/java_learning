package optional;

/**
 * ClassName: Student
 * Package: optional
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/28 23:58
 * @Version 1.0
 */
public class Student {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
