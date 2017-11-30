package shoot;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Entrance extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index;
	private int x;
	private int y;
	private int flyindex;
	private boolean isflsh;
	private boolean i = true;
	private static BufferedImage bgimg;
	private static BufferedImage[] heroimg = new BufferedImage[2];
	
	static {
		bgimg = FlyingObject.loadImage("/shootImg/start.png");
		for (int i = 0; i < heroimg.length; i++) {
			heroimg[i] = FlyingObject.loadImage("/shootImg/hero"+i+".png");
		}
	}
	
	public Entrance() {
		this.index = 0;
		this.flyindex = 0;
		this.isflsh = true;
		this.x = 190;
		this.y = 400;
		this.addMouse();
	}
	public void startGo() {
		this.isflsh = false;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bgimg, 0, 0, World.WIDTH, World.HEIGHT-30, null);
		
		int x = 0;
		if (!this.isflsh) {
			this.flyindex+=3;
			this.y-=this.flyindex;
			
			if (this.y<=-100) {
				Program.playGame(false);
			}
		} 
		this.index++;
		if (this.index==10) {
			this.i = !this.i;
			this.index = 0;
		}
		x = this.i?0:1;
		
		
		g.drawImage(heroimg[x], this.x, this.y, 80, 100, null);
	}
	public void addMouse() {
		boolean isf = this.isflsh;
		Entrance en = this;
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (isf && e.getButton() == MouseEvent.BUTTON1) {
					Rectangle plane = new Rectangle(en.x, en.y, 80, 100);
					Rectangle em = new Rectangle(e.getX(), e.getY(), 1, 1);
					if (plane.intersects(em)) {
						en.startGo();
					}
				}
			}
		};
		this.addMouseListener(mouseAdapter);
	}
	/**
	 * ¼üÅÌÊÂ¼þ
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			this.startGo();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
