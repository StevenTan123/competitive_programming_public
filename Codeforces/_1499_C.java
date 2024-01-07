import java.util.*;
import java.io.*;

public class _1499_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] c = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(line.nextToken());
            }
            long sumup = 0;
            int minup = Integer.MAX_VALUE;
            long sumri = 0;
            int minri = Integer.MAX_VALUE;
            long res = Long.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                if(i % 2 == 0) {
                    sumup += c[i];
                    minup = Math.min(minup, c[i]);
                }else {
                    sumri += c[i];
                    minri = Math.min(minri, c[i]);
                }
                if(i >= 1) {
                    long mincost = sumup + sumri;
                    long upleft = n - ((int)(i / 2) + 1);
                    long rileft = n - ((int)((i + 1) / 2));
                    mincost += upleft * minup;
                    mincost += rileft * minri;
                    res = Math.min(res, mincost);
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
