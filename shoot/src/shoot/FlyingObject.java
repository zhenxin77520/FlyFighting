package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class FlyingObject {
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int speed;
	protected int life;
	
	static final int LIVE = 0;
	static final int REMOVE = 1;
	
	protected int state = LIVE;
	
	public static BufferedImage loadImage(String imgName){
		URL url = FlyingObject.class.getResource(imgName);
		try {
			BufferedImage img = ImageIO.read(url);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void step();
}
