package designed.principle.composititionaggregation;

/**
 * 合成复用原则
 *
 * ClassName: Teacheer
 * Package: designed.principle.composititionaggregation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/14 22:37
 * @Version 1.0
 */
public class Teacher {
    private Notify notify;

    public void setNotify(Notify notify) {
        this.notify = notify;
    }

    public void call() {
        System.out.println("通知家长要开家长会了");

        if(notify instanceof WXNotify) {
            System.out.println("微信通知");
        }
        else if(notify instanceof PhoneNotify) {
            System.out.println("电话通知");
        }

        notify.myNotify();
    }
}
