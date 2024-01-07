import java.util.*;
import java.io.*;

public class _1637_B {
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
            long res = 0;
            for(int l = 0; l < n; l++) {
                for(int r = l; r < n; r++) {
                    res += value(a, l, r);
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static int value(int[] a, int l, int r) {
        int value = r - l + 1;
        for(int i = l; i <= r; i++) {
            if(a[i] == 0) {
                value++;
            }
        }
        return value;
    }
}
