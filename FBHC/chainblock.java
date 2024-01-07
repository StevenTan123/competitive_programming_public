import java.util.*;
import java.io.*;

public class chainblock {
    static int pointer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("chainblock_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            ArrayList<Integer>[] tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(line.nextToken()) - 1;
                int y = Integer.parseInt(line.nextToken()) - 1;
                tree[x].add(y);
                tree[y].add(x);
            }
            int[] f = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                f[i] = Integer.parseInt(line.nextToken());
            }
            int[] euler = new int[2 * n - 1];
            int[] depths = new int[2 * n - 1];
            eulerTour(tree, euler, depths);
            int[] occ = new int[n];
            Arrays.fill(occ, -1);
            for(int i = 0; i < 2 * n - 1; i++) {
                if(occ[euler[i]] == -1) occ[euler[i]] = i;
            }
            Segtree st = new Segtree(depths);
            HashMap<Integer, Integer> prev = new HashMap<Integer, Integer>();
            int[] marks = new int[n];
            for(int i = 0; i < n; i++) {
                if(prev.containsKey(f[i])) {
                    int a = prev.get(f[i]);
                    int b = i;
                    int lca = lca(a, b, euler, depths, occ, st);
                    marks[a]++;
                    marks[b]++;
                    marks[lca] -= 2;
                }
                prev.put(f[i], i);
            }
            int[] arr = new int[n];
            dfs(tree, marks, arr);
            int ans = 0;
            for(int i = 1; i < n; i++) {
                if(arr[i] == 0) {
                    ans++;
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + ans);
            pointer = 0;
        }
        in.close();
        out.close();
    }
    static void dfs(ArrayList<Integer>[] tree, int[] marks, int[] res) {
        int[] par = new int[tree.length];
        Arrays.fill(par, -1);
        ArrayDeque<DFS> stack = new ArrayDeque<DFS>();
        boolean[] visited = new boolean[tree.length];
        stack.push(new DFS(0, 0, 0, 0));
        while(stack.size() > 0) {
            DFS cur = stack.pop();
            if(cur.type == 0) {
                visited[cur.node] = true;
                stack.push(new DFS(cur.node, 0, 0, 1));
                for(int nei : tree[cur.node]) {
                    if(!visited[nei]) {
                        par[nei] = cur.node;
                        stack.push(new DFS(nei, 0, 0, 0));
                    }
                }
            }else {
                res[cur.node] += marks[cur.node];
                for(int nei : tree[cur.node]) {
                    if(nei != par[cur.node]) {
                        res[cur.node] += res[nei];
                    }
                }
            }
        }
    }
    static int lca(int a, int b, int[] euler, int[] depths, int[] occ, Segtree st) {
        int min = Math.min(occ[a], occ[b]);
        int max = Math.max(occ[a], occ[b]);
        return euler[st.min(min, max)];
    }
    static void eulerTour(ArrayList<Integer>[] tree, int[] euler, int[] depths) {
        ArrayDeque<DFS> stack = new ArrayDeque<DFS>();
        boolean[] visited = new boolean[tree.length];
        stack.push(new DFS(0, 0, 0, 0));
        while(stack.size() > 0) {
            DFS cur = stack.pop();
            visited[cur.node] = true;
            if(pointer == 0 || cur.node != euler[pointer - 1]) {
                euler[pointer] = cur.node;
                depths[pointer] = cur.depth;
                pointer++;
            }
            if(cur.ind >= tree[cur.node].size()) {
                continue;
            }
            stack.push(new DFS(cur.node, cur.depth, cur.ind + 1, 0));
            int nei = tree[cur.node].get(cur.ind);
            if(!visited[nei]) {
                stack.push(new DFS(nei, cur.depth + 1, 0, 0));
            }
        }
    }
    static class DFS {
        int node, depth, ind, type;
        DFS(int n, int d, int i, int t) {
            node = n;
            depth = d;
            ind = i;
            type = t;
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
            if (l == r) {
                t[v] = l;
            } else {
                int avg = (l + r) / 2;
                construct2(v * 2, l, avg);
                construct2(v * 2 + 1, avg + 1, r);
                if (a[t[v * 2]] <= a[t[v * 2 + 1]]) {
                    t[v] = t[v * 2];
                } else {
                    t[v] = t[v * 2 + 1];
                }
            }
        }
        int min2(int v, int l, int r, int l2, int r2) {
            int avg = (l + r) / 2;
            if (l == l2 && r == r2) {
                return t[v];
            } else if (l2 > avg) {
                return min2(v * 2 + 1, avg + 1, r, l2, r2);
            } else if (r2 <= avg) {
                return min2(v * 2, l, avg, l2, r2);
            } else {
                int left = min2(v * 2, l, avg, l2, avg);
                int right = min2(v * 2 + 1, avg + 1, r, avg + 1, r2);
                if (a[left] <= a[right]) {
                    return left;
                } else {
                    return right;
                }
            }
        }
        void update2(int v, int i, int val, int l, int r) {
            if (l == r) {
                a[i] = val;
                return;
            }
            int avg = (l + r) / 2;
            if (i <= avg) {
                update2(v * 2, i, val, l, avg);
            } else {
                update2(v * 2 + 1, i, val, avg + 1, r);
            }
            if (a[t[v * 2]] <= a[t[v * 2 + 1]]) {
                t[v] = t[v * 2];
            } else {
                t[v] = t[v * 2 + 1];
            }
        }
    }
}
