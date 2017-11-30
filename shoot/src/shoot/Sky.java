package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sky extends FlyingObject{
	private int height;
	private static BufferedImage img;
	
	static {
		Sky.img = FlyingObject.loadImage("/shootImg/background.png");
	}
	
	public Sky() {
		super();
		this.height = 1400;
		this.y = -(1400-World.HEIGHT);//-800;
	}
	
	@Override
	public void step() {
		this.y = ++this.y>0?-(this.height-World.HEIGHT):this.y;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, 0, this.y, null);
	}
}
