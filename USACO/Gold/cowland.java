import java.util.*;
import java.io.*;

public class cowland {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowland.in"));
        PrintWriter out = new PrintWriter("cowland.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        ArrayList<Integer>[] tree = new ArrayList[n];
        int[] e = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
            e[i] = Integer.parseInt(line.nextToken());
        }
        for(int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        ArrayList<pair> tour = new ArrayList<pair>();
        tour(tree, 0, new boolean[n], tour, e);
        int[][] occ = new int[n][2];
        for(int i = 0; i < n; i++) Arrays.fill(occ[i], -1);
        int[] bit = new int[2 * n + 1];
        for(int i = 0; i < 2 * n; i++) {
            update(bit, i, tour.get(i).a);
            if(occ[tour.get(i).b][0] == -1) occ[tour.get(i).b][0] = i;
            else occ[tour.get(i).b][1] = i;
        }
        ArrayList<Integer> ett2 = new ArrayList<Integer>();
        ArrayList<Integer> dep2 = new ArrayList<Integer>();
        euler_tour(tree, 0, 0, new boolean[n], ett2, dep2);
        int[] ett = new int[ett2.size()];
        int[] dep = new int[dep2.size()];
        int[] first = new int[n];
        Arrays.fill(first, -1);
        for(int i = 0; i < ett.length; i++) {
            ett[i] = ett2.get(i);
            if(first[ett[i]] == -1) first[ett[i]] = i;
            dep[i] = dep2.get(i);
        }
        Segtree st = new Segtree(dep);
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(line.nextToken());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken());
            if(type == 1) {
                update(bit, occ[a][0], e[a]);
                update(bit, occ[a][1], e[a]);
                e[a] = b;
                update(bit, occ[a][0], e[a]);
                update(bit, occ[a][1], e[a]);
            }else {
                b--;
                int lca = lca(a, b, first, dep, ett, st);
                int dist = dist(a, lca, bit, occ);
                int dist2 = dist(b, lca, bit, occ);
                out.println(dist ^ dist2 ^ e[lca]);
            }
        }
        in.close();
        out.close();
    }
    static int dist(int a, int b, int[] bit, int[][] occ) {
        int a_root = to_root(a, bit, occ);
        int b_root = to_root(b, bit, occ);
        return a_root ^ b_root;
    }
    static int to_root(int a, int[] bit, int[][] occ) {
        int pref = occ[a][1] > 0 ? xor(bit, occ[a][1] - 1) : 0;
        int root = xor(bit, occ[0][1]);
        return root ^ pref;
    }
    static int lca(int a, int b, int[] first, int[] dep, int[] ett, Segtree st) {
        int start = Math.min(first[a], first[b]);
        int end = Math.max(first[a], first[b]);
        return ett[st.min(start, end)];
    }
    static void tour(ArrayList<Integer>[] tree, int node, boolean[] visited, ArrayList<pair> tour, int[] e) {
        visited[node] = true;
        tour.add(new pair(e[node], node));
        for(int nei : tree[node]) {
            if(!visited[nei]) tour(tree, nei, visited, tour, e);
        }
        tour.add(new pair(e[node], node));
    }
    static void euler_tour(ArrayList<Integer>[] tree, int node, int depth, boolean[] visited, ArrayList<Integer> tour, ArrayList<Integer> depths) {
        visited[node] = true;
        tour.add(node);
        depths.add(depth);
        for(int nei : tree[node]) {
            if(!visited[nei]) {
                euler_tour(tree, nei, depth + 1, visited, tour, depths);
                tour.add(node);
                depths.add(depth);
            }
        }
    }
    static void update(int[] bit, int index, int xor) {
        index += 1;
        while(index < bit.length) {
            bit[index] = bit[index] ^ xor;
            index += index & -index;
        }
    }
    static int xor(int[] bit, int index) {
        index += 1;
        int res = 0;
        while(index > 0) {
            res = res ^ bit[index];
            index -= index & -index;
        }
        return res;
    }
    static class pair {
        int a, b;
        pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
    static class Segtree {
        static int[] a, t;

        Segtree(int[] aa) {
            a = aa;
            t = new int[a.length * 4];
            construct();
        }

        static void construct() {
            construct2(1, 0, a.length - 1);
        }

        static int min(int l, int r) {
            return min2(1, 0, a.length - 1, l, r);
        }

        static void update(int i, int val) {
            update2(1, i, val, 0, a.length - 1);
        }

        static void construct2(int v, int l, int r) {
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

        static int min2(int v, int l, int r, int l2, int r2) {
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

        static void update2(int v, int i, int val, int l, int r) {
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
