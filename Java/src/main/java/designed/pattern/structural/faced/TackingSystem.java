package designed.pattern.structural.faced;

/**
 * ClassName: TackingSystem
 * Package: designed.pattern.structural.faced
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 19:50
 * @Version 1.0
 */
public class TackingSystem {
    private SelectFoodService selectFoodService;
    private PayService payService;
    private OrderService orderService;

    public TackingSystem() {
        selectFoodService = new SelectFoodService();
        payService = new PayService();
        orderService = new OrderService();
    }

    public void orderTacking(Food food) {
        // 点餐
        selectFoodService.select(food.getName());

        // 校验余额
        boolean flag = payService.checkBalance();

        if(flag) {
            payService.pay();

            orderService.makeOrder();
        }
    }
}
