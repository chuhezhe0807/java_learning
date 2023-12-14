package designed.principle.composititionaggregation;

/**
 * 抽离具体的通知动作为一个类
 *
 * ClassName: Test
 * Package: designed.principle.composititionaggregation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/14 22:57
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
//        WXNotify wxNotify = new WXNotify();
        PhoneNotify notify = new PhoneNotify();
        notify.setParent(new Parent());
        teacher.setNotify(notify);

        teacher.call();
    }
}
