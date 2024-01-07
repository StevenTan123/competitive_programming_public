import java.util.*;
import java.io.*;

public class strongest_friendship {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        int[] degree = new int[n];
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
            degree[a]++;
            degree[b]++;
        }
        TreeSet<Node> sorted = new TreeSet<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if(a.degree == b.degree) {
                    return a.node - b.node;
                }
                return a.degree - b.degree;
            }
        });
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) {
            nodes[i] = new Node(i, degree[i]);
            sorted.add(nodes[i]);
        }

        int[] order = new int[n];
        int[] degrees = new int[n];
        for(int i = 0; i < n; i++) {
            Node cur = sorted.pollFirst();
            degrees[i] = cur.degree;
            order[i] = cur.node;
            for(int ind : graph[cur.node]) {
                Node nei = nodes[ind];
                if(!sorted.contains(nei)) {
                    continue;
                }
                sorted.remove(nei);
                nei.degree--;
                sorted.add(nei);
            }
        }

        int[] par = new int[n];
        int[] sizes = new int[n];
        for(int i = 0; i < n; i++) {
            par[i] = i;
            sizes[i] = 1;
        }

        long res = 0;
        boolean[] seen = new boolean[n];
        for(int i = n - 1; i >= 0; i--) {
            int cur = order[i];
            seen[cur] = true;
            for(int nei : graph[cur]) {
                if(seen[nei]) {
                    union(par, sizes, cur, nei);
                }
            }
            res = Math.max(res, (long)(sizes[find(par, cur)]) * degrees[i]);
        }
        out.println(res);

        in.close();
        out.close();
    }
    static int find(int[] par, int cur) {
        if(par[cur] == cur) {
            return cur;
        }
        par[cur] = find(par, par[cur]);
        return par[cur];
    }
    static void union(int[] par, int[] sizes, int a, int b) {
        int ra = find(par, a);
        int rb = find(par, b);
        if(ra != rb) {
            if(sizes[ra] < sizes[rb]) {
                par[ra] = rb;
                sizes[rb] += sizes[ra];
            }else {
                par[rb] = ra;
                sizes[ra] += sizes[rb];
            }
        }
    }
    static class Node {
        int node, degree;
        Node(int n, int d) {
            node = n;
            degree = d;
        }
    }
}
