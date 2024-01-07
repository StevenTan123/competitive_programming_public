import java.io.*;
import java.util.*;

public class kombat {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] bosses = new int[n];
			for(int j = 0; j < n; j++) {
				bosses[j] = Integer.parseInt(line.nextToken());
			}
			int[][] dp = new int[n][2];
			for(int j = 0; j < n; j++) {
				Arrays.fill(dp[j], Integer.MAX_VALUE);
			}
			dp[0][0] = bosses[0] == 1 ? 1 : 0;
			if(n > 1) dp[1][0] = (bosses[1] == 1 ? 1 : 0) + dp[0][0];
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < 2; k++) {
					if(k == 0 && (j == 0 || j == 1)) continue;
					if(k == 1 && j == 0) continue;
					dp[j][k] = Math.min(dp[j][k], dp[j - 1][(k + 1) % 2] + ((bosses[j] == 1 && k == 0) ? 1 : 0));
					if(j < n - 1) {
						dp[j + 1][k] = dp[j - 1][(k + 1) % 2] + ((bosses[j] == 1 && k == 0) ? 1 : 0) + ((bosses[j + 1] == 1 && k == 0) ? 1 : 0);	
					}
				}
			}
			out.println(Math.min(dp[n - 1][0], dp[n - 1][1]));
		}
		in.close();
		out.close();
	}
}
