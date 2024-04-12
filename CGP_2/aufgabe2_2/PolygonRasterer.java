package aufgabe2_2;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Rastert Polygone mit Scanline-Verfahren.
 * 
 * Zur Vereinfachung nehmen wir an, dass sich Polygone immer komplett im
 * Viewport befinden, den Rand also nicht schneiden.
 */
public class PolygonRasterer {
	/** Zum Zeichnen */
	private Graphics graphics;
	/** Höhe des Zeichenfensters */
	private int height;
	/** Die Edge Table */
	private LinkedList<Edge> edgeTable = new LinkedList<>();
	/** Die Active Edge Table */
	private LinkedList<Edge> activeEdgeTable = new LinkedList<>();

	public PolygonRasterer(int height) {
		this.height = height;
	}

	/**
	 * Umsetzung des Scan-Line-Verfahrens
	 */
	public void rasterize() {
		// TODO: Ihr Code hier...
		//Schleife die gesamte Höhe durchläuft
		for(int y=0; y<height; y++){
			/*
			Alle Kanten die eine Überschneidung in der aktuellen y-Koordinate
			haben in AET aufnehmen
			*/
			for(Edge Ecken : edgeTable){
				if(Ecken.getYMin()==y){
					activeEdgeTable.add(new Edge(Ecken));
				}
			}
			/*
			Sortiere die AET nach x-Schnittpunkten
			 */
			activeEdgeTable.sort(Comparator.comparingDouble(Edge::getxIntersect));

			for(int i=0; i<activeEdgeTable.size(); i+=2){
				//hole den ersten Punkt
				Edge edge1 = activeEdgeTable.get(i);
				//hole den zweiten Punkt um sie zu verbinden
				Edge edge2 = activeEdgeTable.get(i+1);
				//zeichne Linie zwischen den Punkten
				graphics.drawLine((int) edge1.getxIntersect(),y,(int) edge2.getxIntersect(),y);
			}
            /*
             Alle Kanten die größer sind als die zulässige y-Koordinate
             aus AET entfernen
            */
			LinkedList<Edge> edgesToRemove = new LinkedList<>();
			for (Edge Ecken : activeEdgeTable){
				if (Ecken.getyMax()==y){
					edgesToRemove.add(Ecken);
				}
			}
			activeEdgeTable.removeAll(edgesToRemove);
			/*
			Aktualisiere alle x-Schnittwerte mit x-Schnitt= x-Schnitt+1/m
			 */
			for(Edge Ecken : activeEdgeTable){
				Ecken.setxIntersect(Ecken.getxIntersect()+Ecken.getmReci());
			}
		}
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void addEdges(LinkedList<Edge> edges) {
		edgeTable.addAll(edges);
	}
}
