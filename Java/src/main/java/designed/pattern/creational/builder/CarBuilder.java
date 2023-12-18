package designed.pattern.creational.builder;

/**
 * ClassName: CarBuilder
 * Package: designed.pattern.creational.builder
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 17:57
 * @Version 1.0
 */
public class CarBuilder extends Builder{
    private final Car car = new Car();

    @Override
    public void buildBasePlate(String basePlate) {
        car.setBasePlate(basePlate);
    }

    @Override
    public void buildFrame(String frame) {
        car.setFrame(frame);
    }

    @Override
    public void buildDoor(String door) {
        car.setDoor(door);
    }

    @Override
    public Car produceCar() {
        return car;
    }
}
