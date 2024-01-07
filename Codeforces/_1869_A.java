import java.util.*;
import java.io.*;

public class _1869_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }            
            int half = n / 2;
            if (half % 2 == 1) {
                half++;
            }
            out.println(4);
            out.println("1 " + half);
            out.println("1 " + half);
            if ((n - half + 1) % 2 == 1) {
                half--;
            }
            out.println(half + " " + n);
            out.println(half + " " + n);
        }
        in.close();
        out.close();
    }
}
