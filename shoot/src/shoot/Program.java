package shoot;

import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Program{
	private static Timer timer;
	private static JFrame frame;
	/*
	private static World world;
	private static Entrance entrance;
	*/
	
	protected static void getTimer(JPanel panel) {
		//��ʱ��
		Program.timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				panel.repaint();
			}
		}, 0, Config.TIMER_DELAY);
	}
	public static void iniProgram() {
		frame = new JFrame("PlaneFighting");
		//���ô�������
		frame.setResizable(false);
		//���ô��ڹرղ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ڳߴ�
		frame.setSize(World.WIDTH, World.HEIGHT);
		//������ʾ
		frame.setLocationRelativeTo(null);
	}
	public Program() {
	}
	
	public static void showPanel(JPanel panel) {
		//Program.timer.cancel();
		frame.getContentPane().removeAll();
		frame.add(panel);
		frame.addKeyListener((KeyListener)panel);
		Program.getTimer(panel);
		frame.setVisible(true);
	}
	
	public static void entrance(boolean bool) {
		if (bool) {
			Program.iniProgram();
		} else {
			Program.timer.cancel();
		}
		Entrance e = new Entrance();
		Program.showPanel(e);
	}
	
	public static void playGame(boolean bool) {
		if (bool) {
			Program.iniProgram();
		} else {
			Program.timer.cancel();
		}
		World world = new World();
		Program.showPanel(world);
		frame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				world.pause();
				Timer t = new Timer();
				t.schedule(new TimerTask() {
					@Override
					public void run() {
						Program.timer.cancel();
					}
				}, 30);
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				world.continueGame();
				Program.getTimer(world);
			}
		});
	}
	
	public static void main(String[] args) {
		Program.entrance(true);
	}

}
