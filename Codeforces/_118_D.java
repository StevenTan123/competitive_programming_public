import java.util.*;
import java.io.*;

public class _118_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n1 = Integer.parseInt(line.nextToken());
		int n2 = Integer.parseInt(line.nextToken());
		int k1 = Integer.parseInt(line.nextToken());
		int k2 = Integer.parseInt(line.nextToken());
		//dp[i][j][k][l] = # arrangements of i soldiers j footmen ending with type k and l of that type.
		long[][][][] dp = new long[n1 + n2 + 1][n1 + 1][2][Math.max(k1, k2) + 1];
		dp[1][0][1][1] = 1;
		dp[1][1][0][1] = 1;
		for(int i = 2; i <= n1 + n2; i++) {
			for(int j = 0; j <= n1; j++) {
				if(j > i || j > n1) continue;
				if(i - j > n2) continue;
				for(int k = 0; k < 2; k++) {
					for(int l = 1; l <= Math.max(k1, k2); l++) {
						if(k == 0 && l > k1 || k == 1 && l > k2) continue;
						if(k == 0) {
							if(j == 0) continue;
							dp[i][j][k][l] += dp[i - 1][j - 1][0][l - 1];
							dp[i][j][k][l] %= 100000000;
							if(l == 1) {
								for(int a = 0; a <= Math.max(k1, k2); a++) {
									dp[i][j][k][l] += dp[i - 1][j - 1][1][a];
									dp[i][j][k][l] %= 100000000;
								}
							}
						}else {
							dp[i][j][k][l] += dp[i - 1][j][1][l - 1];
							dp[i][j][k][l] %= 100000000;
							if(l == 1) {
								for(int a = 0; a <= Math.max(k1, k2); a++) {
									dp[i][j][k][l] += dp[i - 1][j][0][a];
									dp[i][j][k][l] %= 100000000;
								}
							}
						}
					}
				}
			}
		}
		long res = 0;
		for(int i = 0; i <= n1; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 1; k <= Math.max(k1, k2); k++) {
					res += dp[n1 + n2][i][j][k];
					res %= 100000000;
				}
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}
