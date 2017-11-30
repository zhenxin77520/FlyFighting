package tarena;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class FlyingObject {
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	
	//三个生命状态;
	static final int LIFE = 0;
	static final int DEAD = 1;
	static final int REMOVE = 2;
	//当前状态
	protected int state = LIFE;
	
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
	
	public void step() {
		System.out.println("移动了");
	}
}
