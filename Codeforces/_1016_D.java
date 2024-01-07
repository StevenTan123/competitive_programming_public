import java.util.*;
import java.io.*;

public class _1016_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        int[] b = new int[m];
        line = new StringTokenizer(in.readLine());
        int total = 0;
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            total ^= a[i];
        }
        line = new StringTokenizer(in.readLine());
        int total2 = 0;
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(line.nextToken());
            total2 ^= b[i];
        }
        if(total == total2) {
            int[][] res = new int[n][m];
            res[0][0] = a[0];
            for(int i = 1; i < m; i++) {
                res[0][0] ^= b[i];
                res[0][i] = b[i];
            }
            for(int i = 1; i < n; i++) {
                res[i][0] = a[i];
            }
            out.println("YES");
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < m; j++) {
                    sb.append(res[i][j]);
                    sb.append(' ');
                }
                out.println(sb.toString());
            }
            out.println();
        }else {
            out.println("NO");
        }
        in.close();
        out.close();
    }
}