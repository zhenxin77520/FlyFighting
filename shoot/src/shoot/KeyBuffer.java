package shoot;

import java.util.ArrayList;

public class KeyBuffer {
	private static ArrayList<Integer> list = new ArrayList<>();
	
	public static void addKey(int n) {
		if (!list.contains((Integer)n)) {
			list.add((Integer)n);
		}
	}
	public static void removeKey(int n) {
		if (list.contains((Integer)n)) {
			list.remove((Integer)n);
		}
	}
	public static boolean hasKey(int n) {
		if (list.contains((Integer)n)) {
			return true;
		} else {
			return false;
		}
	}
}
