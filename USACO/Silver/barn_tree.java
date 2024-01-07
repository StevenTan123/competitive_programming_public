import java.util.*;
import java.io.*;

public class barn_tree {
    static long equal;
    static long[] sums;
    static int[] sizes;
    static ArrayList<Move> moves;
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new FileReader("test.in"));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        long[] h = new long[n];
        StringTokenizer line = new StringTokenizer(in.readLine());

        ArrayList<Integer>[] tree = new ArrayList[n];
        sums = new long[n];
        sizes = new int[n];
        moves = new ArrayList<Move>();

        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(line.nextToken());
            tree[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            tree[u].add(v);
            tree[v].add(u);
        }

        init_dfs(tree, h, 0, -1);
        equal = sums[0] / n;
        dfs(tree, h, 0, -1);
        
        Collections.sort(moves, new Comparator<Move>() {
            @Override
            public int compare(Move a, Move b) {
                if (h[a.from] > h[b.from]) {
                    return 1;
                } else if (h[a.from] < h[b.from]) {
                    return -1;
                }
                return 0;
            }
        });

        ArrayDeque<Move> q = new ArrayDeque<Move>();
        for (Move m : moves) {
            q.add(m);
        }
        
        ArrayList<Move> order = new ArrayList<Move>();
        while (q.size() > 0) {
            Move cur = q.poll();
            if (h[cur.from] >= cur.x) {
                h[cur.from] -= cur.x;
                h[cur.to] += cur.x;
                order.add(cur);
            } else {
                moves.add(cur);
            }
        }

        out.println(order.size());
        for (Move m : order) {
            out.println((m.from + 1) + " " + (m.to + 1) + " " + m.x);
        }

        in.close();
        out.close();
    }
    static void init_dfs(ArrayList<Integer>[] tree, long[] h, int cur, int prev) {
        for (int nei : tree[cur]) {
            if (nei != prev) {
                init_dfs(tree, h, nei, cur);
                sums[cur] += sums[nei];
                sizes[cur] += sizes[nei];
            }
        }
        sums[cur] += h[cur];
        sizes[cur]++;
    }
    static void dfs(ArrayList<Integer>[] tree, long[] h, int cur, int prev) {
        for (int nei : tree[cur]) {
            if (nei != prev) {
                long need = (long)(equal * sizes[nei]);
                if (sums[nei] < need) {
                    moves.add(new Move(cur, nei, need - sums[nei]));
                }else if(sums[nei] > need) {
                    moves.add(new Move(nei, cur, sums[nei] - need));
                }
                dfs(tree, h, nei, cur);
            }
        }
    }
    static class Move {
        int from, to;
        long x;
        Move(int f, int t, long xx) {
            from = f;
            to = t;
            x = xx;
        }
    }
}
