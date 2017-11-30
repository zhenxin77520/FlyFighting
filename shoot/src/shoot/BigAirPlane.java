package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BigAirPlane extends FlyingObject{
	private int imgIndex = 0;
	private static BufferedImage[] img = new BufferedImage[5];
	static {
		for (int i = 0; i < img.length; i++) {
			img[i] = FlyingObject.loadImage("/shootImg/bigplane"+i+".png");
		}
	}
	public BigAirPlane() {
		this.width = 120;
		this.height = 90;
		this.speed = 1;
		this.x = (int)(Math.random()*(World.WIDTH-this.width));
		this.y = -this.height;
		this.life = Config.BIGPLANE_LIFE;
	}
	@Override
	public void step() {
		this.y+=this.speed;
		if (this.y>=World.HEIGHT) {
			this.state = FlyingObject.REMOVE;
		}
	}
	@Override
	public void draw(Graphics g) {
		if (this.life<=0) {
			this.imgIndex = ++this.imgIndex>4?0:this.imgIndex;
			if (this.imgIndex==4) {
				this.state = FlyingObject.REMOVE;
			}
		}
		g.drawImage(img[this.imgIndex],this.x, this.y, this.width, this.height, null);
	}
}
