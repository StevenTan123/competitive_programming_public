import java.util.*;
import java.io.*;

public class time {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("time.in"));
		PrintWriter out = new PrintWriter("time.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int c = Integer.parseInt(line.nextToken());
		int[] moneys = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) moneys[i] = Integer.parseInt(line.nextToken());
		ArrayList[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			graph[Integer.parseInt(line.nextToken()) - 1].add(Integer.parseInt(line.nextToken()) - 1);
		}
		long[][] dp = new long[1005][n];
		for(int i = 0; i < 1005; i++) Arrays.fill(dp[i], -1);
		dp[0][0] = 0;
		for(int i = 0; i < 1004; i++) {
			for(int j = 0; j < n; j++) {
				if(dp[i][j] != -1) {
					ArrayList<Integer> nei = graph[j];
					for(int k = 0; k < nei.size(); k++) {
						dp[i + 1][nei.get(k)] = Math.max(dp[i + 1][nei.get(k)], dp[i][j] + moneys[nei.get(k)]);
					}
				}
			}
		}
		long res = 0;
		for(int i = 0; i < 1005; i++) {
			res = Math.max(res, dp[i][0] - (c * i * i));
		}
		out.println(res);
		in.close();
		out.close();
	}
}
