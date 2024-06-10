import java.awt.*;
import java.util.List;

abstract class BSpline {
	
	/** Ordnung der Spline-Kurve (= Grad + 1) */
	protected int k;

	/** Knotenvektor */
	protected double[] knotVector;

	/** Auflösung der Kurve, Schrittweite, Zeichengenauigkeit */
	private double h;
	
	/**
	 * Eine Liste von Point-Objekten Dies sind die Stütz- und Kontrollpunkte
	 */
	protected List<Point> points;

	

	/**
	 * B-Spline-Kurve 
	 * 
	 * @param points Kontrollpunkte
	 * @param k Grad der B-Spline + 1
	 * @param h Schrittweite beim Zeichnen der B-Spline
	 */
	BSpline(List<Point> points, int k, double h) {
		// TODO: Hier Ihr Code...
		this.points = points;
		this.k = k;
		this.h = h;
	}

	/**
	 * Zeichne eine B-Spline mit Stütz- und Kontrollpunkten aus points.
	 * 
	 * @param graphics Grafikobjekt
	 */
	void render(Graphics graphics) {
		// TODO: Ihr Code hier
		//for(){
		//	graphics.drawLine();
		//}
	}

	/**
	 * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
	 * BSpline-Kurve für einen gegebenen Parameterwert errechnet.
	 * 
	 * @param t Kurvenparameter
	 * @return 2D-Koordinate der B-Spline-Kurve für Parameter t
	 */
	Point bSpline(double t) {
		// TODO: Ihr Code hier
			return null;
	}

	/**
	 * Berechne den Wert der B-Spline-Basisfunktion N_{i,k}(t) analog zu Casteljau.
	 * 
	 * @param i Index der B-Spline-Basisfunktion N_{i,k}
	 * @param k Grad der B-Spline-Basisfunktion N_{i,k}
	 * @param t Parameter, an dem N_{i,k} ausgewertet wird
	 * @return N_{i,k}(t)
	 */
	double nik(int i, int k, double t) {
		// TODO: Ihr Code hier
		double[][] niko = new double[i*50][k+1];
		for(int j=i; j<k+i;j++){
			if(knotVector[j] <= t && t < knotVector[j+1] && niko[j-1][1]!=1){
				niko[j][1]=1;
			} else {
				niko[j][1]=0;
			}
		}
		for(int l=0; l<i; l++ ){
			for(int j=1; j<k;j++){
				if (x0rule(l, j)) {
					if(knotVector[l+j-1]-knotVector[l]==0){
						niko[l][j] = ((knotVector[l + j] - t / knotVector[l + j] - knotVector[l + 1])) * niko[l + 1][j - 1];
					} else {
						niko[l][j] = ((t - knotVector[l]) / (knotVector[l + j - 1] - knotVector[l])) * niko[l][j - 1];
					}
				} else {
					niko[l][j] = ((t - knotVector[l]) / (knotVector[l + j - 1] - knotVector[l])) * niko[l][j - 1] + ((knotVector[l + j] - t / knotVector[l + j] - knotVector[l + 1])) * niko[l + 1][j - 1];
				}

			}
		}
		return niko[i][k];
	}
	boolean x0rule(int l, int j){
		if(knotVector[l+j-1]-knotVector[l]==0 || knotVector[l+j]-knotVector[l+1] == 0){
			return true;
		} else{
			return false;
		}
	}
}
