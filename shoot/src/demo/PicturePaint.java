package demo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shoot.FlyingObject;

public class PicturePaint extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static BufferedImage image;
	static BufferedImage img;
	static {
		img = FlyingObject.loadImage("/source/el0.bmp");
		URL u = PicturePaint.class.getResource("/source/el0.bmp");
		System.out.println(u);
		try {
			image =  ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("lala");
		PicturePaint p = new PicturePaint();
		frame.add(p);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("1111");
		g.drawImage(img, 0, 0,50,50, null);
	}
}
