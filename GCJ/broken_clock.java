import java.util.*;
import java.io.*;

public class broken_clock {
    static final long MOD0 = (long) 12 * 3600 * 1000000000;
    static final long MOD1 = (long) 3600 * 1000000000;
    static final long MOD2 = (long) 60 * 1000000000;
    static final long MOD3 = 1000000000;
    static int[][] perms = new int[][] { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long[] vals = new long[] {Long.parseLong(line.nextToken()), Long.parseLong(line.nextToken()), Long.parseLong(line.nextToken())};
            long[] ans = null;
            for(int i = 0; i < 6; i++) {
                long h = vals[perms[i][0]];
                long m = vals[perms[i][1]];
                long s = vals[perms[i][2]];
                ArrayList<Long> candidates = solve(h, m);
                for(long candidate : candidates) {
                    long nh = (h + candidate) % MOD0;
                    if(nh < 0) nh += MOD0;
                    long nm = (m + candidate) % MOD0;
                    if(nm < 0) nm += MOD0;
                    long ns = (s + candidate) % MOD0;
                    if(ns < 0) ns += MOD0;
                    if(works(nh, nm, ns)) {
                        ans = new long[] {nh, nm, ns};
                        break;
                    }
                }
            }
            long ticks = ans[0];
            long hours = ticks / MOD1;
            ticks = ticks % MOD1;
            long mins = ticks / MOD2;
            ticks = ticks % MOD2;
            long seconds = ticks / MOD3;
            long nano = ticks % MOD3;
            String res = "Case #" + t + ": ";
            out.println(res + hours + " " + mins + " " + seconds + " " + nano);
        }
        in.close();
        out.close();
    }
    static ArrayList<Long> solve(long h, long m) {
        ArrayList<Long> candidates = new ArrayList<Long>();
        for(int i = -11; i <= 11; i++) {
            long val = MOD1 * i * 12 + m - 12 * h;
            if(val % 11 == 0) {
                candidates.add(val / 11);
            }
        }
        return candidates;
    }
    static boolean works(long h, long m, long s) {
        if(m % 12 != 0 || h % MOD1 != m / 12) {
            return false;
        }
        if(s % 720 != 0 || h % MOD2 != s / 720) {
            return false;
        }
        return true;
    }
}