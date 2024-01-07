import java.util.*;
import java.io.*;

public class maxflow {
    static int pointer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("maxflow.in"));
        PrintWriter out = new PrintWriter("maxflow.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken()) - 1;
            int y = Integer.parseInt(line.nextToken()) - 1;
            tree[x].add(y);
            tree[y].add(x);
        }
        int[] par = new int[n];
        Arrays.fill(par, -1);
        int[] euler = new int[2 * n - 1];
        int[] depths = new int[2 * n - 1];
        eulerTour(tree, 0, 0, new boolean[n], euler, depths, par);
        int[] occ = new int[n];
        Arrays.fill(occ, -1);
        for(int i = 0; i < 2 * n - 1; i++) {
            if(occ[euler[i]] == -1) occ[euler[i]] = i;
        }
        Segtree st = new Segtree(depths);
        int[] marks = new int[n];
        for(int i = 0; i < k; i++) {
            line = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(line.nextToken()) - 1;
            int t = Integer.parseInt(line.nextToken()) - 1;
            int lca = lca(s, t, euler, depths, occ, st);
            marks[s]++;
            marks[t]++;
            marks[lca]--;
            if(par[lca] > -1) {
                marks[par[lca]]--;
            }
        }
        int[] res = new int[n];
        dfs(tree, 0, new boolean[n], marks, res);
        int max = 0;
        for(int val : res) {
            max = Math.max(max, val);
        }
        out.println(max);
        in.close();
        out.close();
    }
    static void dfs(ArrayList<Integer>[] tree, int cur, boolean[] visited, int[] marks, int[] res) {
        visited[cur] = true;
        int sum = marks[cur];
        for(int nei : tree[cur]) {
            if(!visited[nei]) {
                dfs(tree, nei, visited, marks, res);
                sum += res[nei];
            }
        }
        res[cur] = sum;
    }
    static int lca(int a, int b, int[] euler, int[] depths, int[] occ, Segtree st) {
        int min = Math.min(occ[a], occ[b]);
        int max = Math.max(occ[a], occ[b]);
        return euler[st.min(min, max)];
    }
    static void eulerTour(ArrayList<Integer>[] tree, int cur, int depth, boolean[] visited, int[] euler, int[] depths, int[] par) {
        visited[cur] = true;
        euler[pointer] = cur;
        depths[pointer] = depth;
        pointer++;
        for(int nei : tree[cur]) {
            if(!visited[nei]) {
                par[nei] = cur;
                eulerTour(tree, nei, depth + 1, visited, euler, depths, par);
                euler[pointer] = cur;
                depths[pointer] = depth;
                pointer++;
            }
        }
    }
    static class Segtree {
        int[] a, t;
        Segtree(int[] aa) {
            a = aa;
            t = new int[a.length * 4];
            construct();
        }
        void construct() {
            construct2(1, 0, a.length - 1);
        }
        int min(int l, int r) {
            return min2(1, 0, a.length - 1, l, r);
        }
        void update(int i, int val) {
            update2(1, i, val, 0, a.length - 1);
        }
        void construct2(int v, int l, int r) {
            if(l == r) {
                t[v] = l;
            }else {
                int avg = (l + r) / 2;
                construct2(v * 2, l, avg);
                construct2(v * 2 + 1, avg + 1, r);
                if(a[t[v * 2]] <= a[t[v * 2 + 1]]) {
                    t[v] = t[v * 2];
                }else {
                    t[v] = t[v * 2 + 1];
                }
            }
        }
        int min2(int v, int l, int r, int l2, int r2) {
            int avg = (l + r) / 2;
            if(l == l2 && r == r2) {
                return t[v];
            }else if (l2 > avg) {
                return min2(v * 2 + 1, avg + 1, r, l2, r2);
            }else if (r2 <= avg) {
                return min2(v * 2, l, avg, l2, r2);
            }else {
                int left = min2(v * 2, l, avg, l2, avg);
                int right = min2(v * 2 + 1, avg + 1, r, avg + 1, r2);
                if(a[left] <= a[right]) {
                    return left;
                }else {
                    return right;
                }
            }
        }
        void update2(int v, int i, int val, int l, int r) {
            if(l == r) {
                a[i] = val;
                return;
            }
            int avg = (l + r) / 2;
            if(i <= avg) {
                update2(v * 2, i, val, l, avg);
            }else {
                update2(v * 2 + 1, i, val, avg + 1, r);
            }
            if(a[t[v * 2]] <= a[t[v * 2 + 1]]) {
                t[v] = t[v * 2];
            }else {
                t[v] = t[v * 2 + 1];
            }
        }
    }
}
