package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bee  extends FlyingObject{
	private int awardType;
	private int index;
	private static BufferedImage[] img = new BufferedImage[5];
	static {
		for (int i = 0; i < img.length; i++) {
			img[i] = loadImage("/shootImg/bee"+i+".png");
		}
	}
	public Bee() {
		super();
		this.awardType = 0;
		this.index = 0;
		this.life = Config.BEE_LIFE;
		this.width=40;
		this.height = 30;
		this.speed = 2;
		this.x = (int)(Math.random()*(World.WIDTH-this.width-10));
		this.y = -50;
	}
	
	public void step() {
		switch (this.awardType) {
		case 0:
			this.x-=this.speed;
			this.y+=this.speed;
			break;
		case 1:
			this.x+=this.speed;
			this.y+=this.speed;
			break;
		}
		if (this.x<=0||this.x>=(World.WIDTH-this.width-10)) {
			this.awardType = this.awardType==0?1:0;
		}
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
		
		g.drawImage(Bee.img[this.index], this.x, this.y,this.width,this.height, null);
	}
	
}