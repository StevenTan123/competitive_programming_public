import java.util.*;
import java.io.*;

public class cownditioning {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line1 = new StringTokenizer(in.readLine());
        StringTokenizer line2 = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        int res = 0;
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line1.nextToken()) - Integer.parseInt(line2.nextToken());
            int prev;
            if(i == 0) {
                prev = 0;
            }else {
                prev = a[i - 1];
            }
            if(a[i] >= 0) {
                res += Math.max(a[i] - Math.max(prev, 0), 0);
            }else {
                res += Math.max(Math.min(prev, 0) - a[i], 0);
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
}
