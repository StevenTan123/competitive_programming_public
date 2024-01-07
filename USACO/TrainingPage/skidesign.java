/*
ID: tanstev1
LANG: JAVA
PROB: skidesign
 */

import java.io.*;

public class skidesign {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
		int n = Integer.parseInt(in.readLine());
		int[] mountains = new int[n];
		for(int i = 0; i < n; i++) {
			mountains[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		int mincost = Integer.MAX_VALUE;
		for(int i = 0; i <= 83; i++) {
			int highRange = i + 17;
			int curcost = 0;
			for(int j = 0; j < n; j++) {
				if(mountains[j] < i) {
					curcost += Math.pow(i - mountains[j], 2);
				}else if(mountains[j] > highRange) {
					curcost += Math.pow(mountains[j] - highRange, 2);
				}
			}
			if(curcost < mincost) {
				mincost = curcost;
			}
		}
		PrintWriter out = new PrintWriter("skidesign.out");
		out.println(mincost);
		out.close();
	}
}
