package designed.pattern.creational.abstractfactory;

/**
 * ClassName: Frame
 * Package: designed.pattern.creational.abstractfactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 15:27
 * @Version 1.0
 */
public class CarFrame extends CarFrameFactory {
    @Override
    public void make() {
        System.out.println("制作车架");
    }
}
