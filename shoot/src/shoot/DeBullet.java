package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DeBullet extends FlyingObject {
	private int heroX;
	private int heroY;
	private static BufferedImage img;
	static {
		img = FlyingObject.loadImage("/shootImg/debullet.png");
	}
	public DeBullet(int x, int y, int hx, int hy) {
		this.x = x;
		this.y = y;
		this.heroX = hx;
		this.heroY = hy;
		this.width = 8;
		this.height = 8;
		this.life = 5;
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(img, this.x, this.y, this.width, this.height, null);
	}

	@Override
	public void step() {
		int speed = Config.DEBULLET_SPEED;
	
		if (Math.abs(this.x-this.heroX)>speed/2&&Math.abs(this.y-this.heroY)>speed) {
		this.x += this.x < this.heroX ? speed/2 : this.x==this.heroX?0:-speed/2;
		}
		if (Math.abs(this.y-this.heroY)>speed) {
		this.y += this.y < this.heroY ? speed : this.y==this.heroY?0:-speed;
		}
		if (Math.abs(this.y-this.heroY)<=speed) {
			this.state = FlyingObject.REMOVE;
		}
	}

}
