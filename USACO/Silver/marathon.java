import java.util.*;
import java.io.*;

public class marathon {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[][] checkpoints = new int[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			checkpoints[i][0] = Integer.parseInt(line.nextToken());
			checkpoints[i][1] = Integer.parseInt(line.nextToken());
		}
		in.close();
		PrintWriter out = new PrintWriter("marathon.out");
		int[][] dp = new int[n][k + 1];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < k + 1; j++) {
				dp[i][j] = -1;
			}
		}
		out.println(dfs(0, checkpoints, k, n, dp));
		out.close();
	}
	static int dfs(int cur, int[][] checkpoints, int kleft, int n, int[][] dp) {
		if(cur == n - 1) {
			return 0;
		}
		if(dp[cur][kleft] > -1) {
			return dp[cur][kleft];
		}
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= kleft + 1; i++) {
			if(cur + i >= n) {
				continue;
			}
			int dist = Math.abs(checkpoints[cur + i][0] - checkpoints[cur][0]) + Math.abs(checkpoints[cur + i][1] - checkpoints[cur][1]);
			int ret = dfs(cur + i, checkpoints, kleft - (i - 1), n, dp);
			min = Math.min(min, ret + dist);
		}
		dp[cur][kleft] = min;
		return min;
	}
}
