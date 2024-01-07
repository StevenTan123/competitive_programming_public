import java.util.*;
import java.io.*;

public class elimination {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("elimination_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            double p = Double.parseDouble(line.nextToken());
            double[][] dp = new double[n][n];
            dp[0][0] = 0;
            for(int i = 1; i < n; i++) {
                double prob = 0;
                for(int j = 0; j <= i; j++) {
                    double cur = (double)2 / (i + 1) * ((double)j / i * (1 - p) + (double)(i - j) / i * p);
                    double left = 1 - prob - cur;
                    dp[i][j] = prob * (j == 0 ? 0 : dp[i - 1][j - 1] + 1) + cur + left * (dp[i - 1][j] + 1);
                    prob += cur;
                }
            }
            out.println("Case #" + t + ": ");
            for(int i = 0; i < n; i++) {
                out.println(dp[n - 1][i]);
            }
        }
        in.close();
        out.close();
    }
}
