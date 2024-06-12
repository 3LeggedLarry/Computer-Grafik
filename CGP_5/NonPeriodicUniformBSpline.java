import java.util.List;

public class NonPeriodicUniformBSpline extends BSpline{
    /**
     * B-Spline-Kurve
     *
     * @param points Kontrollpunkte
     * @param k      Grad der B-Spline + 1
     * @param h      Schrittweite beim Zeichnen der B-Spline
     */
    NonPeriodicUniformBSpline(List<Point> points, int k, double h) {
        super(points, k, h);
    }

}
