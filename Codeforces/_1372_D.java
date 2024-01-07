import java.util.*;
import java.io.*;

public class _1372_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        long[][] psum = new long[2][n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            psum[i % 2][i] = (i > 0 ? psum[i % 2][i - 1] : 0) + a[i];
            psum[(i + 1) % 2][i] = i > 0 ? psum[(i + 1) % 2][i - 1] : 0;
        }
        long max = psum[0][n - 1];
        for(int i = 0; i < n - 1; i++) {
            long cur = psum[i % 2][i] + psum[(i + 1) % 2][n - 1] - psum[(i + 1) % 2][i];
            max = Math.max(max, cur);
        }
        out.println(max);
        in.close();
        out.close();
    }
}
