import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Bezier {

	// TODO: Definieren Sie benötigte Attribute hier.
	List<Point> controllPoints;
	double schrittWeite;
	/**
	 * Berechnet Beziér-Kurven. Der Grad der Beziér-Kurve ist über die Zahl der
	 * Kontrollpunkte festgelegt.
	 * 
	 * @param points Kontrollpunkte.
	 * @param h      Schrittweite beim Zeichnen der Beziér-Kurve
	 */

	Bezier(List<Point> points, double h) {
		// TODO: Hier Ihr Code...
		controllPoints = points;
		schrittWeite = h;
	}

	/**
	 * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
	 * Bézier-Kurve für einen gegebenen Parameterwert errechnet.
	 * 
	 * @param t Kurvenparameter
	 * @return Koordinate der Bézier-Kurve
	 */
	Point casteljau(double t) {
		// TODO: Hier Ihr Code...
			Point[][] points= new Point[controllPoints.size()+1][controllPoints.size()+1];
        for(int i=0; i<controllPoints.size(); i++){
			points[0][i] = controllPoints.get(i);
		}

		for(int k=1; k<controllPoints.size(); k++){
			for(int i=k; i<controllPoints.size(); i++){
				points[k][i] = new Point(1-t,points[k-1][i-1],t,points[k-1][i]);
			}
		}
		return points[controllPoints.size()-1][controllPoints.size()-1];
    }

	/**
	 * Zeichne eine Bezier-Kurve mit Stütz- und Kontrollpunkten aus points.
	 * 
	 * @param graphics Grafikobjekt
	 */
	void render(Graphics graphics) {
		// TODO: Hier Ihr Code...

		for(double i=0; i<1-this.schrittWeite;i+=this.schrittWeite){
			int ersterPunktX = (int) casteljau(i).x;
			int ersterPunktY = (int) casteljau(i).y;
			int zweiterPunktX = (int) casteljau(i+schrittWeite).x;
			int zweiterPunktY = (int) casteljau(i+schrittWeite).y;
			graphics.drawLine(ersterPunktX,ersterPunktY,zweiterPunktX,zweiterPunktY);
		}
	}
}
