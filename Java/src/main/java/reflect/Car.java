package reflect;

/**
 * ClassName: Car
 * Package: reflect
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/19 23:49
 * @Version 1.0
 */
public class Car {
    private String name;
    private int age;

    public Car() {

    }

    public Car(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void run() {
        System.out.println("private run....");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
