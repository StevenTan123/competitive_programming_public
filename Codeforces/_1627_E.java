import java.util.*;
import java.io.*;

public class _1627_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] x = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(line.nextToken());
            }
            ArrayList<Node>[] lower = new ArrayList[n];
            ArrayList<Node>[] upper = new ArrayList[n];
            for(int i = 0; i < k; i++) {
                line = new StringTokenizer(in.readLine());
                int[] ladder = new int[5];
                for(int j = 0; j < 5; j++) {
                    ladder[j] = Integer.parseInt(line.nextToken());
                }
                if(lower[ladder[0] - 1] == null) {
                    lower[ladder[0] - 1] = new ArrayList<Node>();
                }
                if(upper[ladder[2] - 1] == null) {
                    upper[ladder[2] - 1] = new ArrayList<Node>();
                }
                Node above = new Node(null, Long.MAX_VALUE, 0, ladder[3]);
                upper[ladder[2] - 1].add(above);
                lower[ladder[0] - 1].add(new Node(above, 0, ladder[4], ladder[1]));
            }
            upper[0] = new ArrayList<Node>();
            upper[0].add(new Node(null, 0, 0, 1));
            for(int i = 0; i < n; i++) {
                if(lower[i] == null || upper[i] == null) {
                    continue;
                }
                Collections.sort(lower[i]);
                Collections.sort(upper[i]);
                int p = 0;
                long min = Long.MAX_VALUE;
                for(int j = 0; j < lower[i].size(); j++) {
                    Node cur = lower[i].get(j);
                    Node cur2 = null;
                    while(p < upper[i].size() && (cur2 = upper[i].get(p)).col <= cur.col) {
                        if(cur2.min != Long.MAX_VALUE) {
                            min = Math.min(min, (long)-x[i] * cur2.col + cur2.min);
                        }
                        p++;
                    }
                    if(min != Long.MAX_VALUE) {
                        cur.above.min = Math.min(cur.above.min, (long)x[i] * cur.col + min - cur.health);
                    }
                }

                p = upper[i].size() - 1;
                min = Long.MAX_VALUE;
                for(int j = lower[i].size() - 1; j >= 0; j--) {
                    Node cur = lower[i].get(j);
                    Node cur2 = null;
                    while(p >= 0 && (cur2 = upper[i].get(p)).col >= cur.col) {
                        if(cur2.min != Long.MAX_VALUE) {
                            min = Math.min(min, (long)-x[i] * (m - cur2.col) + cur2.min);
                        }
                        p--;
                    }
                    if(min != Long.MAX_VALUE) {
                        cur.above.min = Math.min(cur.above.min, (long)x[i] * (m - cur.col) + min - cur.health);
                    }
                }
            }
            if(upper[n - 1] != null) {
                long res = Long.MAX_VALUE;
                for(Node cur : upper[n - 1]) {
                    if(cur.min != Long.MAX_VALUE) {
                        res = Math.min(res, (long)x[n - 1] * (m - cur.col) + cur.min);
                    }
                }
                if(res == Long.MAX_VALUE) {
                    out.println("NO ESCAPE");
                }else {
                    out.println(res);
                }
            }else {
                out.println("NO ESCAPE");
            }
        }
        in.close();
        out.close();
    }
    static class Node implements Comparable<Node> {
        Node above;
        long min;
        int health, col;
        Node(Node a, long m, int h, int c) {
            above = a;
            min = m;
            health = h;
            col = c;
        }
        @Override
        public int compareTo(Node o) {
            return col - o.col;
        }
    }
}
