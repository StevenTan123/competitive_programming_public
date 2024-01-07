import java.util.*;
import java.io.*;

public class mooomoo {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mooomoo.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		int[] v = new int[b];
		for(int i = 0; i < b; i++) {
			v[i] = Integer.parseInt(in.readLine());
		}
		int[] fields = new int[n];
		for(int i = 0; i < n; i++) {
			fields[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		//dp[i][j] = using cows of breeds 0-i, the min # of cows needed to achieve j noise
		int[][] dp = new int[b][100001];
		for(int i = 0; i < b; i++) Arrays.fill(dp[i], -1);
		dp[0][0] = 0;
		for(int i = 0; i < b; i++) {
			for(int j = 0; j < 100001; j++) {
				int min = Integer.MAX_VALUE;
				if(i > 0 && dp[i-1][j] != -1) min = dp[i-1][j];
				if(j-v[i] >= 0 && dp[i][j-v[i]] != -1 && dp[i][j-v[i]] + 1 < min) min = dp[i][j-v[i]] + 1;
				if(min == Integer.MAX_VALUE) continue;
				dp[i][j] = min;
			}
		}
		int res = 0;
		for(int i = 0; i < n; i++) {
			int achieveNoise = fields[i] - ((i - 1 < 0 || fields[i-1] == 0) ? 0 : fields[i-1] - 1);
			res += dp[b-1][achieveNoise];
		}
		PrintWriter out = new PrintWriter("mooomoo.out");
		out.println(res);
		out.close();
	}
}
