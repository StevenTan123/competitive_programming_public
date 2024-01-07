import java.util.*;
import java.io.*;

public class _1875_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int n = Integer.parseInt(line.nextToken());
            int[] x = new int[n];
            long res = b;
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(line.nextToken());
                res += Math.min(a - 1, x[i]);
            }
            out.println(res);
        }
        
        in.close();
        out.close();
    }
}
