package designed.pattern.structural.bridge;

/**
 * ClassName: Pecil
 * Package: designed.pattern.structural.bridge
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 23:34
 * @Version 1.0
 */
public class Pencil extends Tools{
    private final Shape shape;

    public Pencil(Shape shape) {
        super(shape);
        this.shape = shape;
    }

    @Override
    public void use() {
        shape.draw(" 铅笔 ");
    }
}
