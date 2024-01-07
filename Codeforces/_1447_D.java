import java.util.*;
import java.io.*;

public class _1447_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		String a = in.readLine();
		String b = in.readLine();
		int[][] dp = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				char ac = a.charAt(i);
				char bc = b.charAt(j);
				if(ac == bc) {
					if(i > 0 && j > 0) {
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 4 - 2);
					}else {
						dp[i][j] = Math.max(dp[i][j], 4 - 2);
					}
				}
				if(i > 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] - 1);
				if(j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] - 1);
			}
		}
		int max = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				max = Math.max(dp[i][j], max);
			}
		}
		out.println(max);
		in.close();
		out.close();
	}
}
