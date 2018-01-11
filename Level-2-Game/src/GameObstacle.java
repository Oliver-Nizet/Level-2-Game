import java.awt.Color;
import java.awt.Graphics;

public class GameObstacle {
	int x2;
	int y2;

	GameObstacle(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.MAGENTA);
		g.fillOval(x2, y2, 20, 20);
	}
}
