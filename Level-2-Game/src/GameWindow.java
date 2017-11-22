import javax.swing.JFrame;

public class GameWindow {
	GamePanel panel;
	JFrame window;

	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
	}

	GameWindow() {
		panel = new GamePanel();
		window = new JFrame();
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(960, 980);
		window.setVisible(true);
	}
}
