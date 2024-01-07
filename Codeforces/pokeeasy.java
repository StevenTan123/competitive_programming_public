import java.util.*;
import java.io.*;

public class pokeeasy {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int[] a = new int[n];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			long[][] dp = new long[2][n];
			dp[0][0] = a[0];
			for(int j = 1; j < n; j++) {
				dp[1][j] = Math.max(dp[1][j - 1], dp[0][j - 1] - a[j]);
				dp[0][j] = Math.max(dp[0][j - 1], dp[1][j - 1] + a[j]);
			}
			out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
		}
		in.close();
		out.close();
	}
}
