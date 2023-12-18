package designed.pattern.creational.builder;

/**
 * 不关心造车的细节（车门或者车架怎么生产，就是没有抽象类），只需要将各个零件组合到一起
 *
 * ClassName: Test
 * Package: designed.pattern.creational.builder
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 18:22
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilder();

        Engineer engineer = new Engineer();
        engineer.setCarBuilder(carBuilder);

        Car car = engineer.produceCar("制作底盘", "制作车架", "制作车门");
        System.out.println(car);
    }
}
