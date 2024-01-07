import java.util.*;
import java.io.*;

public class _1879_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int[] b = new int[n];
            long suma = 0;
            long sumb = 0;
            int mina = Integer.MAX_VALUE;
            int minb = Integer.MAX_VALUE;
            StringTokenizer line1 = new StringTokenizer(in.readLine());
            StringTokenizer line2 = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line1.nextToken());
                b[i] = Integer.parseInt(line2.nextToken());
                suma += a[i];
                sumb += b[i];
                mina = Math.min(mina, a[i]);
                minb = Math.min(minb, b[i]);
            }
            long rows = suma + (long) minb * n;
            long cols = sumb + (long) mina * n;
            out.println(Math.min(rows, cols));
        }
        in.close();
        out.close();
    }
}
