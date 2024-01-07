import java.util.*;
import java.io.*;

public class _1637_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            HashMap<Integer, Integer> freqs = new HashMap<Integer, Integer>();
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }

        }
        in.close();
        out.close();
    }
}
