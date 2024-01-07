import java.util.*;
import java.io.*;

public class _1616_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] count = new int[101];
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                count[Math.abs(a[i])]++;
            }
            int res = 0;
            for(int i = 1; i <= 100; i++) {
                if(count[i] == 1) {
                    res++;
                }else if(count[i] > 1) {
                    res += 2;
                }
            }
            if(count[0] > 0) {
                res++;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
