import java.awt.Color;
import java.awt.Graphics;

public class GameChance {
	int x3;
	int y3;

	GameChance(int x3, int y3) {
		this.x3 = x3;
		this.y3 = y3;
	}

	public int getX3() {
		return x3;
	}

	public int getY3() {
		return y3;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.MAGENTA);
		g.fillOval(x3, y3, 20, 20);
	}
}
