package reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * ClassName: CarTest
 * Package: reflect
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/19 23:51
 * @Version 1.0
 */
public class CarTest {
    @Test
    public void test() throws Exception {
        Class clazz1 = Car.class;

        Class clazz2 = new Car().getClass();

        Class clazz3 = Class.forName("reflect.Car");

        Car car = (Car) clazz3.getDeclaredConstructor().newInstance();

        System.out.println(car);
    }

    // 获取构造方法
    @Test
    public void test2(){
        Class<Car> clazz = Car.class;

        // getConstructors() 获取所有的public声明的构造器 getDeclaredConstructors() 获取所有构造器
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        Method[] methods = clazz.getMethods();

    }
}
