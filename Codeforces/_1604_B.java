import java.util.*;
import java.io.*;

public class _1604_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            boolean increasing = true;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if(i > 0 && a[i - 1] >= a[i]) {
                    increasing = false;
                }
            }
            if(n % 2 == 0) {
                out.println("YES");
            }else if(!increasing) {
                out.println("YES");
            }else {
                out.println("NO");
            }
        }
        in.close();
        out.close();
    }
}
