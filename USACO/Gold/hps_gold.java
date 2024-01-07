import java.util.*;
import java.io.*;

public class hps_gold {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[] john = new int[n];
		for(int i = 0; i < n; i++) {
			String s = in.readLine();
			if(s.equals("H")) john[i] = 0;
			if(s.equals("P")) john[i] = 1;
			if(s.equals("S")) john[i] = 2;
		}
		in.close();
		//dp[a][b][c] is the max amount of wins possible after a rounds, have switched b hands, and ending with hand c (hoof, paper, or scissors)
		int[][][] dp = new int[n+1][k+1][3];
		dp[0][0][0] = 0;
		dp[0][0][1] = 0;
		dp[0][0][2] = 0;
		for(int a = 1; a <= n; a++) {
			for(int b = 0; b <= k; b++) {
				for(int c = 0; c < 3; c++) {
					int max = 0;
					if(dp[a-1][b][c] > max) max = dp[a-1][b][c];
					if(b > 0 && dp[a-1][b-1][(c+1)%3] > max) max = dp[a-1][b-1][(c+1)%3];
					if(b > 0 && dp[a-1][b-1][(c+2)%3] > max) max = dp[a-1][b-1][(c+2)%3];
					boolean wincur = false;
					if((c == 1 && john[a-1] == 0) || (c == 2 && john[a-1] == 1) || (c == 0 && john[a-1] == 2)) wincur = true;
					dp[a][b][c] = max + (wincur ? 1 : 0);
				}
			}
		}
		int res = 0;
		for(int i = 0; i <= k; i++) {
			for(int j = 0; j < 3; j++) {
				res = Math.max(res, dp[n][i][j]);
			}
		}
		PrintWriter out = new PrintWriter("hps.out");
		out.println(res);
		out.close();
	}
}
