import java.util.*;
import java.io.*;

public class _1775_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            long[] psum = new long[n];
            long max_pos = 0;
            long max_neg = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                psum[i] = (i > 0 ? psum[i - 1] : 0) + Integer.parseInt(line.nextToken());
                if (psum[i] >= 0) {
                    max_pos = Math.max(max_pos, psum[i]);
                } else {
                    max_neg = Math.max(max_neg, -psum[i]);
                }
            }
            out.println(max_pos + max_neg);
        }
        in.close();
        out.close();
    }
}
