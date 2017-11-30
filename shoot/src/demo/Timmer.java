package demo;

import java.util.Timer;
import java.util.TimerTask;

public class Timmer {
	public static void main(String[] args) {
		long mills = 2000;
		Timer timer = new Timer();
		System.out.println(0);
		timer.schedule(new TimerTask() {
			int i = 0;
			@Override
			public void run() {
				System.out.println("lala:"+(++i));
			}
		}, mills, mills);
	}
}
