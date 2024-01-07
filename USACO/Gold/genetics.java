import java.util.*;
import java.io.*;

public class genetics {
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
		//dp[index][first char 2nd to last block][first char last block][last char last block]
		long[][][][] dp = new long[n][4][4][4];
		for(int j = 0; j < 4; j++) {
			for(int k = 0; k < 4; k++) {
				for(int l = 0; l < 4; l++) {
					if(l == k && (a[0] == 4 || a[0] == l)) dp[0][j][k][l] = 1;
				}
			}
		}
		for(int i = 0; i < n - 1; i++) {
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 4; k++) {
					for(int l = 0; l < 4; l++) {
						for(int x = 0; x < 4; x++) {
							if((a[i + 1] == 4 || a[i + 1] == x) && x != l) {
								dp[i + 1][j][k][x] += dp[i][j][k][l];
								dp[i + 1][j][k][x] %= mod;
							}
						}
						if(j == l) {
							for(int x = 0; x < 4; x++) {
								if(a[i + 1] == 4 || a[i + 1] == x) {
									dp[i + 1][k][x][x] += dp[i][j][k][l];
									dp[i + 1][k][x][x] %= mod;
								}
							}
						}
					}
				}
			}
		}
		long res = 0;
		for(int j = 0; j < 4; j++) {
			for(int k = 0; k < 4; k++) {
				for(int l = 0; l < 4; l++) {
					if(j == l) {
						res += dp[n - 1][j][k][l];
						res %= mod;
					}
				}
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}
