import java.util.*;
import java.io.*;

public class _1841_E {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            Pair[] cols = new Pair[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(line.nextToken());
                cols[i] = new Pair(i, val);
            }
            Arrays.sort(cols);

            Node root = new Node(0, n - 1, n);
            TreeSet<Node> leaves = new TreeSet<Node>();
            leaves.add(root);
            for (int i = 0; i < cols.length; i++) {
                int split = cols[i].ind;
                Node node = leaves.lower(new Node(split + 1, 0, cols[i].val));
                leaves.remove(node);

                node.left = new Node(node.l, split - 1, cols[i].val);
                if (split - 1 >= node.l) {
                    leaves.add(node.left);
                }
                node.right = new Node(split + 1, node.r, cols[i].val);
                if (split + 1 <= node.r) {
                    leaves.add(node.right);
                }
            }
            
            long m = Long.parseLong(in.readLine());
            long filled = 0;
            long segments = 0;
            PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
                @Override
                public int compare(Node a, Node b) {
                    return (b.r - b.l + 1) - (a.r - a.l + 1);
                }
            });
            pq.add(root);
            while (pq.size() > 0) {
                Node cur = pq.poll();
                if (cur.left == null) {
                    continue;
                }
                long capacity = (long)(cur.depth - cur.left.depth) * (cur.r - cur.l + 1);
                if (filled + capacity >= m) {
                    long rows = (m - filled) / (cur.r - cur.l + 1);
                    if ((m - filled) % (cur.r - cur.l + 1) != 0) {
                        rows++;
                    }
                    filled = m;
                    segments += rows;
                    break;
                } else {
                    filled += capacity;
                    segments += (cur.depth - cur.left.depth);
                    pq.add(cur.left);
                    pq.add(cur.right);
                }
            }
            out.println(m - segments);
        }

        in.close();
		out.close();
	}
    static class Node implements Comparable<Node> {
        int l, r, depth;
        Node left, right;
        Node(int ll, int rr, int d) {
            l = ll; 
            r = rr;
            depth = d;
        }
        @Override
        public int compareTo(Node o) {
            return l - o.l;
        }
    }
    static class Pair implements Comparable<Pair> {
        int ind, val;
        Pair(int i, int v) {
            ind = i;
            val = v;
        }
        @Override
        public int compareTo(Pair o) {
            return o.val - val;
        }
    }
}
