import java.util.*;
import java.io.*;

public class _1772_G {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            long x = Long.parseLong(line.nextToken());
            long y = Long.parseLong(line.nextToken());
            
            long[] a = new long[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(line.nextToken());
            }
            Arrays.sort(a);
            int ind = -1;
            for (int i = 0; i < n; i++) {
                a[i] -= i;
                if (ind == -1 && x < a[i]) {
                    ind = i;
                }
            }
            if (ind == -1) {
                ind = n;
            }

            if (ind * 2 <= n) {
                if (y - x > ind) {
                    out.println(-1);
                } else {
                    out.println(y - x);
                }
            } else {
                long res = 0;
                while (ind < n) {
                    long cycles = (a[ind] - x) / (ind - (n - ind));
                    if ((a[ind] - x) % (ind - (n - ind)) != 0) {
                        cycles++;
                    }
                    
                    long cycles2 = Math.max(y - ind - x, 0) / (ind - (n - ind));
                    if (Math.max(y - ind - x, 0) % (ind - (n - ind)) != 0) {
                        cycles2++;
                    }
                    if (cycles2 <= cycles) {
                        long left = y - x - cycles2 * (ind - (n - ind));
                        res += cycles2 * n + left;
                        x = y;
                        break;
                    }
                    res += cycles * n;
                    x += cycles * (ind - (n - ind));
                    while (ind < n && x >= a[ind]) {
                        ind++;
                    }
                }
                if (x < y) {
                    res += y - x;
                }
                out.println(res);
            }
        }
        
        in.close();
        out.close();
    }
}
