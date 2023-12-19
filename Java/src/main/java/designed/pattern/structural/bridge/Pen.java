package designed.pattern.structural.bridge;

/**
 * ClassName: Pen
 * Package: designed.pattern.structural.bridge
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 23:36
 * @Version 1.0
 */
public class Pen extends Tools{
    private final Shape shape;

    public Pen(Shape shape) {
        super(shape);
        this.shape = shape;
    }

    @Override
    public void use() {
        shape.draw(" 钢笔 ");
    }
}
