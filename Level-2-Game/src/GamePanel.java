import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public final int menuState = 0;
	public final int gameState = 1;
	public final int endState = 2;
	public int currentState = menuState;

	public void paintComponent(Graphics g) {
		int o = 30;
		for (int i = 30; i < 910; i += 30) {
			g.drawRect(o, o, i, i);
		}
		int p = 0;
		for (int i = 900; i > 30; i -= 30) {
			p += 30;
			g.drawRect(i, i, p, p);
		}
	}
}
