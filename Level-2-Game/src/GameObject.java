import java.awt.Color;
import java.awt.Graphics;

public class GameObject {
	int x, y;

	GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillOval(x, y, 20, 20);
	}
}
