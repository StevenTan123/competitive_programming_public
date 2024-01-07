import java.util.*;
import java.io.*;

public class redistricting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("redistricting.in"));
        PrintWriter out = new PrintWriter("redistricting.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        String s = in.readLine();
        int[] p = new int[n];
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'H') {
                p[i] = (i > 0 ? p[i - 1] : 0) - 1;
            }else {
                p[i] = (i > 0 ? p[i - 1] : 0) + 1;
            }
        }
        TreeSet<Pair> active = new TreeSet<Pair>();
        Pair[] dp = new Pair[n + 1];
        dp[0] = new Pair(0, 0, 0);
        active.add(dp[0]);
        for(int i = 1; i <= n; i++) {
            Pair min = active.first();
            int dif = p[i - 1] - min.b;
            Pair cur = null;
            if(dif < 0) {
                cur = new Pair(min.a, p[i - 1], i);
            }else {
                cur = new Pair(min.a + 1, p[i - 1], i);
            }
            dp[i] = cur;
            active.add(cur);
            if(i >= k) {
                active.remove(dp[i - k]);
            }
        }
        out.println(dp[n].a);
        in.close();
        out.close();
    }
    static class Pair implements Comparable<Pair> {
        int a, b, c;
        Pair(int aa, int bb, int cc) {
            a = aa;
            b = bb;
            c = cc;
        }
        @Override
        public int compareTo(Pair o) {
            if(a == o.a) {
                if(b == o.b) {
                    return c - o.c;
                }
                return o.b - b;
            }
            return a - o.a;
        }
    }
}
