import java.util.*;
import java.io.*;

public class snowcow {
    static ArrayList<Integer>[] tree;
    static int[] start, end, size;
    static int n, q;
    static int p = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("snowcow.in"));
        PrintWriter out = new PrintWriter("snowcow.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        q = Integer.parseInt(line.nextToken());
        tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            tree[u].add(v);
            tree[v].add(u);
        }
        start = new int[n];
        end = new int[n];
        size = new int[n];
        dfs(0, new boolean[n]);
        BIT above = new BIT(new long[n + 1]);
        BIT below = new BIT(new long[n + 1]);
        TreeMap<Integer, Integer>[] colors = new TreeMap[100005];
        for(int i = 0; i < 100005; i++) {
            colors[i] = new TreeMap<Integer, Integer>();
        }
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken()) - 1;
            if(type == 1) {
                int c = Integer.parseInt(line.nextToken());
                Integer higher = colors[c].higherKey(start[x]);
                Integer lower = colors[c].lowerKey(start[x]);
                if(lower != null && end[colors[c].get(lower)] >= end[x]) {
                    continue;
                }
                while(higher != null && higher < end[x]) {
                    above.update(higher, -1);
                    int ind = colors[c].get(higher);
                    above.update(end[ind], 1);
                    below.update(higher, -size[ind]);
                    colors[c].remove(higher);
                    higher = colors[c].higherKey(higher);
                }
                above.update(start[x], 1);
                above.update(end[x], -1);
                below.update(start[x], size[x]);
                colors[c].put(start[x], x);
            }else {
                out.println(above.sum(0, start[x]) * size[x] + below.sum(start[x] + 1, end[x] - 1));
            }
        }
        in.close();
        out.close();
    }   
    static int dfs(int node, boolean[] visited) {
        if(visited[node]) {
            return 0;
        }
        visited[node] = true;
        start[node] = p;
        p++;
        int count = 1;
        for(int nei : tree[node]) {
            count += dfs(nei, visited);
        }
        end[node] = p;
        size[node] = count;
        return count;
    }
    static class BIT {
        long[] bit;
        BIT(long[] a) {
            bit = new long[a.length + 1];
            for(int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }
        void update(int index, long add) {
            index++;
            while (index < bit.length) {
                bit[index] += add;
                index += index & -index;
            }
        }
        long sum(int l, int r) {
            if(l > r) return 0;
            return psum(r) - (l == 0 ? 0 : psum(l - 1));
        }
        long psum(int index) {
            index++;
            long res = 0;
            while(index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
