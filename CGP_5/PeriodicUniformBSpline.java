import java.util.List;

public class PeriodicUniformBSpline extends BSpline{
    /**
     * B-Spline-Kurve
     *
     * @param points Kontrollpunkte
     * @param k      Grad der B-Spline + 1
     * @param h      Schrittweite beim Zeichnen der B-Spline
     */
    PeriodicUniformBSpline(List<Point> points, int k, double h) {
        super(points, k, h);
    }
}
