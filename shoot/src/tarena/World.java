package tarena;

import java.awt.Graphics;

import javax.swing.JPanel;

public class World extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 480;
	static final int HEIGHT = 850;
	private Sky sky;
	private Hero hero;
	
	private FlyingObject[] flyingObjects;
	private Bullet[] bullets;
	
	public void action() {
		sky = new Sky();
		hero = new Hero();
		bullets = new Bullet[4];
		flyingObjects = new FlyingObject[6];
		for (int i = 0; i < 4; i++) {
			bullets[i] = new Bullet();
		}
		
		flyingObjects[0] = new AirPlane();
		flyingObjects[1] = new AirPlane();
		
		flyingObjects[2] = new BigAirPlane();
		flyingObjects[3] = new BigAirPlane();
		
		flyingObjects[4] = new Bee();
		flyingObjects[5] = new Bee();
		
		for (int i = 0; i < flyingObjects.length; i++) {
			flyingObjects[i].step();
		}
		
		sky.step();
		hero.step();
		for (int i = 0; i < 4; i++) {
			bullets[i].step();
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}
