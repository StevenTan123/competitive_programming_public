import java.util.*;
import java.io.*;

public class _1770_D {
    static final long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken()) - 1;
            }
            int[] b = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(line.nextToken()) - 1;
            }
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }

            int equal = 0;
            for (int i = 0; i < n; i++) {
                graph[a[i]].add(b[i]);
                graph[b[i]].add(a[i]);
                if (a[i] == b[i]) {
                    equal++;
                }
            }

            int[] comps = new int[n];
            Arrays.fill(comps, -1);
            int comp = 0;
            for (int i = 0; i < n; i++) {
                if (comps[i] == -1) {
                    dfs(graph, comps, i, comp);
                    comp++;
                }
            }

            int[] size = new int[comp];
            int[] edges = new int[comp];
            for (int i = 0; i < n; i++) {
                size[comps[i]]++;
                edges[comps[a[i]]]++;
            }

            boolean possible = true;
            for (int i = 0; i < comp; i++) {
                if (size[i] != edges[i]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                long res = modmult(binpow(2, comp - equal), binpow(n, equal));
                out.println(res);
            } else {
                out.println(0);
            }
        }
        in.close();
        out.close();
    }
    static void dfs(ArrayList<Integer>[] graph, int[] comps, int cur, int comp) {
        if (comps[cur] != -1) {
            return;
        }
        comps[cur] = comp;
        for (int nei : graph[cur]) {
            dfs(graph, comps, nei, comp);
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
        if (b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if (b % 2 == 0) {
            return modmult(small, small);
        } else {
            return modmult(modmult(small, small), a);
        }
    }
}
