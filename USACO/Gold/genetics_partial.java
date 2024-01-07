import java.util.*;
import java.io.*;

public class genetics_partial {
	public static void main(String[] args) throws Exception {
		long mod = 1000000007;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String s = in.readLine();
		int n = s.length();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if(c == 'A') a[i] = 0;
			else if(c == 'G') a[i] = 1;
			else if(c == 'C') a[i] = 2;
			else if(c == 'T') a[i] = 3;
			else a[i] = 4;
		}
		long[][][][] fill = new long[n][n][4][4];
		for(int i = 0; i < n; i++) {
			for(int j = i; j < n; j++) {
				for(int k = 0; k < 4; k++) {
					if(a[i] != 4 && a[i] != k) continue;
					for(int l = 0; l < 4; l++) {
						if(a[j] != 4 && a[j] != l) continue;
						if(i != j) {
							for(int x = 0; x < 4; x++) {
								if(x == l) continue;
								fill[i][j][k][l] = (fill[i][j][k][l] + fill[i][j - 1][k][x]) % mod;
							}
						}else {
							if(k == l && (l == a[i] || a[i] == 4)) {
								fill[i][j][k][l] = 1;
							}
						}
					}
				}
			}
		}
		long[][] dp = new long[n][4];
		long res = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 4; k++) {
					dp[i][j] = (dp[i][j] + fill[0][i][j][k]) % mod;
				}
				for(int k = 0; k < i; k++) {
					for(int l = 0; l < 4; l++) {
						//looks at last segment between k + 1 and i
						dp[i][j] = (dp[i][j] + dp[k][l] * fill[k + 1][i][j][l] % mod) % mod;
					}
				}
				if(i == n - 1) res = (res + dp[i][j]) % mod;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}
