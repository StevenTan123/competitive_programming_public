import java.util.*;
import java.io.*;

public class moo_network {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        Cow[] cows = new Cow[n];
        TreeSet<Cow>[] by_y = new TreeSet[11];
        for(int i = 0; i <= 10; i++) {
            by_y[i] = new TreeSet<Cow>();
        }
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            cows[i] = new Cow(x, y, i);
            by_y[y].add(cows[i]);
        }
        long res = 0;
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        boolean[] visited = new boolean[n];
        pq.add(new Step(cows[0], 0));
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(visited[cur.to.id]) {
                continue;
            }
            visited[cur.to.id] = true;
            res += cur.dist;
            for(int i = 0; i <= 10; i++) {
                TreeSet<Cow> cows_y = by_y[i];
                Cow left = cows_y.lower(cur.to);
                Cow right = cows_y.higher(cur.to);
                if(left != null) {
                    pq.add(new Step(left, dist(cur.to, left)));
                }
                if(right != null) {
                    pq.add(new Step(right, dist(cur.to, right)));
                }
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long dist(Cow a, Cow b) {
        return (long)(a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
    static class Step implements Comparable<Step> {
        Cow to;
        long dist;
        Step(Cow t, long d) {
            to = t;
            dist = d;
        }     
        @Override
        public int compareTo(Step o) {
            if(dist < o.dist) {
                return -1;
            }else if(dist > o.dist) {
                return 1;
            }
            return 0;
        }
    }
    static class Cow implements Comparable<Cow> {
        int x, y, id;
        boolean used;
        Cow(int xx, int yy, int i) {
            x = xx;
            y = yy;
            id = i;
            used = false;
        }
        @Override
        public int compareTo(Cow o) {
            if(y == o.y) {
                if(x == o.x) {
                    return id - o.id;
                }
                return x - o.x;
            }else {
                if(x >= o.x) {
                    return 1;
                }else {
                    return -1;
                }
            }
        }
    }
}
