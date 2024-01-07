import java.util.*;
import java.io.*;

public class _1705_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            boolean seen = false;
            long moves = 0;
            for(int i = 0; i < n - 1; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                moves += a[i];
                if(a[i] > 0) {
                    seen = true;
                }
                if(a[i] == 0 && seen) {
                    moves++;
                }
            }
            out.println(moves);
        }
        in.close();
        out.close();
    }
}
