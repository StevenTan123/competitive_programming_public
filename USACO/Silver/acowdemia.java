import java.util.*;
import java.io.*;

public class acowdemia {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int l = Integer.parseInt(line.nextToken());
        int[] c = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(c);
        int lbound = 0;
        int rbound = n;
        int res = 0;
        while(lbound <= rbound) {
            int avg = (lbound + rbound) / 2;
            if(ok(c, avg, k, l)) {
                res = avg;
                lbound = avg + 1;
            }else {
                rbound = avg - 1;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static boolean ok(int[] c, int h, int k, int l) {
        long used = 0;
        for(int i = c.length - h; i < c.length; i++) {
            if(h > c[i]) {
                if(h - c[i] > k) return false;
                used += h - c[i];
            }
        }
        if(used > (long)k * l) return false;
        return true;
    }
}
