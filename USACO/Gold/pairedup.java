import java.util.*;
import java.io.*;

public class pairedup {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(line.nextToken());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] x = new int[n];
        int[] y = new int[n];
        int prev = 0;
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(line.nextToken());
            y[i] = Integer.parseInt(line.nextToken());
            if(i > 0 && x[i] - x[i - 1] > k) {
                sizes.add(i - prev);
                prev = i;
            }
        }
        sizes.add(n - prev);
        int res = 0;
        int p = 0;
        for(int block : sizes) {
            int[] curx = new int[block];
            int[] cury = new int[block];
            for(int i = p; i < p + block; i++) {
                curx[i - p] = x[i];
                cury[i - p] = y[i];
            }
            p += block;
            res += best(curx, cury, block, t, k);
        }
        out.println(res);
        in.close();
        out.close();
    }
    static int best(int[] x, int[] y, int n, int t, int k) {
        if(t == 1 && n % 2 == 0) {
            return 0;
        }

        int[][] dp = new int[n][2];
        int val = t == 1 ? Integer.MAX_VALUE : -1;

        //pre[i][j][k] stores min/max of dp[i][j] for all a with parity k from 0 to i
        int[][][] pre = new int[n][2][2];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2; j++) {
                dp[i][j] = val;
                Arrays.fill(pre[i][j], val);
            }
        }
        int prev = 0;
        int left = 0;
        for(int i = 0; i < n; i++) {
            prev = left;
            while(x[i] - x[left] > k) {
                left++;
            }
            int parity = i % 2;

            if(parity == 0) {
                dp[i][0] = y[i];
            }
            if(left > 0) {
                if(valid(pre[left - 1][0][1 - parity])) {
                    dp[i][0] = minmax(dp[i][0], pre[left - 1][0][1 - parity] + y[i], t);
                }
            }
            if(valid(pre[left][1][1 - parity])) {
                dp[i][0] = minmax(dp[i][0], pre[left][1][1 - parity] + y[i], t);
            }

            if(i > 1 && x[i] - x[i - 2] <= k) {
                if(parity == 0) {
                    dp[i][1] = y[i - 1];
                }
                if(prev > 0) {
                    if(valid(pre[prev - 1][0][1 - parity])) {
                        dp[i][1] = minmax(dp[i][1], pre[prev - 1][0][1 - parity] + y[i - 1], t);
                    }
                }
                if(valid(pre[prev][1][1 - parity])) {
                    dp[i][1] = minmax(dp[i][1], pre[prev][1][1 - parity] + y[i - 1], t);
                }
            }

            pre[i][0][parity] = minmax(i == 0 ? val : pre[i - 1][0][parity], dp[i][0], t);
            pre[i][0][1 - parity] = i == 0 ? val : pre[i - 1][0][1 - parity];

            pre[i][1][parity] = minmax(i == 0 ? val : pre[i - 1][1][parity], dp[i][1], t);
            pre[i][1][1 - parity] = i == 0 ? val : pre[i - 1][1][1 - parity];
        }

        int res = val;
        for(int i = n - 1; i >= 0; i -= 2) {
            res = minmax(res, dp[i][0], t);
            res = minmax(res, dp[i][1], t);
        }
        return Math.max(res, 0);
    }
    static int minmax(int a, int b, int t) {
        if(t == 1) return Math.min(a, b);
        else return Math.max(a, b);
    }
    static boolean valid(int val) {
        return val != -1 && val != Integer.MAX_VALUE;
    }
}
