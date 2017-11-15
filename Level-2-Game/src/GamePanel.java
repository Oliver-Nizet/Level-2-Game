import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public final int menuState = 0;
	public final int gameState = 1;
	public final int endState = 2;
	public int currentState = menuState;
}
