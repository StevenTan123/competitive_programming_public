import java.util.*;
import java.io.*;

public class _1801_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int p = Integer.parseInt(line.nextToken());

            int[] w = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                w[i] = Integer.parseInt(line.nextToken());
            }
            ArrayList<Edge>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Edge>();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                int wi = Integer.parseInt(line.nextToken());
                graph[u].add(new Edge(v, wi));
            }

            long res = -1;
            Pair[][] dp = new Pair[n][n];
            dp[0][0] = new Pair(0, p);
            PriorityQueue<Step> pq = new PriorityQueue<Step>();
            pq.add(new Step(0, p, 0, 0));
            while (pq.size() > 0) {
                Step cur = pq.poll();
                if (cur.node == n - 1) {
                    res = cur.perf;
                    break;
                }

                Pair dp_val = dp[cur.node][cur.max_city];
                if (dp_val != null && (cur.perf != dp_val.min_perf || cur.money != dp_val.max_money)) {
                    continue;
                }
                
                for (Edge e : graph[cur.node]) {
                    long money = cur.money;
                    long perf = cur.perf;
                    if (money < e.w) {
                        long ceil = (e.w - money) / w[cur.max_city];
                        if ((e.w - money) % w[cur.max_city] != 0) {
                            ceil++;
                        }
                        perf += ceil;
                        money = ceil * w[cur.max_city] + money - e.w;
                    } else {
                        money -= e.w;
                    }

                    int max_city = cur.max_city;
                    if (w[e.v] > w[max_city]) {
                        max_city = e.v;
                    }
                    Pair next_dp = dp[e.v][max_city];
                    if (next_dp == null) {
                        dp[e.v][max_city] = new Pair(perf, money);
                        pq.add(new Step(perf, money, max_city, e.v));
                    } else {
                        if (perf < next_dp.min_perf || (perf == next_dp.min_perf && money > next_dp.max_money)) {
                            dp[e.v][max_city] = new Pair(perf, money);
                            pq.add(new Step(perf, money, max_city, e.v));
                        }
                    }
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }

    static class Step implements Comparable<Step> {
        long perf, money;
        int max_city, node;

        Step(long p, long mon, int city, int c) {
            perf = p;
            money = mon;
            max_city = city;
            node = c;
        }

        @Override
        public int compareTo(Step o) {
            if (perf == o.perf) {
                return Long.signum(money - o.money);
            }
            return Long.signum(perf - o.perf);
        }
    }

    static class Edge {
        int v, w;

        Edge(int vv, int ww) {
            v = vv;
            w = ww;
        }
    }

    static class Pair {
        long min_perf, max_money;

        Pair(long min, long max) {
            min_perf = min;
            max_money = max;
        }
    }
}
