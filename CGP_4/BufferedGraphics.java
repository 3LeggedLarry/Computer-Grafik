import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/**
 * @author grauschopf
 *
 */

public class BufferedGraphics extends Graphics {

	private Color[][] buffer;
	private Color paintingColor;

	public BufferedGraphics(int width, int height) {
		buffer = new Color[width][height];
	}

	public Color get(int x, int y) {
		return buffer[x][y];
	}

	@Override
	public void clearRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clipRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void copyArea(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public Graphics create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, ImageObserver arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, Color arg3, ImageObserver arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, ImageObserver arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, Color arg5, ImageObserver arg6) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			ImageObserver arg9) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean drawImage(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			Color arg9, ImageObserver arg10) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void drawLineXwise(int x0, int y0, int x1, int y1) {
		double m = ((double) (y1 - y0)) / (x1 - x0);
		double b = y0 - m * x0;
		for (int x = x0; x <= x1; x++) {
			double y = m * x + b;
			buffer[x][(int) (y + 0.5)] = paintingColor;
		}
	}
	
	private void drawLineYwise(int x0, int y0, int x1, int y1) {
		double m = ((double) (y1 - y0)) / (x1 - x0);
		double b = y0 - m * x0;
		for (int y = y0; y <= y1; y++) {
			double x = (y - b) / m;
			buffer[(int) (x + 0.5)][y] = paintingColor;
		}
	}

	@Override
	public void drawLine(int x0, int y0, int x1, int y1) {
		boolean finished = false;
		if (!finished) {
			if (x0 == x1) {
				// vertikale Linie
				for (int y = y0; y <= y1; y++) {
					buffer[x1][y] = paintingColor;
				}
			}
			else {
				int diffx = x1 - x0;
				int diffy = y1 - y0;
				if (Math.abs(diffx) >= Math.abs(diffy)) {
					if (x1 >= x0) {
						drawLineXwise(x0, y0, x1, y1);
					}
					else {
						drawLineXwise(x1, y1, x0, y0);
					}
				}
				else {
					if (y1 >= y0) {
						drawLineYwise(x0, y0, x1, y1);
					}
					else {
						drawLineYwise(x1, y1, x0, y0);
					}
				}
			}			
			finished = true;
		}
	}

	@Override
	public void drawOval(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPolygon(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPolyline(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawString(String arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawString(AttributedCharacterIterator arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillOval(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillPolygon(int[] arg0, int[] arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillRect(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape getClip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getClipBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClip(Shape arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setClip(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColor(Color paintingColor) {
		this.paintingColor = paintingColor;
	}

	@Override
	public void setFont(Font arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPaintMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setXORMode(Color arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void translate(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * Um die konventionelle Bildschirmorientierung zu erhalten wird die Ausgabe des
	 * buffers spalten- statt zeilenweise vorgenommen.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(buffer.length * buffer[0].length);
		for (int col = 0; col < buffer[0].length; col++) {
			for (int row = 0; row < buffer[0].length; row++) {
				stringBuilder.append((buffer[row][col] == null) ? '.' : 'X');
			}
			stringBuilder.append('\n');
		}
		return stringBuilder.toString();
	}
}
