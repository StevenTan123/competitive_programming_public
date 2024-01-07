import java.util.*;
import java.io.*;

public class mooriokart {
    static final long MOD = 1000000007;
    static int n, m, x, y;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new FileReader("mooriokart.in"));
        PrintWriter out = new PrintWriter("mooriokart.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        m = Integer.parseInt(line.nextToken());
        x = Integer.parseInt(line.nextToken());
        y = Integer.parseInt(line.nextToken());
        graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            int w = Integer.parseInt(line.nextToken());
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }
        int[] comps = new int[n];
        Arrays.fill(comps, -1);
        int k = 0;
        for(int i = 0; i < n; i++) {
            if(comps[i] == -1) {
                comps(comps, i, k);
                k++;
            }
        }
        y -= x * k;
        y = Math.max(y, 0);
        HashMap<Integer, Pair>[] count = new HashMap[k];
        for(int i = 0; i < n; i++) {
            if(count[comps[i]] == null) {
                count[comps[i]] = new HashMap<Integer, Pair>();
            }
            count_paths(count[comps[i]], i, -1, 0);
        }
        for(int i = 0; i < k; i++) {
            HashMap<Integer, Pair> freq = count[i];
            for(int weight : freq.keySet()) {
                Pair p = freq.get(weight);
                p.c /= 2;
                p.s /= 2;
            }
        }
        //dp[i][0] stores count of paths that sum to i, dp[i][1] stores the sum of all those path lengths
        long[][] dp = new long[y + 1][2];
        for(int weight : count[0].keySet()) {
            Pair p = count[0].get(weight);
            dp[weight][0] = p.c;
            dp[weight][1] = p.s;
        }
        for(int i = 1; i < k; i++) {
            long[][] new_dp = new long[y + 1][2];
            HashMap<Integer, Pair> freq = count[i];
            for(int weight : freq.keySet()) {
                Pair p = freq.get(weight);
                for(int j = 0; j <= y; j++) {
                    int new_weight = Math.min(y, j + weight);
                    new_dp[new_weight][0] = modadd(new_dp[new_weight][0], modmult(dp[j][0], p.c));
                    new_dp[new_weight][1] = modadd(new_dp[new_weight][1], modadd(modmult(dp[j][1], p.c), modmult(dp[j][0], p.s)));
                }
            }
            dp = new_dp;
        }
        long mult = 1;
        for(int i = 2; i <= k - 1; i++) {
            mult = modmult(mult, i);
        }
        mult = modmult(mult, binpow(2, k - 1));
        long res = modadd(modmult(modmult(dp[y][0], mult), x * k), modmult(dp[y][1], mult));
        out.println(res);
        in.close();
        out.close();
    }
    static void count_paths(HashMap<Integer, Pair> freq, int node, int prev, int len) {
        if(prev != -1) {
            int len2 = Math.min(len, y);
            Pair count = freq.get(len2);
            if(count == null) {
                count = new Pair(0, 0);
                freq.put(len2, count);
            }
            count.c++;
            count.s += len;
        }
        for(Edge e : graph[node]) {
            if(e.n != prev) {
                count_paths(freq, e.n, node, len + e.w);
            }
        }
    }
    static void comps(int[] comps, int node, int num) {
        if(comps[node] != -1) {
            return;
        }
        comps[node] = num;
        for(Edge e : graph[node]) {
            comps(comps, e.n, num);
        }
    }
    static class Edge {
        int n, w;
        Edge(int nn, int ww) {
            n = nn;
            w = ww;
        }
    }
    static class Pair {
        int c;
        long s;
        Pair(int count, long sum) {
            c = count;
            s = sum;
        }
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if(b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if(b % 2 == 0) {
            return modmult(small, small);
        }else {
            return modmult(modmult(small, small), a);
        }
    }
}
