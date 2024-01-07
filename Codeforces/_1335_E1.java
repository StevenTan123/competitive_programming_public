import java.util.*;
import java.io.*;

public class _1335_E1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[][] prefix = new int[n][27];
            for(int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(line.nextToken());
                for(int k = 0; k < 27; k++) {
                    prefix[j][k] = j > 0 ? prefix[j - 1][k] : 0;
                }
                prefix[j][a[j]]++;
            }
            int[][] suffix = new int[n][27];
            for(int j = n - 1; j >= 0; j--) {
                for(int k = 0; k < 27; k++) {
                    suffix[j][k] = j == n - 1 ? 0 : suffix[j + 1][k];
                }
                suffix[j][a[j]]++;
            }
            int max = 0;
            for(int l = 0; l < n; l++) {
                int[] count = new int[27];
                for(int r = l; r < n; r++) {
                    count[a[r]]++;
                    int maxmid = 0;
                    int maxsides = 0;
                    for(int j = 0; j < 27; j++) {
                        maxmid = Math.max(maxmid, count[j]);
                        maxsides = Math.max(maxsides, Math.min(l > 0 ? prefix[l - 1][j] : 0, r == n - 1 ? 0 : suffix[r + 1][j]));
                    }
                    max = Math.max(max, maxmid + (maxsides * 2));
                }
            }
            out.println(max);
        }
        in.close();
        out.close();
    }
}
