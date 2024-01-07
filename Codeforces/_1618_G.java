import java.util.*;
import java.io.*;

public class _1618_G {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[][] items = new int[n + m][2];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            items[i][0] = Integer.parseInt(line.nextToken());
            items[i][1] = 1;
        }
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < m; i++) {
            items[i + n][0] = Integer.parseInt(line.nextToken());
        }
        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        };
        Arrays.sort(items, comp);
        long[] psum = new long[n + m];
        for(int i = 0; i < n + m; i++) {
            psum[i] = (i > 0 ? psum[i - 1] : 0) + items[i][0];
        }
        int[][] queries = new int[q][2];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < q; i++) {
            queries[i][0] = Integer.parseInt(line.nextToken());
            queries[i][1] = i;
        }
        Arrays.sort(queries, comp);
        long total = 0;
        int[] val = new int[n + m];
        int[] par = new int[n + m];
        for(int i = 0; i < n + m; i++) {
            par[i] = i;
            if(items[i][1] == 1) {
                val[i] = 1;
                total += items[i][0];
            }
        }
        int[][] merge = new int[n + m - 1][2];
        for(int i = 1; i < n + m; i++) {
            merge[i - 1][0] = items[i][0] - items[i - 1][0];
            merge[i - 1][1] = i;
        }
        Arrays.sort(merge, comp);
        long[] res = new long[q];
        int p = 0;
        for(int i = 0; i < q; i++) {
            while(p < n + m - 1 && merge[p][0] <= queries[i][0]) {
                int ind = merge[p][1];
                total -= sum(psum, ind - val[ind - 1], ind - 1);
                int head = find(par, ind);
                total -= sum(psum, head - val[head] + 1, head);
                val[head] += val[ind - 1];
                total += sum(psum, head - val[head] + 1, head);
                par[ind - 1] = ind;
                p++;
            }
            res[queries[i][1]] = total;
        }
        for(int i = 0; i < q; i++) {
            out.println(res[i]);
        }
        in.close();
        out.close();
    }
    static long sum(long[] psum, int l, int r) {
        if(l > r) {
            return 0;
        }
        return psum[r] - (l > 0 ? psum[l - 1] : 0);
    }
    static int find(int[] parents, int node) {
        if(parents[node] == node) {
            return node;
        }
        parents[node] = find(parents, parents[node]);
        return parents[node];
    }
}
