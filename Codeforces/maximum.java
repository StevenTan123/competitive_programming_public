import java.util.*;
import java.io.*;

public class maximum {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] a = new int[n];
			int negcount = 0;
			int poscount = 0;
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				if(a[j] > 0) {
					poscount++;
				}else if(a[j] < 0) {
					negcount++;
				}
			}
			Arrays.sort(a);
			long res = -1;
			if(poscount > 0 && poscount + negcount >= 5) {
				long max = Long.MIN_VALUE;
				for(int j = 0; j <= 5; j++) {
					max = Math.max(max, mult(j, 5 - j, a));
				}
				res = max;
			}else {
				if(poscount + negcount < 5) {
					res = 0;
				}else {
					if(poscount + negcount == n) {
						long mult = 1;
						for(int j = n - 5; j < n; j++) {
							mult *= a[j];
						}
						res = mult;
					}else {
						res = 0;
					}
				}
			}
			if(poscount + negcount != n && res < 0) {
			    res = 0;
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
	static long mult(int negnum, int posnum, int[] a) {
		long res = 1;
		for(int i = 0; i < negnum; i++) {
			res *= a[i];
		}
		for(int i = a.length - 1; i >= a.length - posnum; i--) {
			res *= a[i];
		}
		return res;
	}
}
