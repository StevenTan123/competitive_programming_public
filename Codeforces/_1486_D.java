import java.util.*;
import java.io.*;

public class _1486_D {
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
        int l = 0;
        int r = n;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(possible(a, k, m)) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static boolean possible(int[] a, int k, int median) {
        int[] min = new int[a.length];
        int dif = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] >= median) {
                dif++;
            }else {
                dif--;
            }
            min[i] = Math.min(i > 0 ? min[i - 1] : 0, dif);
            if(i - k + 1 >= 0) {
                int prev = i - k >= 0 ? min[i - k] : 0;
                if(dif - prev > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
