import java.util.*;
import java.io.*;

public class _1453_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] a = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			long gapsum = 0;
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				if(j > 0) gapsum += Math.abs(a[j] - a[j - 1]);
			}
			long maxdecrease = 0;
			for(int j = 0; j < n; j++) {
				long curdecrease;
				if(j == 0) {
					curdecrease = Math.abs(a[1] - a[0]);
					maxdecrease = Math.max(maxdecrease, curdecrease);
				}else if(j == n - 1) {
					curdecrease = Math.abs(a[n - 1] - a[n - 2]);
					maxdecrease = Math.max(maxdecrease, curdecrease);
				}else {
					curdecrease = Math.abs(a[j + 1] - a[j]) + Math.abs(a[j] - a[j - 1]) - Math.abs(a[j + 1] - a[j - 1]);
					maxdecrease = Math.max(maxdecrease, curdecrease);
				}
			}
			out.println(gapsum - maxdecrease);
		}
		in.close();
		out.close();
	}
}
