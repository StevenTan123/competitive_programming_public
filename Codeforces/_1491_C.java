import java.util.*;
import java.io.*;

public class _1491_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int[] c = new int[n];
            long res = 0;
            for(int i = 0; i < n; i++) {
                res += Math.max(a[i] - 1 - c[i], 0);
                for(int j = i + 2; j <= Math.min(n - 1, i + a[i]); j++) {
                    c[j]++;
                }
                int leftover = Math.max(c[i] - (a[i] - 1), 0);
                if(i < n - 1) c[i + 1] += leftover;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
