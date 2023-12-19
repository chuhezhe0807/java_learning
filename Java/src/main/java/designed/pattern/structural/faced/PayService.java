package designed.pattern.structural.faced;

/**
 * ClassName: PayService
 * Package: designed.pattern.structural.faced
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 19:42
 * @Version 1.0
 */
public class PayService {
    public void pay() {
        System.out.println("正在支付...");
    }

    public boolean checkBalance() {
        System.out.println("检查用户余额...");

        return true;
    }
}
