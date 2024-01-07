import java.util.*;
import java.io.*;

public class _1842_E {
    static int M = 1 << 18;
    static int[][] c = new int[M << 1][2];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int A = Integer.parseInt(line.nextToken());

        int[][] points = new int[n][3];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            points[i] = new int[] { x, y, c };
            sum += c;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });

        int[] dp = new int[k + 1];
        int p = 0;
        for (int i = 1; i <= k; i++) {
            add(i - 1, -A);
            while (p < n && points[p][1] == k - i) {
                add(points[p][0], points[p][2]);
                p++;
            }
            dp[i] = Math.max(dp[i - 1], c[1][0]);
            insert(i, dp[i]);
        }
        out.println(sum - dp[k]);

        in.close();
        out.close();
    }
    static void insert(int i, int v) {
        for (i += M; i > 0; i >>= 1) {
            c[i][0] = v;
            v = Math.max(v, c[i ^ 1][0]);
        }
    }
    static void add(int i, int v) {
        for (i += M + 1; i > 1; i >>= 1) {
            int v1 = (i & 1) > 0 ? v : 0;
            c[i ^ 1][0] += v1;
            c[i ^ 1][1] += v1;
            c[i >> 1][0] = Math.max(c[i][0], c[i ^ 1][0]) + c[i >> 1][1];
        }
    }
}
