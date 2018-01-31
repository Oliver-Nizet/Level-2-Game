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
	int playerSize = 30;
	int playerX = 480;
	int playerY = 480;
	int menuState = 0;
	int instructionState = 1;
	int gameState = 2;
	int endState = 3;
	int winState = 4;
	int loseState = 5;
	int currentState = menuState;
	Timer t;
	private static Random random = new Random();
	List<GameObject> list;
	List<GameObstacle> list2;
	List<GameChance> list3;

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
			if ((playerSize / 3 - 10) >= 100) {
				currentState = winState;
			}
			if ((playerSize / 3 - 10) <= -5) {
				currentState = loseState;
			}
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
			if (playerY - (playerSize / 2) < 30) {
				playerY = (playerSize / 2 + 30);
			}
			if (playerY + (playerSize / 2) > 930) {
				playerY = 930 - (playerSize / 2);
			}
			if (playerX - (playerSize / 2) < 30) {
				playerX = (playerSize / 2) + 30;
			}
			if (playerX + (playerSize / 2) > 930) {
				playerX = 930 - (playerSize / 2);

			}
			g.setColor(Color.BLUE);
			g.fillOval(playerX - (playerSize / 2), playerY - (playerSize / 2), playerSize, playerSize);
			g.setColor(Color.BLACK);
			Font s = new Font("Default", Font.BOLD, 24);
			g.setFont(s);
			g.drawString("Score: " + (playerSize / 3 - 10), 10, 22);
			if (list == null) {
				list = initializeList(0);
			}
			if (list2 == null) {
				list2 = initializeList2(0);
			}
			if (list3 == null) {
				list3 = initializeList3(0);
			}
			for (GameObject go : list) {
				go.draw(g);
			}
			for (GameObstacle ob : list2) {
				ob.draw(g);
			}
			for (GameChance ch : list3) {
				ch.draw(g);
			}
			check();
		}
		if (currentState == winState) {
			g.drawString("You Reached 100", 200, 100);
			g.drawString("Points and Won!", 200, 300);
		}
		if (currentState == loseState) {
			g.drawString("You Got a Score of -5", 50, 100);
			g.drawString("or Less and Lost.", 175, 300);
		}
	}

	public List<GameObject> initializeList(int size) {
		List<GameObject> l = new ArrayList<>();
		for (int i = size; i < 10; i++) {
			GameObject go = new GameObject(random.nextInt(820) + 60, random.nextInt(820) + 60);
			l.add(go);
		}
		return l;
	}

	public List<GameObstacle> initializeList2(int size) {
		List<GameObstacle> o = new ArrayList<>();
		for (int i = size; i < 5; i++) {
			GameObstacle ob = new GameObstacle(random.nextInt(820) + 60, random.nextInt(820) + 60);
			o.add(ob);
		}
		return o;
	}

	public List<GameChance> initializeList3(int size) {
		List<GameChance> c = new ArrayList<>();
		for (int i = size; i < 3; i++) {
			GameChance ch = new GameChance(random.nextInt(820) + 60, random.nextInt(820) + 60);
			c.add(ch);
		}
		return c;
	}

	public void check() {
		for (int i = 0; i < list.size(); i++) {
			GameObject go = list.get(i);
			// System.out.println(i + " : (" + go.x + ", " + go.y + ") @ (" + playerX + ", "
			// + playerY + ")");
			if (intersect(playerX, playerY, go.x, go.y, playerSize / 2, 10)) {
				go.x = random.nextInt(820) + 60;
				go.y = random.nextInt(820) + 60;
				playerSize += 3;
			}
		}
		for (int i = 0; i < list2.size(); i++) {
			GameObstacle ob = list2.get(i);
			if (intersect(playerX, playerY, ob.x2, ob.y2, playerSize / 2, 10)) {
				ob.x2 = random.nextInt(820) + 60;
				ob.y2 = random.nextInt(820) + 60;
				playerSize -= 9;
			}
		}
		for (int i = 0; i < list3.size(); i++) {
			GameChance ch = list3.get(i);
			if (intersect(playerX, playerY, ch.x3, ch.y3, playerSize / 2, 10)) {
				ch.x3 = random.nextInt(820) + 60;
				ch.y3 = random.nextInt(820) + 60;
				int x = random.nextInt(10) + 1;
				if (x % 2 == 0) {
					playerSize += 15;
				} else {
					playerSize -= 15;
				}
			}
		}
	}

	public boolean intersect(int x1, int y1, int x2, int y2, int s1, int s2) {
		int dx = (x1 - x2) * (x1 - x2);
		int dy = (y1 - y2) * (y1 - y2);
		int ds = (s1 + s2) * (s1 + s2);
		return dx + dy <= ds;
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
		if (e.getKeyCode() == KeyEvent.VK_H) {
			playerSize += 30;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}