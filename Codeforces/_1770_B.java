import java.util.*;
import java.io.*;

public class _1770_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] p = new int[n];
            for (int i = 0; i < n / 2; i++) {
                p[i * 2] = n - i;
                p[i * 2 + 1] = i + 1;
            }
            if (n % 2 == 1) {
                p[n - 1] = n / 2 + 1;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(p[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
