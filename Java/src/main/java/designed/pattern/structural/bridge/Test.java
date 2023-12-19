package designed.pattern.structural.bridge;

/**
 * ClassName: Test
 * Package: designed.pattern.structural.bridge
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 23:38
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Pencil pencil = new Pencil(new Circle());
        pencil.use();

        Pen pen = new Pen(new Square());
        pen.use();
    }
}
