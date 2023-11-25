package graphics2d.chart.pie;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: PiePainter
 * Package: graphics2d.chart.piecopy
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/23 21:46
 * @Version 1.0
 */
public class PiePainter {
    // img对象
    private final BufferedImage img;
    private static final int SHADOW_DEPTH = 20;
    private static final int SHIFT_ANGLE = -30; // 第一个饼图arc
    private static final float CHART_IMAGE_RATIO = 0.8f;

    public PiePainter(int width, int height) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();

        double[] data = new double[]{20.72, 6.56, 3.74, 10.26, 15.38, 5.69, 10.72, 15.38, 6.15, 18.0};
        Color[] defaultColors = createColors();

        int w = (int) (width * (CHART_IMAGE_RATIO));
        int h = (int) (w * 0.618); // 黄金分割
        int x = (int) (width * (1 - CHART_IMAGE_RATIO) / 2);
        int y = (height - h) / 2;
        Pie[] pies = createPies(x, y, w, h, SHADOW_DEPTH, SHIFT_ANGLE, data, defaultColors);

        int startIndex = 0; // 从第几个饼图开始绘制
        int endIndex = pies.length - 1; // 要画的饼图的数量.
        FontMetrics metrics = g2d.getFontMetrics();

        // 一次性绘制完3D效果，然后再绘制饼图的效果比绘制饼图的同时绘制好
        for (int i = startIndex; i <= endIndex; i++) {
            Pie p = pies[i];
            g2d.setColor(p.getColor().darker());
            g2d.fill(p.getFrontArea()); // 3D饼图的前面
        }

        // 需要时再绘制
        // 第一块画左面
//        g2d.setColor(pies[startIndex].getColor().darker());
//        g2d.draw(pies[startIndex].getLeftArea());

        // 最后一块画右面
//        g2d.setColor(pies[endIndex - 1].getColor().darker());
//        g2d.draw(pies[endIndex - 1].getRightArea());

        // 最后再绘制饼图的上面部分，把不需要的部分隐藏掉
        for (int i = startIndex; i <= endIndex; i++) {
            Pie p = pies[i];
            g2d.setColor(p.getColor());
            g2d.fill(p.getArc()); // 上面的颜色填充部分

            int sw = metrics.stringWidth(p.getLabel()) / 2;
            int sh = (metrics.getAscent()) / 2;
            int labelX = (int) (p.getLabelPosition().getX() - sw);
            int labelY = (int) (p.getLabelPosition().getY() + sh);
            g2d.setColor(Color.BLACK);
            g2d.drawString(p.getLabel(), labelX, labelY); // 文字信息
        }
    }

    private Color[] createColors() {
        // 返回16进制的值颜色
        List<Color> colors = new ArrayList<>();
        colors.add(Color.decode("#FF7321"));
        colors.add(Color.decode("#169800"));
        colors.add(Color.decode("#00E500"));
        colors.add(Color.decode("#D0F15A"));
        colors.add(Color.decode("#AA6A2D"));
        colors.add(Color.decode("#BFDD89"));
        colors.add(Color.decode("#E2FF55"));
        colors.add(Color.decode("#D718A5"));
        colors.add(Color.decode("#00DBFF"));
        colors.add(Color.decode("#00FF00"));

        return colors.toArray(new Color[0]);
    }

    public Pie[] createPies(
               int x,
               int y,
               int w,
               int h,
               int shadowDepth,
               double shiftAngle,
               double[] data,
               Color[] colors) {
        double sum = 0;
        for (double d : data) {
            sum += d;
        }

        // 初始化Pies
        double arcAngle;
        double startAngle = shiftAngle;
        Pie[] pies = new Pie[data.length];

        for (int i = 0; i < data.length; ++i) {
            arcAngle = data[i] * 360 / sum; // 使用百分比计算角度
            if (i + 1 == data.length) {
                arcAngle = 360 + shiftAngle - startAngle; // 保证闭合
                arcAngle = arcAngle > 0 ? arcAngle : 0;
            }

            Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, arcAngle, Arc2D.PIE);
            pies[i] = new Pie(arc, colors[i % colors.length], data[i], shadowDepth);
            startAngle += arcAngle;
        }

        return pies;
    }

    // 写入图片
    public void write2File(String path) {
        try {
            ImageIO.write(img, "PNG", Files.newOutputStream(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PiePainter piePainter = new PiePainter(1200, 960);

        piePainter.write2File("D:\\WorkSpace_BackEnd\\TestFiles\\graphics2d\\chart\\pie.png");
        System.out.println("3D饼图做成");
    }
}
