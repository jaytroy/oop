package nl.rug.oop.rts.controller;

public class Utils {
    public static boolean isPointNearLine(int x1, int y1, int x2, int y2, int px, int py, int threshold) {
        double distance = pointToSegmentDistance(x1, y1, x2, y2, px, py);
        return distance <= threshold;
    }

    public static double pointToSegmentDistance(int x1, int y1, int x2, int y2, int px, int py) {
        double lineLengthSquared = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
        if (lineLengthSquared == 0) return Math.hypot(px - x1, py - y1);

        double t = ((px - x1) * (x2 - x1) + (py - y1) * (y2 - y1)) / lineLengthSquared;
        t = Math.max(0, Math.min(1, t));
        double projectionX = x1 + t * (x2 - x1);
        double projectionY = y1 + t * (y2 - y1);
        return Math.hypot(px - projectionX, py - projectionY);
    }
}

