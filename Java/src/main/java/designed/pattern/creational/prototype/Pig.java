package designed.pattern.creational.prototype;

/**
 * ClassName: Pig
 * Package: designed.pattern.creational.prototype
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 18:40
 * @Version 1.0
 */
public class Pig implements Cloneable{
    private String name;
    private String doSomeThing;

    public Pig() {
        System.out.println("pig init...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoSomeThing() {
        return doSomeThing;
    }

    public void setDoSomeThing(String doSomeThing) {
        this.doSomeThing = doSomeThing;
    }

    @Override
    public String toString() {
        return "Pig{" +
                "name='" + name + '\'' +
                ", doSomeThing='" + doSomeThing + '\'' +
                '}' + super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
