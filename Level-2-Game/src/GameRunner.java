import javax.swing.JFrame;

public class GameRunner {
	GamePanel panel;
	JFrame window;

	public static void main(String[] args) {
		GameRunner gr = new GameRunner();
	}

	GameRunner() {
		panel = new GamePanel();
		window = new JFrame();
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(960, 980);
		window.setVisible(true);
		window.addKeyListener(panel);
	}
}
