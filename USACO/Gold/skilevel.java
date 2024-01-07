import java.util.*;
import java.io.*;

public class skilevel {
    static int n, m, t;
    static Node[] cells;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("skilevel.in"));
        PrintWriter out = new PrintWriter("skilevel.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        m = Integer.parseInt(line.nextToken());
        n = Integer.parseInt(line.nextToken());
        t = Integer.parseInt(line.nextToken());
        Edge[] edges = new Edge[2 * (m - 1) * (n - 1) + (m - 1) + (n - 1)];
        cells = new Node[m * n];
        int p = 0;
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                int ind = i * n + j;
                cells[ind] = new Node(ind, 0, 0, Integer.parseInt(line.nextToken()));
                if(i > 0) {
                    int ind2 = (i - 1) * n + j;
                    edges[p] = new Edge(ind2, ind, Math.abs(cells[ind2].val - cells[ind].val));
                    p++;
                }
                if(j > 0) {
                    int ind2 = i * n + j - 1;
                    edges[p] = new Edge(ind2, ind, Math.abs(cells[ind2].val - cells[ind].val));
                    p++;
                }
                cells[ind].size = 1;
                cells[ind].res = -1;
            }
        }
        Arrays.sort(edges);
        for(Edge e : edges) {
            union(e.v1, e.v2, e.dif);
        }
        long ans = 0;
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                int val = Integer.parseInt(line.nextToken());
                if(val == 1) {
                    ans += find_dif(i * n + j);
                }
            }
        }
        out.println(ans);
        in.close();
        out.close();
    }
    static int find_dif(int node) {
        if(cells[node].res != -1) {
            return cells[node].res;
        }
        return find_dif(cells[node].par);
    }
    static int find(int node) {
        if(cells[node].par == node) {
            return node;
        }
        return find(cells[node].par);
    }
    static void union(int a, int b, int dif) {
        int roota = find(a);
        int rootb = find(b);
        if(roota == rootb) {
            return;
        }
        int max = roota;
        int min = rootb;
        if(cells[roota].size < cells[rootb].size) {
            max = rootb;
            min = roota;
        }
        if(cells[max].size + cells[min].size >= t) {
            if(cells[max].size < t) {
                cells[max].res = dif;
            }
            if(cells[min].size < t) {
                cells[min].res = dif;
            }
        }
        cells[min].par = max;
        cells[max].size += cells[min].size;
    }
    static class Node {
        int par, size, res, val;
        Node(int p, int s, int r, int v) {
            par = p;
            size = s;
            res = r;
            val = v;
        }
    }
    static class Edge implements Comparable<Edge> {
        int v1, v2, dif;
        Edge(int vv1, int vv2, int d) {
            v1 = vv1;
            v2 = vv2;
            dif = d;
        }
        @Override
        public int compareTo(Edge o) {
            return dif - o.dif;
        }
    }
}
