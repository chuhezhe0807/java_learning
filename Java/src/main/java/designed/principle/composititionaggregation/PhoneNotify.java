package designed.principle.composititionaggregation;

/**
 * ClassName: PhoneNotify
 * Package: designed.principle.composititionaggregation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/14 22:53
 * @Version 1.0
 */
public class PhoneNotify implements Notify{
    private Parent parent;

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @Override
    public void myNotify() {
        if(null != parent) {
            parent.meeting();
        }
    }
}
