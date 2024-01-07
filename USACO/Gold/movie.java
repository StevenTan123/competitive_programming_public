import java.util.*;
import java.io.*;

public class movie {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("movie.in"));
        PrintWriter out = new PrintWriter("movie.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int[] pow2 = new int[n + 1];
        pow2[0] = 1;
        for(int i = 1; i <= n; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        int l = Integer.parseInt(line.nextToken());
        int[] d = new int[n];
        int[][] s = new int[n][];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            d[i] = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            s[i] = new int[c];
            for(int j = 0; j < c; j++) {
                s[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        int min = Integer.MAX_VALUE;
        int[] dp = new int[pow2[n]];
        for(int i = 0; i < pow2[n]; i++) {
            int bits = 0;
            int val = i;
            for(int j = 0; j < n; j++) {
                int bit = val % 2;
                if(bit == 0) {
                    int ind = bsearch(s[j], dp[i]);
                    if(ind != -1) {
                        dp[i + pow2[j]] = Math.max(dp[i + pow2[j]], Math.max(dp[i], s[j][ind] + d[j]));
                    }
                }else {
                    bits++;
                }
                val /= 2;
            }
            if(dp[i] >= l) {
                min = Math.min(min, bits);
            }
        }
        out.println(min == Integer.MAX_VALUE ? -1 : min);
        in.close();
        out.close();
    }
    static int bsearch(int[] a, int val) {
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(a[m] <= val) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return res;
    }
}
