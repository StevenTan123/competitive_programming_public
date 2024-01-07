import java.util.*;
import java.io.*;

public class _1875_F {
    static final int MAXN = 5005;
    public static void main(String[] args) throws IOException {
        // dp[i][j] = probability of succeeding on travelling to j-th best edge
        // of i total edges, given you try edges in order
        double[][] dp = new double[MAXN][MAXN];
        for (int i = 1; i < MAXN; i++) {
            dp[i][1] = (double) 1 / i;
            for (int j = 2; j <= i; j++) {
                if (i > 2) {
                    dp[i][j] += (double) (i - j) / i * dp[i - 2][j - 1];
                    if (j > 2) {
                        dp[i][j] += (double) (j - 2) / i * dp[i - 2][j - 2];
                    }
                }
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            ArrayList<Integer>[] DAG = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                DAG[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(line.nextToken()) - 1;
                int b = Integer.parseInt(line.nextToken()) - 1;
                DAG[a].add(b);
            }

            double[] DAG_dp = new double[n];
            DAG_dp[n - 1] = 1;
            for (int i = n - 2; i >= 0; i--) {
                double[] sorted = new double[DAG[i].size()];
                for (int j = 0; j < DAG[i].size(); j++) {
                    sorted[j] = DAG_dp[DAG[i].get(j)];
                }
                Arrays.sort(sorted);
                for (int j = sorted.length - 1; j >= 0; j--) {
                    DAG_dp[i] += sorted[j] * dp[DAG[i].size()][sorted.length - j];
                }
            }
            out.println(DAG_dp[0]);
        }
        
        in.close();
        out.close();
    }
}
