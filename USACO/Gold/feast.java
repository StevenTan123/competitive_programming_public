import java.io.*;
import java.util.*;

public class feast {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("feast.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(line.nextToken());
		int a = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		in.close();
		int[][] dp = new int[2][t + 1];
		dp[0][0] = 1;
		int max = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < t + 1; j++) {
				if(dp[i][j] != 1) {
					continue;
				}
				if(j + a < t + 1) {
					dp[i][j + a] = 1;
				}
				if(j + b < t + 1) {
					dp[i][j + b] = 1;
				}
				if(i == 0) {
					dp[1][j / 2] = 1;
				}
				max = Math.max(j, max);
			}
		}
		PrintWriter out = new PrintWriter("feast.out");
		out.println(max);
		out.close();
	}
}
