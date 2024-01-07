import java.util.*;
import java.io.*;

public class _1591_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int cur = 0;
            int res = 0;
            for(int i = n - 1; i >= 0; i--) {
                if(a[i] > cur) {
                    cur = a[i];
                    res++;
                }
            }
            out.println(res - 1);
        }
        in.close();
        out.close();
    }
}
