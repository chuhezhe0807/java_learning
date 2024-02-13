package skills.generic;

/**
 * 通配符 ?, ? extends E, ? super T
 *
 * ClassName: wildNeedCardDemo
 * Package: generic
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/9 16:21
 * @Version 1.0
 */
public class wildNeedCardDemo {
    public static void main(String[] args) {
        GenericStack<Integer> stack = new GenericStack<>();
        stack.push(10);
        stack.push(-10);
        stack.push(0);
        stack.push(11);

        System.out.println("max: " + max(stack));
    }

    // 将Number泛型改为 ? extends Number, 防止当 GenericStack<> 传入的泛型为Number的子类，不能通过静态检查
    public static double max(GenericStack<? extends Number> stack) {
        // Initialize value
        double max = stack.pop().doubleValue();

        while(!stack.isEmpty()) {
            double value = stack.pop().doubleValue();

            if(value > max) {
                max = value;
            }
        }

        return max;
    }

    // 向 stack1 中添加 stack2 中的全部值
    // 或者也可以改成 public static <T> void add(GenericStack<T> stack1, GenericStack<? extends T> stack2)
    public static <T> void add(GenericStack<? super T> stack1, GenericStack<T> stack2) {
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
}
