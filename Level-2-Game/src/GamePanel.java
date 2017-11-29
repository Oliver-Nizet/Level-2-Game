import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

	/*
	 * public final int menuState = 0; public final int gameState = 1; public final
	 * int endState = 2; public int currentState = menuState;
	 */
	int playerSize = 30;
	int playerX = 465;
	int playerY = 495;
	Timer t;

	public static void main(String[] args) {
		GamePanel gp = new GamePanel();
	}

	GamePanel() {
		t = new Timer(1000 / 60, this);
		t.start();
	}

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
		g.setColor(Color.BLUE);
		g.fillOval(playerX, playerY, playerSize, playerSize);
		g.setColor(Color.BLACK);
		Font f = new Font("Default", Font.BOLD, 24);
		g.setFont(f);
		g.drawString("Score: " + (playerSize - 30), 10, 22);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
}
