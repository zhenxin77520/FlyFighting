package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	public boolean isBomb;
	private int bombIndex;
	private static BufferedImage img;
	private static BufferedImage[] bombImg = new BufferedImage[3];
	static {
		img = loadImage("/shootImg/bullet.png");//bulletB 
		for (int i = 0; i < bombImg.length; i++) {
			bombImg[i] = FlyingObject.loadImage("/shootImg/bomb"+i+".png");
		}
	}
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 8;
		this.height = 16;
		this.isBomb = false;
		this.bombIndex = 0;
		this.speed = Config.BULLET_SPEED;
	}
	@Override
	public void step() {
		this.y-=this.speed;
		if (this.y<=0) {
			this.state = FlyingObject.REMOVE;
		}
	}
	@Override
	public void draw(Graphics g) {
		if (isBomb) {
			this.bombIndex = ++this.bombIndex>2?0:this.bombIndex;
			g.drawImage(bombImg[this.bombIndex], this.x-7, this.y-5, 20, 20, null);
			if (this.bombIndex==2) {
				this.state = FlyingObject.REMOVE;
			}
		} else {
			g.drawImage(img, this.x, this.y, this.width, this.height, null);
		}
	}
}
