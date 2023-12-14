package designed.principle.singleresponsibility;

import org.aspectj.weaver.ast.Or;

/**
 * ClassName: Test
 * Package: designed.principle.singleresponsibility
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/13 21:48
 * @Version 1.0
 */
public class Test {
    /**
     * Order 类与 JavaProgrammer 无关，不能在JavaProgrammer类中写相关的方法
     * 需要将Order类注入，通过属性调用
     */
    public static void main(String[] args) {
        JavaProgrammer javaProgrammer = new JavaProgrammer(new Order());
        javaProgrammer.sleep();
        javaProgrammer.eat();
    }
}
