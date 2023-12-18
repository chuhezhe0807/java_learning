package designed.pattern.creational.builder;

/**
 * ClassName: Builder
 * Package: designed.pattern.creational.builder
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 17:55
 * @Version 1.0
 */
public abstract class Builder {
    public abstract void buildBasePlate(String basePlate);
    public abstract void buildFrame(String frame);
    public abstract void buildDoor(String door);
    public abstract Car produceCar();
}
