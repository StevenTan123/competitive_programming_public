import java.util.*;
import java.io.*;

public class _1635_B {
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
            int count = 0;
            for(int i = 1; i < n - 1; i++) {
                if(a[i] > a[i - 1] && a[i] > a[i + 1]) {
                    int to = Math.max(a[i], i < n - 2 ? a[i + 2] : 1);
                    a[i + 1] = to;
                    count++;
                }
            }
            out.println(count);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(a[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
