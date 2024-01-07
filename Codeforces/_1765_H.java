import java.util.*;
import java.io.*;

public class _1765_H {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[] p = new int[n];
        int[] degree = new int[n];
        ArrayList<Integer>[] DAG = new ArrayList[n];
        line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(line.nextToken());
            DAG[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            DAG[b].add(a);
            degree[a]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // Maintains patients sorted descending by p restriction such that all patients that
            // depend on it have been placed higher in the queue already.
            PriorityQueue<Pair> candidates = new PriorityQueue<Pair>();
            for (int j = 0; j < n; j++) {
                if (degree[j] == 0) {
                    candidates.add(new Pair(p[j], j));
                }
            }

            int[] successors_left = new int[n];
            for (int j = 0; j < n; j++) {
                successors_left[j] = degree[j];
            }
            int pos = n;
            while (candidates.size() > 0) {
                Pair cur = candidates.poll();
                if (cur.b == i) {
                    boolean forced = candidates.size() == 0;
                    if (!forced) {
                        Pair next = candidates.poll();
                        if (next.a >= pos) {
                            place_candidate(DAG, candidates, successors_left, p, next);
                            candidates.add(cur);
                            pos--;
                        } else {
                            forced = true;
                        }
                    } 
                    if (forced) {
                        sb.append(pos);
                        sb.append(' ');
                        break;
                    }
                } else {
                    place_candidate(DAG, candidates, successors_left, p, cur);
                    pos--;
                }
            }
        }
        out.println(sb.toString());

        in.close();
        out.close();
    }
    static void place_candidate(ArrayList<Integer>[] DAG, PriorityQueue<Pair> candidates, int[] successors_left, int[] p, Pair placed) {
        for (int nei : DAG[placed.b]) {
            successors_left[nei]--;
            if (successors_left[nei] == 0) {
                candidates.add(new Pair(p[nei], nei));
            }
        }
    }
    static class Pair implements Comparable<Pair> {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
        @Override
        public int compareTo(Pair o) {
            if (a == o.a) {
                return b - o.b;
            }
            return o.a - a;
        }
    }
}
