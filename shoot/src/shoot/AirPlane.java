package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AirPlane extends FlyingObject{
	private int index = 0;
	public boolean isShoot;
	private static BufferedImage[] img = new BufferedImage[5];
	static {
		for (int i = 0; i < img.length; i++) {
			img[i] = FlyingObject.loadImage("/shootImg/airplane"+i+".png");
		}
	}
	public AirPlane() {
		super();
		this.isShoot = false;
		this.life = Config.AIRPLANE_LIFE;
		this.width = 70;
		this.height = 55;
		this.speed = 2;
		this.x = (int)(Math.random()*(World.WIDTH-this.width));
		this.y = -36;
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
			this.index = ++this.index>4?0:this.index;
			if (this.index==4) {
				this.state = FlyingObject.REMOVE;
			}
		}
		//System.out.println(this.life);
		g.drawImage(AirPlane.img[this.index], this.x, this.y,this.width,this.height,null);
	}
}
