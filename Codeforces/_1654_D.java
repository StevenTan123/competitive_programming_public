import java.util.*;
import java.io.*;

public class _1654_D {
    static final long MOD = 998244353;
    static ArrayList<Edge>[] tree;
    static int[] num, den, lcm, spf;
    static long res;
    static int n;
    public static void main(String[] args) throws IOException {
        spf = new int[200005];
        for(int i = 2; i < 200005; i++) {
            if(spf[i] == 0) {
                for(int j = i; j < 200005; j += i) {
                    if(spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            n = Integer.parseInt(in.readLine());
            tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Edge>();
            }
            for(int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(line.nextToken()) - 1;
                int v2 = Integer.parseInt(line.nextToken()) - 1;
                int x = Integer.parseInt(line.nextToken());
                int y = Integer.parseInt(line.nextToken());
                tree[v1].add(new Edge(v2, x, y));
                tree[v2].add(new Edge(v1, y, x));
            }
            num = new int[n + 1];
            den = new int[n + 1];
            lcm = new int[n + 1];
            dfs(0, -1);
            res = 0;
            long prod = 1;
            for(int i = 0; i <= n; i++) {
                for(int j = 0; j < lcm[i]; j++) {
                    prod = modmult(prod, i);
                }
            }
            dfs2(0, -1, 1, prod);
            out.println(res);
        }
        in.close();
        out.close();
    }
    static void dfs(int node, int prev) {
        for(Edge e : tree[node]) {
            if(e.to == prev) {
                continue;
            }
            ArrayList<Pair> num_c = new ArrayList<Pair>();
            ArrayList<Pair> den_c = new ArrayList<Pair>();
            int num_val = e.y;
            while(num_val > 1) {
                if(den[spf[num_val]] > 0) {
                    den[spf[num_val]]--;
                    den_c.add(new Pair(spf[num_val], -1));
                }else {
                    num[spf[num_val]]++;
                    num_c.add(new Pair(spf[num_val], 1));
                }
                num_val /= spf[num_val];
            }
            int den_val = e.x;
            while(den_val > 1) {
                if(num[spf[den_val]] > 0) {
                    num[spf[den_val]]--;
                    num_c.add(new Pair(spf[den_val], -1));
                }else {
                    den[spf[den_val]]++;
                    lcm[spf[den_val]] = Math.max(lcm[spf[den_val]], den[spf[den_val]]);
                    den_c.add(new Pair(spf[den_val], 1));
                }
                den_val /= spf[den_val];
            }
            dfs(e.to, node);
            for(Pair p : num_c) {
                num[p.a] -= p.b;
            }
            for(Pair p : den_c) {
                den[p.a] -= p.b;
            }
        }
    }
    static class Pair {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
    static void dfs2(int node, int prev, long cur, long mult) {
        res = modadd(res, modmult(cur, mult));
        for(Edge e : tree[node]) {
            if(e.to == prev) {
                continue;
            }
            long new_val = modmult(cur, modmult(e.y, modinv(e.x)));
            dfs2(e.to, node, new_val, mult);
        }
    }
    static class Edge {
        int to, x, y;
        Edge(int tt, int xx, int yy) {
            to = tt;
            x = xx;
            y = yy;
        }
    }
    static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
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
