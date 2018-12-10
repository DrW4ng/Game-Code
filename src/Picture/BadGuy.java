package Picture;

import java.awt.Image;
import java.awt.Toolkit;

public class BadGuy {
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Image img;

	public BadGuy() {
		setxCoord(10);
		setyCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("../files/Plankton.png");
		
	}
	
	
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
		public int getxCoord() {
		return xCoord;
	}
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getyCoord() {
		return yCoord;
	}
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
		public BadGuy(int x, int y, int w, int h, String imgpath) {
			setxCoord(x+50);
			setyCoord(y+50);
			setWidth(w);
			setHeight(h);
			setImg(imgpath);
		}
}
