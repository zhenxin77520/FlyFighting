package tarena;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Program{
	public static void main(String[] args) {
		
		//�����������    
		//JFrame("����");
		JFrame frame = new JFrame("PlaneFighting");
		
		World world = new World();
		world.action();
		
		
		JButton btn = new JButton("btn");
		
		world.add(btn);
		
		frame.add(world);
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				world.repaint();
			}
		}, 0, 30);
		
		//���ô��ڹرղ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���ڳߴ�
		frame.setSize(World.WIDTH, World.HEIGHT);
		//������ʾ
		frame.setLocationRelativeTo(null);
		//������ʾ
		frame.setVisible(true);
	}
}
