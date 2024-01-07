import java.util.*;
import java.io.*;

public class _1516_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] += Integer.parseInt(line.nextToken());
                if(i < n - 1) {
                    if(a[i] <= k) {
                        a[n - 1] += a[i];
                        k -= a[i];
                        a[i] = 0;
                    }else {
                        a[n - 1] += k;
                        a[i] -= k;
                        k = 0;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(a[i]);
                if(i < n - 1) sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
