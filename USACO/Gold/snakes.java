import java.util.*;
import java.io.*;

public class snakes {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("snakes.in"));
		PrintWriter out = new PrintWriter("snakes.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[] a = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
		}
		long[][] wastes = new long[n][n];
		for(int i = 0; i < n; i++) {
			int max = 0;
			long psum = 0;
			for(int j = i; j < n; j++) {
				max = Math.max(max, a[j]);
				psum += a[j];
				wastes[i][j] = max * (long)(j - i + 1) - psum;
			}
		}
		//min waste after picking up n groups, using j skips, previous start k
		long[][][] dp = new long[n][k + 1][n];
		long[][] dpmin = new long[n][k + 1];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dpmin[i], Long.MAX_VALUE);
			for(int j = 0; j < k + 1; j++) {
				Arrays.fill(dp[i][j], Long.MAX_VALUE);
			}
		}
		long res = Long.MAX_VALUE;
		dp[0][0][0] = 0;
		dpmin[0][0] = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < k + 1; j++) {
				for(int x = 0; x < n; x++) {
					if(x == i && i > 0 && j > 0 && dpmin[i - 1][j - 1] != Long.MAX_VALUE) {
						dp[i][j][x] = Math.min(dp[i][j][k], dpmin[i - 1][j - 1]);
					}
					if(i > 0) {
						if(x > 0 && j > 0) {
							if(dpmin[x - 1][j - 1] != Long.MAX_VALUE) {
								long waste = dpmin[x - 1][j - 1] + wastes[x][i];
								dp[i][j][x] = Math.min(dp[i][j][x], waste);
							}
						}else if(x == 0 && j == 0) {
							long waste = wastes[x][i];
							dp[i][j][x] = Math.min(dp[i][j][x], waste);
						}
					}
					dpmin[i][j] = Math.min(dp[i][j][x], dpmin[i][j]);
					if(i == n - 1 && dp[i][j][x] != -1) res = Math.min(res, dp[i][j][x]);
				}
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}