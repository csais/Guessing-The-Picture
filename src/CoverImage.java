import java.awt.Color;
import java.awt.Graphics2D;

public class CoverImage {
	public int map[][];
	public int rectHeight;
	public int rectWidth;

	public CoverImage(int row, int col, int width, int height) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		rectWidth = width / col;
		rectHeight = height / row;
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) { // only draw if 1
					g.setColor(Color.lightGray);
					g.fillRect(j * rectWidth, i * rectHeight, rectWidth, rectHeight);
				}
			}
		}
	}

	public void clearAll() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 0;
			}
		}
	}

	public void setCoverValue(int value, int row, int col) {
		map[row][col] = value;
	}
}
