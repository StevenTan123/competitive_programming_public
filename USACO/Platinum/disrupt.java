import java.util.*;
import java.io.*;

public class disrupt {
    static ArrayList<Edge>[] tree;
    static TreeSet<Integer>[] flags;
    static boolean[] visited;
    static int[] length, res;
    static Comparator<Integer> comp = new Comparator<Integer>(){
        @Override
        public int compare(Integer a, Integer b) {
            return length[a] - length[b];
        }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("disrupt.in"));
        PrintWriter out = new PrintWriter("disrupt.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Edge>();
        }
        for(int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(line.nextToken()) - 1;
            int q = Integer.parseInt(line.nextToken()) - 1;
            tree[p].add(new Edge(q, i));
            tree[q].add(new Edge(p, i));
        }
        flags = new TreeSet[n];
        for(int i = 0; i < n; i++) {
            flags[i] = new TreeSet<Integer>(comp);
        }
        length = new int[m];
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(line.nextToken()) - 1;
            int q = Integer.parseInt(line.nextToken()) - 1;
            int r = Integer.parseInt(line.nextToken());
            length[i] = r;
            flags[p].add(i);
            flags[q].add(i);
        }
        visited = new boolean[n];
        res = new int[n - 1];
        dfs(0, null);
        for(int i = 0; i < n - 1; i++) {
            out.println(res[i]);
        }
        in.close();
        out.close();
    }
    static TreeSet<Integer> dfs(int node, Edge prev) {
        if(visited[node]) {
            return null;
        }
        visited[node] = true;
        TreeSet<Integer> merged = flags[node];
        for(Edge e : tree[node]) {
            TreeSet<Integer> child = dfs(e.node, e);
            if(child != null) {
                TreeSet<Integer> small, large;
                if(child.size() < merged.size()) {
                    small = child;
                    large = merged;
                }else {
                    small = merged;
                    large = child;
                }
                for(int i : small) {
                    if(large.contains(i)) {
                        large.remove(i);
                    }else {
                        large.add(i);
                    }
                }
                merged = large;
            }
        }
        if(prev != null) {
            if(merged.size() > 0) {
                res[prev.ind] = length[merged.first()];
            }else {
                res[prev.ind] = -1;
            }
        }
        return merged;
    }
    static class Edge {
        int node, ind;
        Edge(int n, int i) {
            node = n;
            ind = i;
        }
    }
}
