import java.util.*;
import java.io.*;

public class equal_sum {
    static long[] pow2 = new long[31];
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for(int i = 1; i <= 30; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        long sum_a = 0;
        long[] a = new long[100];
        StringBuilder a_str = new StringBuilder();
        for(int i = 0; i < 30; i++) {
            a[i] = pow2[i];
            sum_a += a[i];
            a_str.append(a[i]);
            a_str.append(' ');
        }
        int prev = 1;
        for(int i = 30; i < 100; i++) {
            while(pow2(prev)) {
                prev++;
            }
            a[i] = prev;
            sum_a += a[i];
            a_str.append(a[i]);
            a_str.append(' ');
            prev++;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            long sum = sum_a;
            int n = Integer.parseInt(in.readLine());
            System.out.println(a_str.toString());
            System.out.flush();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] b = new int[n];
            for(int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(line.nextToken());
                sum += b[i];
            }
            long half = sum / 2;
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < n; i++) {
                if(b[i] <= half) {
                    half -= b[i];
                    res.append(b[i]);
                    res.append(' ');
                }
            }
            int count = 0;
            while(half > 0) {
                if(half % 2 == 1) {
                    res.append(pow2[count]);
                    res.append(' ');
                }
                half /= 2;
                count++;
            }
            System.out.println(res.toString());
            System.out.flush();
        }
        in.close();
    }
    static boolean pow2(long val) {
        for(int i = 0; i <= 30; i++) {
            if(val == pow2[i]) {
                return true;
            }
        }
        return false;
    }
}
