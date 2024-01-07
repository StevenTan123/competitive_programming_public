import java.util.*;
import java.io.*;

public class balance {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("balance.in"));
        PrintWriter out = new PrintWriter("balance.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] a2 = new int[n];
        int[] b2 = new int[n];
        long ainv = 0;
        long binv = 0;
        int ones = 0;
        StringTokenizer line = new StringTokenizer(in.readLine());
        int count = 0;
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            a2[i] = 1 - a[i];
            if(a[i] == 1) {
                count++;
                ones++;
            }else ainv += count;
        }
        count = 0;
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(line.nextToken());
            b2[i] = 1 - b[i];
            if(b[i] == 1) {
                count++;
                ones++;
            }else binv += count;
        }
        long delta = ainv - binv;
        long res = Math.min(brute(a, b, delta, n, ones - n, 1), brute(a2, b2, delta, n, ones - n, -1));
        out.println(res);
        in.close();
        out.close();
    }
    static long brute(int[] a, int[] b, long delta, int n, int change, int sign) {
        int l = n - 1;
        int r = 0;
        while(l >= 0 && a[l] == 0) l--;
        while(r < n && b[r] == 1) r++;
        if(l < 0 || r >= n) return Math.abs(delta);
        long swaps = 0;
        long res = Math.abs(delta);
        for(int i = 1; i < n; i++) {
            swaps += n - 1 - l + r + 1;
            delta -= (n - 1 - l) * sign;
            delta += r * sign;
            delta += change * sign;
            res = Math.min(res, swaps + Math.abs(delta));
            l--;
            r++;
            while(l >= 0 && a[l] == 0) l--;
            while(r < n && b[r] == 1) r++;
            if(l < 0 || r >= n) return res;
        }
        return res;
    }
}
