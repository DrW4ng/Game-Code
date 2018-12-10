package Picture;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener {
	
	GoodGuy backdrop = new GoodGuy(-200,-200,800,1600,"files/Krusty.png");
	GoodGuy formula = new GoodGuy(-200,150,100,100,"files/Formula.png");
	GoodGuy scoreboard = new GoodGuy(-190, -200, 100,200,"files/Scoreboard.png");
	GoodGuy link = new GoodGuy(-100,100,150,150,"files/TposeRight.png");
	GoodGuy Over = new GoodGuy(-80,-150,600,1200,"files/GameOver.png");
	GoodGuy Victory = new GoodGuy(-80,-150,600,1200,"files/victory.png");
	GoodGuy liveboard = new GoodGuy(-190,-150,100,200,"files/LiveBoard.png");
	GoodGuy startscreen = new GoodGuy(-200,-200,800,1600,"files/StartScreen.png");
	LinkedList badguys = new LinkedList();
	LinkedList knives = new LinkedList();
	
	
	int time = 0;
	public boolean lose = false;
	public boolean Startgame = false;
	int score;
	int lives = 3;
	
	
	public MyCanvas() {

		this.setSize(1600,800);
		this.addKeyListener(this);
		playIt("files/Hula.wav");
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
	
			for (int i = 0; i < 40; i++) {
				BadGuy bg = new BadGuy(rand.nextInt(winwidth-100)+1650, rand.nextInt(winheight-100),100,100,"files/Plankton.png");
				Rectangle r = new Rectangle(100,100,30,30);	
					
				badguys.add(bg);
			}
	}
	
	
		
		
	

	
	public void paint(Graphics g) {
		
		if (Startgame == false) {
			g.drawImage(startscreen.getImg(), startscreen.getxCoord(), startscreen.getyCoord(), startscreen.getHeight(), startscreen.getWidth(), this);
		}else{
			g.drawImage(backdrop.getImg(), backdrop.getxCoord(), backdrop.getyCoord(), backdrop.getHeight(), backdrop.getWidth(), this);
			g.setFont(new Font("Symtext", Font.PLAIN, 30));
			g.drawImage(scoreboard.getImg(), scoreboard.getxCoord(), scoreboard.getyCoord(), scoreboard.getHeight(), scoreboard.getWidth(), this);
			g.drawImage(liveboard.getImg(), liveboard.getxCoord(), liveboard.getyCoord(), liveboard.getHeight(), liveboard.getWidth(), this);
			g.drawString(Integer.toString(score),155,58);
			g.drawString(Integer.toString(lives), 155, 110);
			
		
			
			time++;
				
				
				if (lose == false ) {
					g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getHeight(), link.getWidth(), this);
					
					g.drawImage(formula.getImg(), formula.getxCoord(), formula.getyCoord(), formula.getHeight(), formula.getWidth(), this);
					for(int i = 0; i < badguys.size(); i++) {
						System.out.println(score);
						
						
						
						BadGuy bg = (BadGuy) badguys.get(i);
						g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
						Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getHeight(), bg.getWidth());
						if (time%2==0) {
							bg.setxCoord(bg.getxCoord()- 1);
						} repaint();
						
						
							for(int j = 0; j < knives.size(); j++) {
							projectile k = (projectile) knives.get(j);
							if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
							if (time%8==0) {
								k.setxCoord(k.getxCoord() + 1);
							
		
							} 
							g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);
							
							Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getHeight(),k.getWidth());
							if (kr.intersects(r)) {
								badguys.remove(i);
								knives.remove(j);
								score++;
								playIt("files/splat.wav");
							} repaint();
							
						} repaint();
						if (bg.getxCoord()==100.00) {
							lives--;
							badguys.remove(i);
							//		lose = true;
						}
						if (score >= 30) {
							g.drawImage(Victory.getImg(), Victory.getxCoord(), Victory.getyCoord(), Victory.getHeight(), Victory.getWidth(), this);
							score=30;
							lives=3;
						}
						if (lives==0) {
							lose = true;
						}
					}
					
					
				}else{
					g.drawImage(Over.getImg(), Over.getxCoord(), Over.getyCoord(), Over.getHeight(), Over.getWidth(), this);
		
		}
	}
}
		
	
	
		

			

	public void keyPressed(KeyEvent e) {
		
		
		
		
		
		
		if (e.getKeyCode() == 32) {
			projectile knife = new projectile(link.getxCoord(),link.getyCoord(),40,40,"files/Patty.png");
			knives.add(knife);
			
		}
		if (e.getKeyCode() == 17) {
			Startgame = true;
		}
		link.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		repaint();
		
		
			
			
				}
	
	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} 

}

