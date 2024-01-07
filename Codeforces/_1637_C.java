import java.util.*;
import java.io.*;

public class _1637_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            long moves = 0;
            boolean all_one = true;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if(i > 0 && i < n - 1) {
                    int ceil = a[i] / 2;
                    if(a[i] % 2 == 1) {
                        ceil++;
                    }
                    if(a[i] > 1) {
                        all_one = false;
                    }
                    moves += ceil;
                }
            }
            if(all_one || (n == 3 && a[1] % 2 == 1)) {
                out.println(-1);
            }else {
                out.println(moves);
            }
        }
        in.close();
        out.close();
    }
}
