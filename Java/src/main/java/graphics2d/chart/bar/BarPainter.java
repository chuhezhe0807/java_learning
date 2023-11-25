package graphics2d.chart.bar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 柱状图
 * ClassName: BarChart
 * Package: graphics2d.chart.bar
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/22 22:54
 * @Version 1.0
 */
public class BarPainter {
    // 图片宽度
    private final int width;

    // 图片高度
    private final int height;

    // img对象
    private final BufferedImage img;

    // 绘图环境
    private final Graphics2D g2d;

    // 垂直方向起点
    private final int yStart;

    // 垂直方向终点
    private final int yEnd;

    // 直方图数据
    private List<Bar> bars;

    // 构造函数
    public BarPainter(int width, int height, int yStart, int yEnd) {
        this.width = width;
        this.height = height;
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.g2d = (Graphics2D) img.getGraphics();
        this.yStart = yStart;
        this.yEnd = yEnd;
    }

    // 添加一项直方图数据
    public void addBar(String name, int value) {
        if (bars == null) {
            bars = new ArrayList<>();
        }

        bars.add(new Bar(name, value));
    }

    /**
     * 重置屏幕坐标系为笛卡尔坐标系，默认坐标系方向为左上角为原点，水平向右为x正方向，垂直向下为y正方向
     *
     *  void translate(double tx, double ty) tx - 坐标沿X轴方向平移的距离 ty - 坐标在Y轴方向上平移的距离
     *  void rotate(double theta, double anchorx, double anchory) 通过正角度θ旋转使正X轴上的点朝向正Y轴旋转 anchor旋转锚点坐标
     *  void scale(double sx, double sy) sx - 坐标沿X轴方向缩放的因子 sy - 坐标沿Y轴方向缩放的因子
     */
    private void resetCoordinate() {
        AffineTransform trans = new AffineTransform();
        trans.translate(0, this.height - this.yStart); // 从左上角移到左下角
        trans.rotate(Math.toRadians(180.0), 0, 0); // 从[0,0]正X轴上朝向正Y轴旋转180度
        trans.scale(-1, 1); // 水平翻转 同理 trans.scale(1, -1) 为垂直翻转
        this.g2d.setTransform(trans);
    }

    // 绘制图案
    public void draw() {
        resetCoordinate();

        // 设置背景为天蓝色
        g2d.setColor(new Color(135, 206, 235));
        g2d.fillRect(0, -this.yStart, this.width, this.height);

        final int yMax = this.yEnd;
        final int barCnt = this.bars.size();

        // --往下是竖向网格线
        final float stepx = (float) (this.width / barCnt);
        final float CW = stepx / 3;// CW:Column Width

        // LINE_TYPE_DASHED
        Stroke dashed = new BasicStroke(
                1,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL,
                0,
                new float[]{16, 4},
                0);

        g2d.setColor(Color.gray);

        for (int i = 0; i < barCnt; i++) {
            float x = i * stepx;
            float y = yMax;

            g2d.setStroke(new BasicStroke(1.0f));
            g2d.drawLine((int) x, 0, (int) x, (int) y);

            g2d.setStroke(dashed);
            g2d.drawLine((int) (x + CW), 0, (int) (x + CW), (int) y);
            g2d.drawLine((int) (x + 2 * CW), 0, (int) (x + 2 * CW), (int) y);
        }

        // 以最高点定比例
        float maxCnt = -1;
        for (Bar b : bars) {
            if (b.getValue() > maxCnt) {
                maxCnt = b.getValue();
            }
        }
        final float ratio = yMax / maxCnt;

        // --往下是画横向网格线
        final float stepy = (float) (yMax / barCnt);
        final float GH = stepy / 3;// GH:Grid Width

        for (int i = 0; i <= barCnt; i++) {
            float y = i * stepy;

            g2d.setStroke(new BasicStroke(1.0f));
            g2d.drawLine(0, (int) y, this.width, (int) y);

            g2d.setFont(new Font("宋体", Font.BOLD, 16));
            g2d.setColor(Color.black);
            int yValue = (int) (y * maxCnt / yMax);
            putString(g2d, yValue + "", 15, (int) y);

            if (i > 0) {
                g2d.setStroke(dashed);
                g2d.drawLine(0, (int) (y - GH), this.width, (int) (y - GH));
                g2d.drawLine(0, (int) (y - 2 * GH), this.width, (int) (y - 2 * GH));
            }
        }

        // --往下是画柱状图
        for (int i = 0; i < this.bars.size(); i++) {
            Bar bar = this.bars.get(i);

            float x = i * stepx + (CW);
            float y = 0;
            float w = CW;
            float h = bar.getValue() * ratio;

            g2d.setColor(getColor(i));
            g2d.fillRect((int) x, (int) y, (int) (w), (int) (h));

            // 在柱子顶写文字
            g2d.setFont(new Font("仿宋", Font.BOLD, 16));
            g2d.setColor(Color.black);
            putString(g2d, bar.getName() + "=" + bar.getValue(), (int) (x + CW / 2), (int) (h - 15));
        }

        // 写标题
        g2d.setFont(new Font("仿宋", Font.BOLD, 36));
        g2d.setColor(Color.black);
        putString(g2d, "g2d绘制直方图示例", this.width / 2, this.yEnd + 50);

        // 写作者
        g2d.setFont(new Font("仿宋", Font.BOLD, 12));
        g2d.setColor(Color.black);
        putString(g2d, "chuhezhe", this.width - 100, -25);

        g2d.dispose();// g2d使命完成
    }

    // 写入图片
    public void write2File(String path) {
        try {
            ImageIO.write(img, "PNG", Files.newOutputStream(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 写文字
    private void putString(Graphics2D g2d, String text, int x, int y) {
        AffineTransform previousTrans = g2d.getTransform();
        AffineTransform newtrans = new AffineTransform();

        FontMetrics fm2 = g2d.getFontMetrics();
        int textWidth = fm2.stringWidth(text);

        newtrans.translate(x - textWidth / 2.0, (this.height - this.yStart) - y);

        g2d.setTransform(newtrans);
        g2d.drawString(text, 0, 0);
        g2d.setTransform(previousTrans);
    }

    // 取一种颜色
    private static Color getColor(int idx) {
        Color[] colors = {Color.red, Color.yellow, Color.blue, Color.magenta, Color.green, Color.orange, Color.cyan};

        int i = idx % colors.length;
        return colors[i];
    }

    public static void main(String[] args) {
        BarPainter pm = new BarPainter(1200, 960, 50, 800);
        pm.addBar("勇气", 80);
        pm.addBar("毅力", 120);
        pm.addBar("果敢", 450);
        pm.addBar("机敏", 32);
        pm.addBar("决心", 360);
        pm.addBar("明智", 230);
        pm.addBar("忍耐", 420);

        pm.draw();

        pm.write2File("D:\\WorkSpace_BackEnd\\TestFiles\\graphics2d\\chart\\bar.png");
        System.out.println("直方图做成");
    }
}