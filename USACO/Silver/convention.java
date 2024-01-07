import java.util.*;
import java.io.*;
public class convention {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("convention.in"));
		int cows = in.nextInt();
		int buses = in.nextInt();
		int buscap = in.nextInt();
		int[] carrive = new int[cows];
		for(int i = 0; i < carrive.length; i++) {
			carrive[i] = in.nextInt();
		}
		in.close();
		Arrays.sort(carrive);
		PrintWriter out = new PrintWriter("convention.out");
		out.println(binarySearch(carrive, buscap, buses, 0, 1000000000));
		out.close();
	}
	static int binarySearch(int[] carrive, int buscap, int buses, int l, int r) {
		if(l == r) {
			if(willSuffice(carrive, l, buscap, buses)) {
				return l;
			}else {
				return -1;
			}
		}else if(r < l) {
			return l;
		}
		int middle = (l + r) / 2;
		boolean ws = willSuffice(carrive, middle, buscap, buses);
		int bs;
		if(ws) {
			bs = binarySearch(carrive, buscap, buses, l, middle - 1);
		}else {
			bs = binarySearch(carrive, buscap, buses, middle + 1, r);
		}
		if(ws) {
			if(bs == -1) {
				return middle;
			}
		}
		return bs;
	}
	static boolean willSuffice(int[] carrive, int maxtime, int buscap, int buses) {
		boolean isStart = true;
		int prevb = 0;
		for(int i = 0; i < carrive.length; i++) {
			if(isStart) {
				prevb = i;
			}
			isStart = false;
			if(i - prevb + 1 >= buscap) {
				isStart = true;
				buses--;
			}else if(i < carrive.length - 1) {
				if(carrive[i + 1] - carrive[prevb] > maxtime) {
					isStart = true;
					buses--;
				}
			}else if(i == carrive.length - 1) {
				isStart = true;
				buses--;
			}
			if(buses < 0) {
				return false;
			}
		}
		return true;
	}
}
