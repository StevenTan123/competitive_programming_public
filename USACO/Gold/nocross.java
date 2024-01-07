import java.util.*;
import java.io.*;

public class nocross {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
		int n = Integer.parseInt(in.readLine());
		int[] top = new int[n];
		int[] bottom = new int[n];
		for(int i = 0; i < 2 * n; i++) {
			if(i < n) {
				top[i] = Integer.parseInt(in.readLine());
			}else {
				bottom[i - n] = Integer.parseInt(in.readLine());
			}
		}
		in.close();
		int[][] dp = new int[n][n];
		
		//Base case filling out first row
		for(int i = 0; i < n; i++) {
			int val = 0;
			if(Math.abs(top[0] - bottom[i]) <= 4) {
				val = 1;
			}
			if(i == 0) {
				dp[0][i] = val;
			}else {
				dp[0][i] = Math.max(dp[0][i-1], val);
			}
		}
		
		//Filling dp array
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int val = 0;
				if(Math.abs(top[i] - bottom[j]) <= 4) {
					if(j == 0) {
						val = 1;
					}else {
						val = dp[i - 1][j - 1] + 1;
					}
				}else {
					if(j == 0) {
						val = dp[i - 1][j];
					}else {
						val = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
				dp[i][j] = val;
			}
		}
		PrintWriter out = new PrintWriter("nocross.out");
		out.println(dp[n - 1][n - 1]);
		out.close();
	}
}
