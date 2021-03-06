import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	float playerX = 480;
	float playerY = 480;
	int menuState = 0;
	int instructionState = 1;
	int gameState = 2;
	int endState = 3;
	int winState = 4;
	int loseState = 5;
	int currentState = menuState;
	int level1 = 1;
	int level2 = 2;
	int level3 = 3;
	int level4 = 4;
	int level5 = 5;
	int currentLevel = level1;
	boolean moveUp = false;
	boolean moveDown = false;
	boolean moveLeft = false;
	boolean moveRight = false;
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
			g.drawString("Press I for Instructions", 50, 425);
			g.drawString("Press S to Start", 175, 725);
		}
		if (currentState == instructionState) {
			Font i = new Font("Default", Font.BOLD, 55);
			setFont(i);
			g.drawString("Use Arrow Keys To Move", 120, 125);
			g.drawString("Green Orbs Give Points", 145, 250);
			g.drawString("Red Orbs Take Points", 180, 375);
			g.drawString("Purple Orbs Give or Take Points", 25, 500);
			g.drawString("Press B to Return To Main", 120, 625);
			g.drawString("Press S to Start", 260, 750);
		}
		if (currentState == gameState) {
			if (moveUp == true) {
				playerY -= 0.75;
			}
			if (moveDown == true) {
				playerY += 0.75;
			}
			if (moveLeft == true) {
				playerX -= 0.75;
			}
			if (moveRight == true) {
				playerX += 0.75;
			}
			if ((playerSize / 3 - 10) >= 50) {
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
			g.fillOval((int) playerX - (playerSize / 2), (int) playerY - (playerSize / 2), playerSize, playerSize);
			g.setColor(Color.BLACK);
			Font s = new Font("Default", Font.BOLD, 24);
			g.setFont(s);
			g.drawString("Score: " + (playerSize / 3 - 10), 10, 22);
			g.drawString("Level: " + currentLevel, 850, 22);
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
			if (currentState == winState) {
				playerSize = 30;
				playerX = 480;
				playerY = 480;
				currentLevel++;
				list = initializeList(0);
				list2 = initializeList2(0);
				list3 = initializeList3(0);
				currentState = gameState;
			}
		}
		if (currentState == loseState) {
			g.drawString("You Got a Score of -5", 50, 100);
			g.drawString("or Less and Lost", 170, 225);
			g.setColor(Color.RED);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(175));
			g.drawLine(320, 440, 640, 760);
			g.drawLine(640, 440, 320, 760);
		}
		if (currentLevel == 6) {
			currentState = endState;
		}
		if (currentState == endState) {
			Font w = new Font("Default", Font.BOLD, 200);
			g.setFont(w);
			g.drawString("You Win!", 30, 200);
			drawStar(g, Color.YELLOW, 5, 480, 585, 250, 250);
		}

	}

	public double circleX(int sides, int angle) {
		double coeff = (double) angle / (double) sides;
		return Math.cos(2 * coeff * Math.PI - (Math.PI / 2));
	}

	public double circleY(int sides, int angle) {
		double coeff = (double) angle / (double) sides;
		return Math.sin(2 * coeff * Math.PI - (Math.PI / 2));
	}

	public void drawStar(Graphics g, Color c, int sides, int x, int y, int w, int h) {
		Color colorSave = g.getColor();
		g.setColor(c);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(175, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		for (int i = 0; i < sides; i++) {
			int x1 = (int) (circleX(sides, i) * (double) (w)) + x;
			int y1 = (int) (circleY(sides, i) * (double) (h)) + y;
			int x2 = (int) (circleX(sides, (i + 2) % sides) * (double) (w)) + x;
			int y2 = (int) (circleY(sides, (i + 2) % sides) * (double) (h)) + y;
			g.drawLine(x1, y1, x2, y2);
		}
	}

	public List<GameObject> initializeList(int size) {
		int g = 0;
		if (currentLevel == level1) {
			g = 10;
		} else if (currentLevel == level2) {
			g = 8;
		} else if (currentLevel == level3) {
			g = 6;
		} else if (currentLevel == level4) {
			g = 4;
		} else if (currentLevel == level5) {
			g = 2;
		}
		List<GameObject> l = new ArrayList<>();
		for (int i = size; i < g; i++) {
			GameObject go = new GameObject(random.nextInt(820) + 60, random.nextInt(820) + 60);
			l.add(go);
		}
		return l;
	}

	public List<GameObstacle> initializeList2(int size) {
		int r = 0;
		if (currentLevel == level1) {
			r = 5;
		} else if (currentLevel == level2) {
			r = 7;
		} else if (currentLevel == level3) {
			r = 9;
		} else if (currentLevel == level4) {
			r = 11;
		} else if (currentLevel == level5) {
			r = 13;
		}
		List<GameObstacle> o = new ArrayList<>();
		for (int i = size; i < r; i++) {
			GameObstacle ob = new GameObstacle(random.nextInt(820) + 60, random.nextInt(820) + 60);
			o.add(ob);
		}
		return o;
	}

	public List<GameChance> initializeList3(int size) {
		int p = 0;
		if (currentLevel == level1) {
			p = 3;
		} else if (currentLevel == level2) {
			p = 3;
		} else if (currentLevel == level3) {
			p = 3;
		} else if (currentLevel == level4) {
			p = 3;
		} else if (currentLevel == level5) {
			p = 3;
		}
		List<GameChance> c = new ArrayList<>();
		for (int i = size; i < p; i++) {
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
				playerSize += 9;
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
				int x = random.nextInt(100) + 1;
				if (x % 2 == 0) {
					playerSize += 15;
				} else {
					playerSize -= 15;
				}
			}
		}
	}

	public boolean intersect(float playerX2, float playerY2, int x2, int y2, int s1, int s2) {
		float dx = (playerX2 - x2) * (playerX2 - x2);
		float dy = (playerY2 - y2) * (playerY2 - y2);
		float ds = (s1 + s2) * (s1 + s2);
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
				moveUp = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				moveDown = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveLeft = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveRight = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_O) {
				playerSize += 150;
			}
			if (e.getKeyCode() == KeyEvent.VK_P) {
				playerSize -= 175;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (currentState == gameState) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				moveUp = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				moveDown = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveLeft = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveRight = false;
			}
		}
	}
}