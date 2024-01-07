/*
ID: tanstev1
LANG: JAVA
PROB: numtri
 */
import java.io.*;
import java.util.*;

public class numtri {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] tri = new int[n][n];
		int[][] dp = new int[n][n];
		for(int i = 0; i < n; i++) {
			int numRow = i + 1;
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < numRow; j++) {
				tri[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		in.close();
		PrintWriter out = new PrintWriter("numtri.out");
		out.println(dfs(tri, dp, 0, 0, n));
		out.close();
	}
	static int dfs(int[][] tri, int[][] dp, int row, int col, int n) {
		if(dp[row][col] > 0) {
			return dp[row][col] - 1;
		}
		if(row == tri.length - 1) {
			//To avoid confusion 0 sum paths with and no dp yet
			dp[row][col] = tri[row][col] + 1;
			return tri[row][col];
		}
		int left = dfs(tri, dp, row + 1, col, n);
		int max = left;
		int right = dfs(tri, dp, row + 1, col + 1, n);
		if(right > max) {
			max = right;
		}
		max += tri[row][col];
		dp[row][col] = max + 1;
		return max;
	}
}
