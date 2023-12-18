package designed.pattern.creational.prototype;

/**
 * ClassName: Test
 * Package: designed.pattern.creational.prototype
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 19:05
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 会调用多次 new 方法
//        Pig peki0 = new Pig();
//        peki0.setName("peki0");
//        peki0.setDoSomeThing("喜欢吃");
//        System.out.println(peki0);
//
//        Pig peki1 = new Pig();
//        peki1.setName("peki1");
//        peki1.setDoSomeThing("喜欢睡");
//        System.out.println(peki1);
//
//        Pig peki2 = new Pig();
//        peki2.setName("peki2");
//        peki2.setDoSomeThing("喜欢开车");
//        System.out.println(peki2);
//
//        Pig peki3 = new Pig();
//        peki3.setName("peki3");
//        peki3.setDoSomeThing("喜欢做饭");
//        System.out.println(peki3);

        // 只调用一次 new 方法
        Pig peki0 = new Pig();
        peki0.setName("peki0");
        peki0.setDoSomeThing("喜欢吃");
        System.out.println(peki0);

        Pig peki1 = ((Pig) peki0.clone());
        peki1.setName("peki1");
        peki1.setDoSomeThing("喜欢睡");
        System.out.println(peki1);

        Pig peki2 = ((Pig) peki0.clone());
        peki2.setName("peki2");
        peki2.setDoSomeThing("喜欢开车");
        System.out.println(peki2);

        Pig peki3 = ((Pig) peki0.clone());
        peki3.setName("peki3");
        peki3.setDoSomeThing("喜欢做饭");
        System.out.println(peki3);
    }
}
