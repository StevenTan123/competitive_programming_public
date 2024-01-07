/*
ID: tanstev1
LANG: JAVA
TASK: subset
 */
import java.util.*;
import java.io.*;

public class subset {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		int n = Integer.parseInt(in.readLine());
		in.close();
		int[] nums = new int[n];
		int halfsum = 0;
		for(int i = 0; i < n; i++) {
			nums[i] = i + 1;
			halfsum += i + 1;
		}
		//dp[i][j] means using subset 0 thru i how many ways to form sum of j
		long[][] dp = new long[n][halfsum + 1];
		if(halfsum % 2 == 0) {
			halfsum /= 2;
			for(int i = 0; i < n; i++) {
				dp[i][0] = 1;
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < halfsum + 1; j++) {
					if(i == 0) {
						if(i + 1 == j) {
							dp[i][j] = 1;
						}
						continue;
					}
					if(i + 1 > j) {
						dp[i][j] = dp[i - 1][j];
					}else {
						dp[i][j] = dp[i - 1][j - (i + 1)] + dp[i - 1][j];
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("subset.out");
		out.println(dp[n - 1][halfsum] / 2);
		out.close();
	}
}
