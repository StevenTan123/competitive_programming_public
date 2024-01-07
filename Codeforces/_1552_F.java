import java.util.*;
import java.io.*;

public class _1552_F {
    static final long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] portals = new int[n][3];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            portals[i][0] = Integer.parseInt(line.nextToken());
            portals[i][1] = Integer.parseInt(line.nextToken());
            portals[i][2] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(portals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int[] back = new int[n];
        for(int i = 0; i < n; i++) {
            back[i] = bsearch(portals, portals[i][1]);
        }
        //dp[i][0, 1] = time to reach portal i for the first, last time, assuming every portal is initially active
        long[][] dp = new long[n][2];
        for(int i = 0; i < n; i++) {
            if(i > 0) {
                dp[i][0] = modadd(dp[i - 1][1], modadd(portals[i][0], -portals[i - 1][0]));
            }else {
                dp[i][0] = portals[i][0];
            }
            dp[i][1] = modadd(modadd(dp[i][0], modadd(portals[back[i]][0], -portals[i][1])), modadd(dp[i][0], -dp[back[i]][0]));
        }
        long time = 0;
        for(int i = 0; i < n; i++) {
            int dist = portals[i][0] - (i > 0 ? portals[i - 1][0] : 0);
            time = modadd(time, dist);
            if(portals[i][2] == 1) {
                time = modadd(time, modadd(dp[i][1], -dp[i][0]));
            }
        }
        out.println(modadd(time, 1));
        in.close();
        out.close();
    }
    static int bsearch(int[][] portals, int loc) {
        int l = 0;
        int r = portals.length - 1;
        int res = -1;
        while(l <= r) {
            int avg = (l + r) / 2;
            if(portals[avg][0] >= loc) {
                res = avg;
                r = avg - 1;
            }else {
                l = avg + 1;
            }
        }
        return res;
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
}
