import java.util.*;
import java.io.*;

public class jeffrey_graham {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());

        double[][] dp = new double[N + 1][M + 1];
        double[] col_sums = new double[M + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= M; j++) {
            dp[1][j] = 1;
        }
        Arrays.fill(col_sums, 1);
        col_sums[0] = 0;
        for (int i = 2; i <= N; i++) {
            double cur_sum = i - 1;
            for (int j = 2; j <= M; j++) {
                if (j > 2) {
                    cur_sum += col_sums[j - 1];
                }
                dp[i][j] = cur_sum * 4 / (i * j) + 1;
            }
            for (int j = 2; j <= M; j++) {
                col_sums[j] += dp[i][j];
            }
        }
        out.println(dp[N][M]);

		in.close();
		out.close();
	}
}
