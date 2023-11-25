package graphics2d.tester;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ClassName: TesterTest
 * Package: graphics2d.tester
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/20 23:12
 * @Version 1.0
 */
public class TesterTest {
    @Test
    public void test01() {
        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
//        graphics2D.fillRect(50, 50, 300, 300); // fillRect
//        graphics2D.fill3DRect(50, 50, 300, 300, true); // fill3DRect
//        graphics2D.fillOval(50, 50, 300, 400); // fillOval 使用矩形框出来一个圆或椭圆Z
//        graphics2D.fillArc(50, 50, 400, 400, 0, -360); // fillArc 逆时针为正方向
        graphics2D.setColor(Color.white);
        graphics2D.setStroke(new BasicStroke(6));
        graphics2D.drawRect(50, 50, 300, 300); // drawRect
        graphics2D.setColor(Color.red);
        graphics2D.fillRect(53, 53, 294, 294); // drawRect
//        graphics2D.drawOval(50, 50, 300, 400); // drawOval 使用矩形框出来一个圆或椭圆Z

        try {
            ImageIO.write(
                    image,
                    "png",
                    Files.newOutputStream(Paths.get("D:\\WorkSpace_BackEnd\\TestFiles\\graphics2d\\tester\\drawRect.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        try(FileInputStream fileInputStream = new FileInputStream("D:\\WorkSpace_BackEnd\\TestFiles\\graphics2d\\images\\contimg86.jpg")) {
            BufferedImage bufferedImage = ImageIO.read(fileInputStream);
            Graphics2D graphics2D = bufferedImage.createGraphics();
//            graphics2D.clearRect(100, 100, 100, 100);
            graphics2D.clipRect(100, 100, 300, 300);

            ImageIO.write(
                    bufferedImage,
                    "png",
                    Files.newOutputStream(Paths.get("D:\\WorkSpace_BackEnd\\TestFiles\\graphics2d\\tester\\clipRect.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Arc2D.Double()
    @Test
    public void test03() {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(new Arc2D.Double(50, 50, 200, 200, 30, 60, Arc2D.PIE));
        g2d.draw(new Arc2D.Double(200, 50, 200, 200, 30, 60, Arc2D.OPEN));
        g2d.draw(new Arc2D.Double(200, 200, 200, 200, 30, 60, Arc2D.CHORD));

        try {
            ImageIO.write(
                    bufferedImage,
                    "png",
                    Files.newOutputStream(Paths.get("D:\\WorkSpace_BackEnd\\TestFiles\\graphics2d\\shapes\\Arc2D.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            g2d.dispose();
        }
    }
}
