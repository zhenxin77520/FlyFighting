package tarena;

import java.awt.image.BufferedImage;

public class Sky extends FlyingObject{
	private int y1;
	private int step;
	private static BufferedImage img;
	
	static {
		Sky.img = FlyingObject.loadImage("/shootImg/background.png");
	}
	
	public Sky() {
		super();
		
	}
	
}
