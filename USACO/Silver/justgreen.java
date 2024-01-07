import java.util.*;
import java.io.*;

public class justgreen {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] ge = new int[n][n];
        int[][] g = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                int val = Integer.parseInt(line.nextToken());
                ge[i][j] = (val >= 100 ? 1 : 0) + (j > 0 ? ge[i][j - 1] : 0);
                g[i][j] = (val > 100 ? 1 : 0) + (j > 0 ? g[i][j - 1] : 0);
            }
        }
        out.println(count_eq(ge, n) - count_eq(g, n));
        in.close();
        out.close();
    }
    static long count_eq(int[][] psums, int n) {
        long res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int prev = -1;
                for(int k = 0; k < n; k++) {
                    boolean full = (psums[k][j] - (i > 0 ? psums[k][i - 1] : 0)) == j - i + 1;
                    if(!full) {
                        int len = k - prev - 1;
                        res += (long)(len + 1) * len / 2;
                        prev = k;
                    }
                }
                int len = n - prev - 1;
                res += (long)(len + 1) * len / 2;
            }
        }
        return res;
    }
}
