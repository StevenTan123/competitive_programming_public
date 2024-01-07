import java.util.*;
import java.io.*;

public class hps {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		int n = Integer.parseInt(in.readLine());
		int[] onecount = new int[3];
		int[] twocount = new int[3];
		int[] sequence = new int[n];
		for(int i = 0; i < n; i++) {
			String c = in.readLine();
			if(c.equals("H")) {
				twocount[0]++;
				sequence[i] = 0;
			}else if(c.equals("P")) {
				twocount[1]++;
				sequence[i] = 1;
			}else {
				twocount[2]++;
				sequence[i] = 2;
			}
		}
		in.close();
		int max = 0;
		for(int bp = 1; bp <= n; bp++) {
			int moved = sequence[bp - 1];
			twocount[moved]--;
			onecount[moved]++;
			int gameswon = maxlist(twocount) + maxlist(onecount);
			if(gameswon > max) {
				max = gameswon;
			}
		}
		PrintWriter out = new PrintWriter("hps.out");
		out.println(max);
		out.close();
	}
	static int maxlist(int[] list) {
		int max = 0;
		for(int i = 0; i < list.length; i++) {
			if(list[i] > max) {
				max = list[i];
			}
		}
		return max;
	}
}
