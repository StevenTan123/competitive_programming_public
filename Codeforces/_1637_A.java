import java.util.*;
import java.io.*;

public class _1637_A {
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
            int[] min = new int[n];
            for(int i = n - 1; i >= 0; i--) {
                min[i] = Math.min(i < n - 1 ? min[i + 1] : Integer.MAX_VALUE, a[i]);
            }
            int max = 0;
            boolean possible = false;
            for(int i = 0; i < n - 1; i++) {
                max = Math.max(max, a[i]);
                if(max > min[i + 1]) {
                    possible = true;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
