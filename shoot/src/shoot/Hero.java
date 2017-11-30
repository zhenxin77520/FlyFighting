package shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
	public int firePower;
	public int fireDelay;
	private int imgIndex;
	private boolean i = true;
	private int deadIndex = 0;
	private boolean isMove = true;
	public int strongTime;
	public boolean isDead;
	private static BufferedImage[] img = new BufferedImage[6];
	private static BufferedImage strongImg;
	static {
		for (int i = 0; i < img.length; i++) {
			img[i] = FlyingObject.loadImage("/shootImg/hero"+i+".png");
		}
		strongImg = FlyingObject.loadImage("/shootImg/heroS.png");
	}
	public Hero() {
		super();
		this.life = Config.HERO_LIFE;
		this.fireDelay = Config.HERO_FIRE_DELAY;
		this.strongTime = Config.HERO_STRONGTIME;
		this.imgIndex = 0;
		this.width = 60;
		this.height = 80;
		this.x = 200;
		this.y = 550;
		this.speed = Config.HERO_SPEED;
		this.firePower = Config.HERO_FIRE_POWER;
		this.isDead = false;
	}
	
	@Override
	public void step() {
		int x = this.x;
		int y = this.y;
		if (isMove) {
			if (KeyBuffer.hasKey(38)) {//ио
				this.y-=this.speed;
			}
			if (KeyBuffer.hasKey(40)) {//об
				this.y+=this.speed;
			}
			if (KeyBuffer.hasKey(37)) {//вС
				this.x-=this.speed;
			}
			if (KeyBuffer.hasKey(39)) {//ср
				this.x+=this.speed;
			}
		}
		if (this.x>=(World.WIDTH-this.width-10)||this.x<=0) {
			this.x = x;
		}
		if (this.y>=(World.HEIGHT-this.height-20)||this.y<=0) {
			this.y = y;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		int index = 0;
		if (this.life>0) {
			this.imgIndex++;
			if (this.imgIndex==10) {
				this.i = !this.i;
				this.imgIndex = 0;
			}
			index = this.i?0:1;
		} else {
			this.isMove = false;
			this.deadIndex++;
			index = this.deadIndex/5;
			index = index>5?5:index;
			if (this.deadIndex>30) {
				this.isDead = true;
			}
		}
		if (this.strongTime > 0) {
			g.drawImage(Hero.strongImg, this.x, this.y,this.width,this.height, null);
		} else {
			g.drawImage(Hero.img[index], this.x, this.y,this.width,this.height, null);
		}
	}
	public void addBuff() {
		int n = (int)(Math.random()*3);
		switch (n) {
		case 0:
			this.speed = ++this.speed>8?8:this.speed;
			break;
		case 1:
			this.firePower = ++this.firePower>4?4:this.firePower;
			break;
		case 2:
			this.fireDelay = --this.fireDelay<3?3:this.fireDelay;
			break;
		}
	}
}
