import java.util.*;
import java.io.*;
public class hoofball {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("hoofball.in"));
		int[] cows = new int[in.nextInt()];
		for(int i = 0; i < cows.length; i++) {
			cows[i] = in.nextInt();
		}
		in.close();
		Arrays.sort(cows);
		int[] cowS = new int[cows.length];
		for(int i = 0; i < cows.length; i++) {
			int t = target(i, cows);
			cowS[t]++;
		}
		int res = 0;
		for(int i = 0; i < cowS.length; i++) {
			if(cowS[i] == 0) {
				res++;
			}
			if(cowS[i] == 1 && cowS[target(i, cows)] == 1 && i == target(target(i, cows), cows) && i < target(i, cows)) {
				res++;
			}
		}
		PrintWriter out = new PrintWriter("hoofball.out");
		out.println(res);
		out.close();
	}
	static int target(int i, int[] cows) {
		if(cows.length <= 1) {
			return -1;
		}
		int ld = 1001;
		if(i > 0) {
			ld = cows[i] - cows[i - 1];
		}
		int rd = 1001;
		if(i < cows.length - 1) {
			rd = cows[i + 1] - cows[i];
		}
		if(ld <= rd) {
			return i - 1;
		}else {
			return i + 1;
		}
	}
}
