package tarena;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Program{
	public static void main(String[] args) {
		
		//创建窗体对象    
		//JFrame("标题");
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
		
		//设置窗口关闭操作
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//窗口尺寸
		frame.setSize(World.WIDTH, World.HEIGHT);
		//居中显示
		frame.setLocationRelativeTo(null);
		//窗口显示
		frame.setVisible(true);
	}
}
