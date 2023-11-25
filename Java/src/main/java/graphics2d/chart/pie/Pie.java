package graphics2d.chart.pie;

import graphics2d.chart.util.GeometryUtil;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * 饼图
 * ClassName: Pie
 * Package: graphics2d.chart.pie
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/22 22:54
 * @Version 1.0
 */
class Pie {
    private final Arc2D arc; // 这里的弧并不是圆上的一弧，而是椭圆的一部分.
    private final Area frontArea;
    private final Area leftArea;
    private final Area rightArea;
    private final Color color;
    private final double labelValue;
    private final Point2D labelPosition;

    public Pie(Arc2D arc, Color color, double labelValue, int shadowDepth) {
        this.arc = arc;
        this.color = color;
        this.labelValue = labelValue;

        // 3D饼图厚度
        Arc2D arcBottom = new Arc2D.Double(arc.getX(), arc.getY() + shadowDepth, arc.getWidth(),
                arc.getHeight(), arc.getAngleStart(), arc.getAngleExtent(), Arc2D.CHORD);

        Point2D topCenterPoint = new Point2D.Double(arc.getCenterX(), arc.getCenterY());
        Point2D topLeftPoint = arc.getStartPoint();
        Point2D topRightPoint = arc.getEndPoint();
        Point2D bottomLeftPoint = arcBottom.getStartPoint();
        Point2D bottomRightPoint = arcBottom.getEndPoint();

        // Front path
        GeneralPath frontPath = new GeneralPath();
        frontPath.moveTo(topLeftPoint.getX(), topLeftPoint.getY());
        frontPath.lineTo(topRightPoint.getX(), topRightPoint.getY());
        frontPath.lineTo(bottomRightPoint.getX(), bottomRightPoint.getY());
        frontPath.lineTo(bottomLeftPoint.getX(), bottomLeftPoint.getY());
        frontPath.closePath();
        this.frontArea = new Area(arcBottom);
        this.frontArea.add(new Area(frontPath));

        // Left path 3D扇形pie的左边框
        GeneralPath leftPath = new GeneralPath();
        leftPath.moveTo(topCenterPoint.getX(), topCenterPoint.getY());
        leftPath.lineTo(topLeftPoint.getX(), topLeftPoint.getY());
        leftPath.lineTo(bottomLeftPoint.getX(), bottomLeftPoint.getY());
        leftPath.lineTo(topCenterPoint.getX(), topCenterPoint.getY() + 3);
        leftPath.closePath();
        this.leftArea = new Area(leftPath);

        // Right path 3D扇形pie的右边框
        GeneralPath rightPath = new GeneralPath();
        rightPath.moveTo(topCenterPoint.getX(), topCenterPoint.getY());
        rightPath.lineTo(topRightPoint.getX(), topRightPoint.getY());
        rightPath.lineTo(bottomRightPoint.getX(), bottomRightPoint.getY());
        rightPath.lineTo(topCenterPoint.getX(), topCenterPoint.getY() + 3);
        rightPath.closePath();
        this.rightArea = new Area(rightPath);

        // Label position: 五分之四处
        Point2D c = getPieCenter();
        Point2D m = calculateArcMiddle();
        double dis = GeometryUtil.distanceOfPoints(c, m) * 0.8;
        labelPosition = GeometryUtil.extentPoint(c, m, dis);
    }

    public Arc2D getArc() {
        return arc;
    }

    public Area getFrontArea() {
        return frontArea;
    }

    public Area getLeftArea() {
        return leftArea;
    }

    public Area getRightArea() {
        return rightArea;
    }

    public Color getColor() {
        return color;
    }

    public Point2D getLabelPosition() {
        return labelPosition;
    }

    public String getLabel() {
        return labelValue + "%";
    }
    // 饼图的圆心
    public Point2D getPieCenter() {
        return new Point2D.Double(arc.getCenterX(), arc.getCenterY());
    }

    private Point2D calculateArcMiddle() {
        // 创建一个新的弧，其扩展角度为当前弧的一半
        return new Arc2D.Double(arc.getX(), arc.getY(), arc.getWidth(), arc.getHeight(),
                arc.getAngleStart(), arc.getAngleExtent() / 2, Arc2D.PIE).getEndPoint();
    }
}