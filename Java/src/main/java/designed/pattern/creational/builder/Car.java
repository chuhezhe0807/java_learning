package designed.pattern.creational.builder;

/**
 * ClassName: Car
 * Package: designed.pattern.creational.builder
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 17:52
 * @Version 1.0
 */
public class Car {
    private String basePlate;
    private String frame;
    private String door;

    public String getBasePlate() {
        return basePlate;
    }

    public void setBasePlate(String basePlate) {
        this.basePlate = basePlate;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "Car{" +
                "basePlate='" + basePlate + '\'' +
                ", frame='" + frame + '\'' +
                ", door='" + door + '\'' +
                '}';
    }
}
