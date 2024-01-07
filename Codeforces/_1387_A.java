import java.util.*;
import java.io.*;

public class _1387_A {
    static ArrayList<Integer> constants;
    static final double epsilon = 1e-9;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < M; i++) {
            line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            int c = Integer.parseInt(line.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        Pair[] exps = new Pair[N];
        Double[] vals = new Double[N];
        Arrays.fill(vals, null);
        boolean possible = true;
        for (int i = 0; i < N; i++) {
            if (vals[i] == null) {
                constants = new ArrayList<Integer>();
                possible = possible && dfs(graph, exps, vals, i, new Pair(1, 0));
                if (!possible) {
                    break;
                }
            }
            if (vals[i] == null) {
                Collections.sort(constants);
                int median = constants.get(constants.size() / 2);
                dfs_fill_vals(graph, vals, i, median);
            }
        }
        if (possible) {
            out.println("YES");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(vals[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        } else {
            out.println("NO");
        }

        in.close();
        out.close();
    }
    static boolean dfs(ArrayList<Edge>[] graph, Pair[] exps, Double[] vals, int cur, Pair exp) {
        if (exps[cur] == null) {
            exps[cur] = exp;
        } else if (exps[cur] != null) {
            if (exp.a == exps[cur].a) {
                if (exp.b == exps[cur].b) {
                    return true;
                } else {
                    return false;
                }
            } else {
                int coeff = exp.a - exps[cur].a;
                int constant = exps[cur].b - exp.b;
                double val = (double) constant / coeff;
                return dfs_fill_vals(graph, vals, cur, exp.a * val + exp.b);
            }
        }
        if (exp.a < 0) {
            constants.add(exp.b);
        } else {
            constants.add(-exp.b);
        }
        for (Edge e : graph[cur]) {
            Pair next_exp = new Pair(-exp.a, 1 - exp.b);
            if (e.type == 2) {
                next_exp.b = 2 - exp.b;
            }
            if (!dfs(graph, exps, vals, e.to, next_exp)) {
                return false;
            }
            if (vals[e.to] != null) {
                return true;
            }
        }
        return true;
    }
    static boolean dfs_fill_vals(ArrayList<Edge>[] graph, Double[] vals, int cur, double cur_val) {
        if (vals[cur] == null) {
            vals[cur] = cur_val;
        } else {
            if (Math.abs(vals[cur] - cur_val) > epsilon) {
                return false;
            } else {
                return true;
            }
        }
        for (Edge e : graph[cur]) {
            double next_val = 1 - cur_val;
            if (e.type == 2) {
                next_val = 2 - cur_val;
            }
            if (!dfs_fill_vals(graph, vals, e.to, next_val)) {
                return false;
            }
        }
        return true;
    }
    static class Edge {
        int to, type;
        Edge(int t, int tt) {
            to = t;
            type = tt;
        }
    }
    static class Pair {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
}
