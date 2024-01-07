import java.util.*;
import java.io.*;

public class reverse_eng {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = i + 1;
            String res = "Case #" + t + ": ";
            if(c < n - 1 || c > n * (n + 1) / 2 - 1) {
                out.println(res + "IMPOSSIBLE");
                continue;
            }
            int[][] rev = new int[n - 1][2];
            int sum = n - 1;
            for(int i = 0; i < n - 1; i++) {
                int use = Math.min(c - sum, n - i - 1);
                rev[i][0] = i;
                rev[i][1] = i + use;
                sum += use;
            }
            for(int i = n - 2; i >= 0; i--) {
                reverse(a, rev[i][0], rev[i][1]);
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(a[i]);
                if(i < n - 1) sb.append(' ');
            }
            out.println(res + sb.toString());
        }
        in.close();
        out.close();
    }
    static void reverse(int[] a, int l, int r) {
        for(int i = l; i <= r; i++) {
            int opp = r - (i - l);
            if(i >= opp) continue;
            int temp = a[i];
            a[i] = a[opp];
            a[opp] = temp;
        }
    }
}