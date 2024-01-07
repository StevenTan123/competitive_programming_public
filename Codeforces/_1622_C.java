import java.util.*;
import java.io.*;

public class _1622_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            long k = Long.parseLong(line.nextToken());
            int[] a = new int[n];
            long sum = 0;
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                sum += a[i];
            }
            if(sum <= k) {
                out.println(0);
            }else {
                sort(a);
                long min = (long)a[0] * n;
                long count = 0;
                if(min > k) {
                    long left = min - k;
                    long ceil = left / n;
                    if(left % n != 0) {
                        ceil++;
                    }
                    a[0] -= ceil;
                    count += ceil;
                }
                long[] psum = new long[n];
                for(int i = 0; i < n; i++) {
                    psum[i] = (i > 0 ? psum[i - 1] : 0) + a[i];
                }
                int p = 0;
                long res = Long.MAX_VALUE;
                for(int i = 0; i < n; i++) {
                    while(p < n) {
                        long val = psum[p] - i + (long)(n - p - 1) * a[0];
                        if(val > k) {
                            break;
                        }
                        p++;
                    }
                    res = Math.min(res, count + i + n - p);
                    a[0]--;
                }
                out.println(res);
            }
        }
        in.close();
        out.close();
    }
    static void sort(int[] a) {
        Random rand = new Random();
        for(int i = 0; i < a.length; i++) {
            int ind = rand.nextInt(a.length);
            int temp = a[i];
            a[i] = a[ind];
            a[ind] = temp;
        }
        Arrays.sort(a);
    }
}
