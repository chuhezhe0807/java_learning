package graphics2d.chart.util;

import java.awt.geom.Point2D;

/**
 * ClassName: GeometryUtil
 * Package: graphics2d.chart.util
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/23 21:21
 * @Version 1.0
 */
public class GeometryUtil {
    // 两点之间的距离
    public static double distanceOfPoints(Point2D p1, Point2D p2) {
        double disX = p2.getX() - p1.getX();
        double disY = p2.getY() - p1.getY();
        double dis = Math.sqrt(disX * disX + disY * disY);

        return dis;
    }

    // 两点的中点
    public static Point2D middlePoint(Point2D p1, Point2D p2) {
        double x = (p1.getX() + p2.getX()) / 2;
        double y = (p1.getY() + p2.getY()) / 2;

        return new Point2D.Double(x, y);
    }

    // 在两点所在直线上，以从startPoint到endPoint为方向，离startPoint的距离disToStartPoint的点
    public static Point2D extentPoint(Point2D startPoint, Point2D endPoint, double disToStartPoint) {
        double disX = endPoint.getX() - startPoint.getX();
        double disY = endPoint.getY() - startPoint.getY();
        double dis = Math.sqrt(disX * disX + disY * disY);
        double sin = (endPoint.getY() - startPoint.getY()) / dis;
        double cos = (endPoint.getX() - startPoint.getX()) / dis;
        double deltaX = disToStartPoint * cos;
        double deltaY = disToStartPoint * sin;

        return new Point2D.Double(startPoint.getX() + deltaX, startPoint.getY() + deltaY);
    }
}