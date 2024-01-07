import java.util.*;
import java.io.*;

public class _1815_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            ArrayList<Integer>[] graph = new ArrayList[n];
            ArrayList<Node>[] nodes = new ArrayList[n];
            StringBuilder[] combos = new StringBuilder[n];
            int[] lens = new int[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
                nodes[i] = new ArrayList<Node>();
                combos[i] = new StringBuilder();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[v].add(u);
            }
            int maxd = 0;
            int count = 0;
            boolean[] visited = new boolean[n];
            ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
            bfs.add(new BFS(0, 0));
            while (bfs.size() > 0) {
                BFS cur = bfs.poll();
                if (visited[cur.node]) {
                    continue;
                }
                visited[cur.node] = true;
                maxd = Math.max(maxd, cur.dist);
                count++;

                combos[cur.dist].append(cur.node + 1);
                combos[cur.dist].append(' ');
                lens[cur.dist]++;
                for (int nei : graph[cur.node]) {
                    bfs.add(new BFS(nei, cur.dist + 1));
                }
            }
            if (count == n) {
                Node head = new Node(0);
                nodes[0].add(head);
                for (int i = 0; i < maxd; i++) {
                    for (int j = 0; j <= nodes[i].size() / 2; j++) {
                        Node cur = new Node(i + 1);
                        nodes[i + 1].add(cur);
                        if (j == 0) {
                            head = cur;
                        }    
                        add_left(cur, nodes[i].get(j));
                    }
                    for (int j = nodes[i].size() / 2; j < nodes[i].size(); j++) {
                        Node cur = new Node(i + 1);
                        nodes[i + 1].add(cur);
                        add_right(cur, nodes[i].get(j));
                    }
                }
                Node cur = head;
                int len = 0;
                StringBuilder res = new StringBuilder();
                while (cur != null) {
                    res.append(combos[cur.x]);
                    len += lens[cur.x];
                    cur = cur.next;
                }
                out.println("FINITE");
                out.println(len);
                out.println(res.toString());

            } else {
                out.println("INFINITE");
            }
        }

        in.close();
        out.close();
    }
    static class BFS {
        int node, dist;
        BFS(int n, int d) {
            node = n;
            dist = d;
        }
    }
    static class Node {
        Node prev, next;
        int x;
        Node(int xx) {
            x = xx;
        }
    }
    // Add node a before node b
    static void add_left(Node a, Node b) {
        if (b.prev == null) {
            b.prev = a;
            a.next = b;
        } else {
            a.next = b;
            a.prev = b.prev;
            b.prev.next = a;
            b.prev = a;
        }
    }
    // Add node a after node b
    static void add_right(Node a, Node b) {
        if (b.next == null) {
            b.next = a;
            a.prev = b;;
        } else {
            a.next = b.next;
            a.prev = b;
            b.next.prev = a;
            b.next = a;
        }
    }
}
