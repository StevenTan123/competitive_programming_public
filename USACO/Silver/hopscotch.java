import java.util.*;
import java.io.*;

public class hopscotch {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(line.nextToken());
		int c = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[][] map = new int[r][c];
		long[][] dp = new long[r][c];
		for(int i = 0; i < r; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(line.nextToken());
				dp[i][j] = -1;
			}
		}
		in.close();
		PrintWriter out = new PrintWriter("hopscotch.out");
		out.println(dfs(0, 0, map, dp) % 1000000007);
		out.close();
	}
	static long dfs(int r, int c, int[][] map, long[][] dp) {
		long sum = 0;
		if(r == map.length - 1 && c == map[0].length - 1) {
			return 1;
		}
		for(int i = r + 1; i < map.length; i++) {
			for(int j = c + 1; j < map[0].length; j++) {
				if(dp[i][j] > -1 && map[i][j] != map[r][c]) {
					sum += dp[i][j];
					sum = sum % 1000000007;
				}else if(map[i][j] != map[r][c]) {
					dp[i][j] = dfs(i, j, map, dp);
					sum += dp[i][j];
					sum = sum % 1000000007;
				}
			}
		}
		return sum;
	}
}
