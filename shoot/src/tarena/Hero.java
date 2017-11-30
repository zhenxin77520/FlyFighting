package tarena;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Hero extends FlyingObject{
	private int life;
	private int doubleFire;
	private static BufferedImage[] img = new BufferedImage[2];
	
	@Override
	public void step() {
		
	}
	static {
		for (int i = 0; i < img.length; i++) {
			img[i] = FlyingObject.loadImage("/shootImg/hero"+i+".png");
		}
	}
	public Hero() {
		super();
		this.life = 3;
		this.width = 60;
		this.height = 80;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getDoubleFire() {
		return doubleFire;
	}

	public void setDoubleFire(int doubleFire) {
		this.doubleFire = doubleFire;
	}

}
