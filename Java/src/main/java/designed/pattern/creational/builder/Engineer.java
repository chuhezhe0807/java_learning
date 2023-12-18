package designed.pattern.creational.builder;

/**
 * ClassName: Engineer
 * Package: designed.pattern.creational.builder
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 18:02
 * @Version 1.0
 */
public class Engineer {
    private CarBuilder carBuilder;

    public void setCarBuilder(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public Car produceCar(String basePlate, String frame, String door) {
        carBuilder.buildDoor(door);
        carBuilder.buildFrame(frame);
        carBuilder.buildBasePlate(basePlate);

        return carBuilder.produceCar();
    }
}
