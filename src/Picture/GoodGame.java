package Picture;

import java.awt.Color;
import javax.swing.JFrame;
public class GoodGame extends JFrame {
	public GoodGame() {
		this.setBackground(Color.black);
		this.setSize(1600,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		GoodGame screen = new GoodGame();
		MyCanvas canvas = new MyCanvas();
		screen.getContentPane().add(canvas);
	}
}