import java.util.*;
import java.io.*;

public class _1541_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n + 1];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            long res = 0;
            for(int i = 1; i <= n; i++) {
                int start = a[i] - (i % a[i]);
                for(int j = start; j <= n; j += a[i]) {
                    if(j <= i) continue;
                    if(i + j == (long)a[i] * a[j]) {
                        res++;
                    }
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
