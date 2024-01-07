import java.util.*;
import java.io.*;

public class _1370_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        out.println(bsearch(a, k));
        in.close();
        out.close();
    }
    static int bsearch(int[] a, int k) {
        int l = 0;
        int r = 1000000000;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(ok(a, 0, m, k) || ok(a, 1, m, k)) {
                res = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        return res;
    }
    static boolean ok(int[] a, int start, int under, int k) {
        int count = start;
        boolean prev = false;
        for(int i = start; i < a.length; i++) {
            if(!prev && a[i] <= under) {
                prev = true;
                count++;
            }else if(prev) {
                prev = false;
                count++;
            }
        }
        return count >= k;
    }
}
