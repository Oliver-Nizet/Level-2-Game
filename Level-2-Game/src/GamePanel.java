import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private static final char Up = 0;
	/*
	 * public final int menuState = 0; public final int gameState = 1; public final
	 * int endState = 2; public int currentState = menuState;
	 */
	int playerSize = 30;
	int playerX = 465;
	int playerY = 465;
	int menuState = 0;
	int instructionState = 1;
	int gameState = 2;
	int endState = 3;
	int currentState = menuState;
	Timer t;
	private static Random random = new Random();
	List<GameObject> list;

	public static void main(String[] args) {
		GamePanel gp = new GamePanel();
	}

	GamePanel() {
		t = new Timer(1000 / 1000, this);
		t.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == menuState) {
			Font m = new Font("Default", Font.BOLD, 75);
			setFont(m);
			g.drawString("Simple Agario", 200, 125);
			g.drawString("by Oliver Nizet", 175, 325);
			g.drawString("Press I for Instructions", 50, 525);
			g.drawString("Press S to Start", 175, 725);
		}
		if (currentState == instructionState) {
			Font i = new Font("Default", Font.BOLD, 60);
			setFont(i);
			g.drawString("Use Arrow Keys To Move", 75, 125);
			g.drawString("Collect Items To Grow", 80, 325);
			g.drawString("Press B to Return To Main", 75, 525);
			g.drawString("Press S to Start", 175, 725);
		}
		if (currentState == gameState) {
			g.setColor(Color.lightGray);
			int o = 30;
			for (int i = 30; i < 910; i += 30) {
				g.drawRect(o, o, i, i);
			}
			int p = 0;
			for (int i = 900; i > 30; i -= 30) {
				p += 30;
				g.drawRect(i, i, p, p);
			}
			if (playerY < 30) {
				playerY = 30;
			}
			if (playerY > 900) {
				playerY = 900;
			}
			if (playerX < 30) {
				playerX = 30;
			}
			if (playerX > 900) {
				playerX = 900;
			}
			g.setColor(Color.BLUE);
			g.fillOval(playerX, playerY, playerSize, playerSize);
			g.setColor(Color.BLACK);
			Font s = new Font("Default", Font.BOLD, 24);
			g.setFont(s);
			g.drawString("Score: " + (playerSize - 30), 10, 22);
			if (list == null) {
				list = initializeList(0);
			}
			for (GameObject go : list) {
				go.draw(g);
			}
		}
	}

	public List<GameObject> initializeList(int size) {
		List<GameObject> l = new ArrayList<>();
		for (int i = size; i < 10; i++) {
			GameObject go = new GameObject(random.nextInt(900) + 50, random.nextInt(900) + 50);
			l.add(go);
		}
		return l;
	}

	public void Check() {
		if (0 == 0) {
			// (for if statement) playerX - (playerSize/2) <= ("itemX" - 10) && playerY -
			// (playerSize/2) <= ("itemY" - 10)
			// removeItem;
			// addItem;
			playerSize++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState == menuState || currentState == instructionState) {
			if (e.getKeyCode() == KeyEvent.VK_I) {
				currentState = instructionState;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				currentState = gameState;
			}
			if (e.getKeyCode() == KeyEvent.VK_B) {
				currentState = menuState;
			}
		}
		if (currentState == gameState) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				playerY -= 15;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				playerY += 15;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				playerX -= 15;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				playerX += 15;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_H && e.getKeyCode() == KeyEvent.VK_A && e.getKeyCode() == KeyEvent.VK_C
				&& e.getKeyCode() == KeyEvent.VK_K) {
			playerSize += 10;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}