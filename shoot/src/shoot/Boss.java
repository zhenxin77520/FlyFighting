package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boss extends FlyingObject {
	private int imgIndex = 0;
	private static BufferedImage[] img = new BufferedImage[6];
	static {
		for (int i = 0; i < img.length; i++) {
			img[i] = FlyingObject.loadImage("/shootImg/boss"+i+".png");
		}
	}
	public Boss() {
		this.width = 250;
		this.height = 200;
		this.x = (World.WIDTH/2) - (this.width/2);
		this.y = -this.height;
		this.life = Config.BOSS_LIFE;
	}
	@Override
	public void draw(Graphics g) {
		if (this.life <= 0) {
			this.imgIndex = ++this.imgIndex > 5 ? 0 : this.imgIndex;
			if (this.imgIndex==5) {
				this.state = FlyingObject.REMOVE;
			}
		}
		g.drawImage(img[imgIndex], this.x, this.y, this.width, this.height, null);
	}

	@Override
	public void step() {
		this.y += this.y >=0 ? 0 : 1;
	}

}
