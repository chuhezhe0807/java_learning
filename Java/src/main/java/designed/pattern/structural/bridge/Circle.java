package designed.pattern.structural.bridge;

/**
 * ClassName: Circle
 * Package: designed.pattern.structural.bridge
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 22:48
 * @Version 1.0
 */
public class Circle implements Shape{
    @Override
    public void draw(String tools) {
        System.out.println("实用工具 : " + tools + " 画圆形");
    }
}
