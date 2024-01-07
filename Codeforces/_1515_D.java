import java.util.*;
import java.io.*;

public class _1515_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int l = Integer.parseInt(line.nextToken());
            int r = Integer.parseInt(line.nextToken());
            int maxv = Math.max(l, r);
            int minv = Math.min(l, r);
            int[] freqs1 = new int[n];
            int[] freqs2 = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int[] cur;
                if (l >= r && i < l || l < r && i >= l) {
                    cur = freqs1;
                } else {
                    cur = freqs2;
                }
                int c = Integer.parseInt(line.nextToken()) - 1;
                cur[c]++;
            }
            int used = 0;
            for (int i = 0; i < n; i++) {
                int min = Math.min(freqs1[i], freqs2[i]);
                used += min;
                freqs1[i] -= min;
                freqs2[i] -= min;
            }
            int pairs = 0;
            for (int i = 0; i < n; i++) {
                pairs += freqs1[i] / 2;
            }
            int left = (maxv - minv) / 2;
            int paired = used + Math.min(left, pairs);
            int res = left + (n / 2 - paired);
            out.println(res);
        }
        in.close();
        out.close();
    }
}