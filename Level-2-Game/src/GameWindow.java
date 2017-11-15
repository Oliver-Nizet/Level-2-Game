import javax.swing.JFrame;

public class GameWindow {
	GamePanel panel;
	JFrame window;

	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
	}

	GameWindow() {
		window.add(panel);
	}
}
