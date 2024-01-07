import java.util.*;
import java.io.*;

public class _1635_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            boolean nondec = true;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if(i > 0 && a[i] < a[i - 1]) {
                    nondec = false;
                }
            }
            if(nondec) {
                out.println(0);
            }else if(a[n - 2] > a[n - 1] || a[n - 1] < 0) {
                out.println(-1);
            }else {
                out.println(n - 2);
                for(int i = n - 3; i >= 0; i--) {
                    out.println((i + 1) + " " + (i + 2) + " " + n);
                }
            }
        }
        in.close();
        out.close();
    }
}
