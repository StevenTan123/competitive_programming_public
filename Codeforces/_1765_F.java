import java.util.*;
import java.io.*;

public class _1765_F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        Contract[] contracts = new Contract[n];
        for (int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            contracts[i] = new Contract(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
        }
        Arrays.sort(contracts);

        // dp[i] = max profit signing some out of the i lowest concentration contracts,
        // including signing the i-th contract.
        double[] dp = new double[n];
        double res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = -contracts[i].w;
            for (int j = 0; j < i; j++) {
                if (contracts[j].x < contracts[i].x) {
                    double revenue = (double) k * (contracts[i].x - contracts[j].x) / 100 * (contracts[i].c + contracts[j].c) / 2;
                    dp[i] = Math.max(dp[i], dp[j] - contracts[i].w + revenue);
                }
            }
            res = Math.max(res, dp[i]);
        }
        out.println(res);

        in.close();
        out.close();
    }

    static class Contract implements Comparable<Contract> {
        int x, w, c;
        Contract(int xx, int ww, int cc) {
            x = xx;
            w = ww;
            c = cc;
        }

        @Override
        public int compareTo(Contract o) {
            if (x == o.x) {
                return c - o.c;
            }
            return x - o.x;
        }
    }
}
