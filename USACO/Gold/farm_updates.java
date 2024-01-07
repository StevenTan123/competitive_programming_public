import java.util.*;
import java.io.*;

public class farm_updates {
    static int[] par, active, size, res;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[][] queries = new int[q][];
        active = new int[n];
        Arrays.fill(active, 1);
        ArrayList<Integer> edges = new ArrayList<Integer>();
        ArrayList<Integer> all_edges = new ArrayList<Integer>();
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            char type = line.nextToken().charAt(0);
            if(type == 'D') {
                queries[i] = new int[] {0, Integer.parseInt(line.nextToken()) - 1};
                active[queries[i][1]] = 0;
            }else if(type == 'A') {
                queries[i] = new int[] {1, Integer.parseInt(line.nextToken()) - 1, Integer.parseInt(line.nextToken()) - 1};
                edges.add(i);
                all_edges.add(i);
            }else {
                queries[i] = new int[] {2, Integer.parseInt(line.nextToken()) - 1};
                edges.set(queries[i][1], -1);
            }
        }
        res = new int[n];
        par = new int[n];
        size = new int[n];
        for(int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
            res[i] = -1;
            if(active[i] == 1) {
                res[i] = q;
            }
        }
        for(int edge : edges) {
            if(edge != -1) {
                union(queries[edge][1], queries[edge][2], q);
            }
        }
        for(int i = q - 1; i >= 0; i--) {
            int[] cur = queries[i];
            if(cur[0] == 0) {
                int head = find(cur[1]);
                if(active[head] == 0) {
                    active[head] = 1;
                    res[head] = Math.max(res[head], i);
                }
            }else if(cur[0] == 2) {
                int edge = all_edges.get(cur[1]);
                union(queries[edge][1], queries[edge][2], i);
            }
        }
        for(int i = 0; i < n; i++) {
            out.println(find_ans(i));
        }
        in.close();
        out.close();
    }
    static int find_ans(int node) {
        if(res[node] != -1 || par[node] == node) {
            return res[node];
        }
        return find_ans(par[node]);
    }
    static int find(int node) {
        if(par[node] == node) {
            return node;
        }
        return find(par[node]);
    }
    static void union(int a, int b, int i) {
        int roota = find(a);
        int rootb = find(b);
        if(roota == rootb) {
            return;
        }
        int big = roota;
        int small = rootb;
        if(size[roota] < size[rootb]) {
            big = rootb;
            small = roota;
        }
        par[small] = big;
        size[big] += size[small];
        if(active[big] == 1 && active[small] == 0) {
            res[small] = Math.max(res[small], i);
        }else if(active[small] == 1 && active[big] == 0) {
            res[big] = Math.max(res[big], i);
            active[big] = 1;
        }
    }
}
