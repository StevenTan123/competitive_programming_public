import java.util.*;
import java.io.*;

public class _1557_A {
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
            Arrays.sort(a);
            double avg = 0;
            for(int i = 0; i < n - 1; i++) {
                avg += a[i];
            }
            avg /= n - 1;
            out.println(avg + a[n - 1]);
        }
        in.close();
        out.close();
    }
}
