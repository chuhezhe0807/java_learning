package skills.myenum;

import java.security.PrivilegedAction;
import java.util.function.Supplier;

/**
 * ClassName: MyEnumTest
 * Package: skills.myenum
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/12/16 23:25
 * @Version 1.0
 */
public class MyEnumTest {
    public static void main(String[] args) {
        for (MyEnum value : MyEnum.values()) {
            System.out.printf("%s --- %s%n", value.getSeasonName(), value.getSeasonDesc());
        }

        Supplier<MyEnum> action = () -> MyEnum.SPRING;
        switch(action.get()) {
            case SPRING:
                System.out.println("SPRING");
                break;
            case SUMMER:
                System.out.println("SUMMER");
                break;
            case AUTUMN:
                System.out.println("AUTUMN");
                break;
            case WINTER:
                System.out.println("WINTER");
                break;
            default:
                break;
        }
    }
}
