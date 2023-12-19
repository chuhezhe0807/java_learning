package designed.pattern.structural.faced;

/**
 * ClassName: Test
 * Package: designed.pattern.structural.faced
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 21:36
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Food food = new Food();
        food.setName("鱼香肉丝");

        TackingSystem tackingSystem = new TackingSystem();
        tackingSystem.orderTacking(food);
    }
}
