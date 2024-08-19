import java.util.*;
import java.io.*;

public class walk {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			int z = Integer.parseInt(line.nextToken());
			line = new StringTokenizer(in.readLine());
			int[] a = new int[n];
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			int[][] dp = new int[z + 1][n];
			int sum = 0;
		    int res = 0;
			for(int c = 0; c < n; c++) {
				if(c > k) {
					break;
				}
				sum += a[c];
				dp[0][c] = sum;
				res = Math.max(res, dp[0][c]);
			}
			for(int r = 1; r < z + 1; r++) {
				for(int c = 0; c < n; c++) {
					int moved = 2 * r + c;
					if(moved > k) continue;
					if(c > 0) {
						dp[r][c] = Math.max(dp[r][c], dp[r][c - 1] + a[c]);
					}
					if(c < n - 1) {
					    dp[r][c] = Math.max(dp[r][c], dp[r - 1][c + 1] + a[c]);
					}
					res = Math.max(res, dp[r][c]);
				}
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
