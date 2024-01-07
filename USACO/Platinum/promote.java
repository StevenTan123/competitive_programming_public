import java.util.*;
import java.io.*;

public class promote {
    static int pointer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("promote.in"));
        PrintWriter out = new PrintWriter("promote.out");
        int n = Integer.parseInt(in.readLine());
        int[] p = new int[n];
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(in.readLine());
            tree[i] = new ArrayList<Integer>();
        }
        for(int i = 1; i < n; i++) {
            tree[Integer.parseInt(in.readLine()) - 1].add(i);
        }
        int[] tour = new int[n * 2];
        int[][] occ = new int[n][2];
        dfs(tree, p, tour, occ, 0);
        MergeSortTree mst = new MergeSortTree(tour);
        for(int i = 0; i < n; i++) {
            int res = 0;
            if(occ[i][1] - occ[i][0] > 2) {
                res = mst.greater(1, 0, tour.length - 1, occ[i][0] + 1, occ[i][1] - 1, p[i]) / 2;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static void dfs(ArrayList<Integer>[] tree, int[] p, int[] tour, int[][] occ, int node) {
        tour[pointer] = p[node];
        occ[node][0] = pointer;
        pointer++;
        for(int child : tree[node]) {
            dfs(tree, p, tour, occ, child);
        }
        tour[pointer] = p[node];
        occ[node][1] = pointer;
        pointer++;
    }
    static class MergeSortTree {
        int[] a;
        int[][] t;
        MergeSortTree(int[] aa) {
            a = aa;
            t = new int[a.length * 4][];
            construct(1, 0, a.length - 1);
        }
        void construct(int v, int l, int r) {
            if(l == r) {
                t[v] = new int[] {a[l]};
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                t[v] = merge(t[v * 2], t[v * 2 + 1]);
            }
        }
        int greater(int v, int l, int r, int ql, int qr, int val) {
            if(ql > qr) {
                return 0;
            }else if(l == ql && r == qr) {
                int ind = bsearch(t[v], val);
                if(ind == -1) {
                    return 0;
                }else {
                    return t[v].length - ind;
                }
            }else {
                int m = (l + r) / 2;
                int left = greater(v * 2, l, m, ql, Math.min(m, qr), val);
                int right = greater(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr, val);
                return left + right;
            }
        }
        int[] merge(int[] a, int[] b) {
            int[] res = new int[a.length + b.length];
            int ap = 0;
            int bp = 0;
            for(int i = 0; i < res.length; i++) {
                if(ap >= a.length) {
                    res[i] = b[bp];
                    bp++;
                }else if(bp >= b.length) {
                    res[i] = a[ap];
                    ap++;
                }else {
                    if(a[ap] <= b[bp]) {
                        res[i] = a[ap];
                        ap++;  
                    }else {
                        res[i] = b[bp];
                        bp++;
                    }
                }
            }
            return res;
        }
        int bsearch(int[] a, int val) {
            int l = 0;
            int r = a.length - 1;
            int ind = -1;
            while(l <= r) {
                int m = (l + r) / 2;
                if(a[m] >= val) {
                    ind = m;
                    r = m - 1;
                }else {
                    l = m + 1;
                }
            }
            return ind;
        }
    }
}
