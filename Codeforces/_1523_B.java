import java.util.*;
import java.io.*;

public class _1523_B {
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
            out.println(3 * n);
            int[] pattern = new int[] {1, 2, 1, 1, 2, 1};
            for(int i = 0; i < n / 2; i++) {
                for(int j = 0; j < 6; j++) {
                    out.println(pattern[j] + " " + (i * 2 + 1) + " " + (i * 2 + 2));
                }
            }
        }
        in.close();
        out.close();
    }
}
