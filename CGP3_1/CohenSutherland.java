import java.awt.Graphics;
import java.util.OptionalInt;

/**
 * Clipping nach Cohen-Sutherland.
 */
public class CohenSutherland {
	/** Zum Zeichnen */
	private Graphics graphics;

	/** Dimension des Clipping-Rechtecks */
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	/**
	 * Ctor.
	 * 
	 * @param graphics Zum Zeichnen
	 * @param xmin     minimale x-Koordinate
	 * @param ymin     minimale y-Koordinate
	 * @param xmax     maximale x-Koordinate
	 * @param ymax     maximale y-Koordinate
	 */
	public CohenSutherland(Graphics graphics, int xmin, int ymin, int xmax, int ymax) {
		super();
		this.graphics = graphics;
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}

	/**
	 * Berechne den Cohen-Sutherland-Outputcode für einen Punkt.
	 * 
	 * @formatter:off
	 * viertletztes Bit = 1 <=> y > ymax 
	 * drittletztes Bit = 1 <=> y < ymin
	 * vorletztes Bit = 1 <=> x > xmax 
	 * letztes Bit = 1 <=> x < xmin
	 * @formatter:on
	 * 
	 * Die 4 Bits werden sehr verschwenderisch in einem int untergebracht.
	 * 
	 * Warum kein byte? Die bitweisen Operationen sind für Datentyp byte nicht
	 * definiert! Genauer werden z.B. die Bytes bei byte1 | byte2 zu ints gecastet
	 * und das Ergebnis ist ein int.
	 * Mehr Details: <a href="https://stackoverflow.com/questions/27582233/why-byte-and-short-values-are-promoted-to-int-when-an-expression-is-evaluated">Stack Overflow</a>
	 * 
	 * @param x x-Koordinate Punkt
	 * @param y y-Koordinate Punkt
	 * @return Outputcode
	 */
	int outputCode(int x, int y) {
		int bitErgebnis=0;
		// TODO: Ihr Code hier ...
		if(y>ymax){
			bitErgebnis = bitErgebnis | Area.GTYMAX; //4bit
		}
		if(y<ymin){
			bitErgebnis = bitErgebnis | Area.LTYMIN; //3bit
		}
		if(x<xmin){
			bitErgebnis = bitErgebnis | Area.LTXMIN; //1bit
		}
		if(x>xmax){
			bitErgebnis = bitErgebnis | Area.GTXMAX; //2bit
		}
		return bitErgebnis;
	}

	/**
	 * Clipping nach Cohen-Sutherland. Die Linie von (xA,yA) nach (xE,yE) wird an
	 * dem durch die Attribute (xmin,ymin) und (xmax,ymax) definierten Rechteck
	 * geclippt und der sichtbare Teil der Linie gezeichnet.
	 * 
	 * @param xA x-Koordinate Anfangspunkt Linie
	 * @param yA y-Koordinate Anfangspunkt Linie
	 * @param xE x-Koordinate Endpunkt Linie
	 * @param yE y-Koordinate Endpunkt Linie
	 */
	void clipLine(int xA, int yA, int xE, int yE) {
		// TODO: Ihr Code hier ...
		int andAbfrage=0;
		int orAbfrage=0;
		int aOC = outputCode(xA,yA);
		int eOC = outputCode(xE,yE);
		andAbfrage= aOC & eOC;
		orAbfrage= aOC | eOC;
		if(andAbfrage!=0){

		} else if (orAbfrage==0) {
			graphics.drawLine(xA,yA,xE,yE);
		} else {

			if (aOC > 0) {
				int newX = 0;
				int newY = 0;

				if (yA < ymin) {
					newX = (int) (((double)(xE - xA) / (yE - yA)) * (ymin - yE) + xE);
					newY = ymin;
					clipLine((int)newX, (int)newY, xE, yE);
				} else if (yA > ymax) {
					newX = (int) (((double)(xE - xA) / (yE - yA)) * (ymax - yE) + xE);
					newY = ymax;
					clipLine((int) newX, (int) newY, xE, yE);
				} else if (xA < xmin) {
					newX = xmin;
					newY = (int) (((double)(yE - yA) / (xE - xA)) * (xmin - xE) + yE);
					clipLine((int) newX, (int) newY, xE, yE);
				} else if (xA > xmax) {
					newX = xmax;
					newY = (int) (((double)(yE - yA) / (xE - xA)) * (xmax - xE) + yE);
					clipLine((int) newX, (int)newY, xE, yE);
				}

			} else {
				clipLine(xE,yE,xA,yA);
			}

		}
	}
}
