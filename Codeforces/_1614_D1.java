import java.util.*;
import java.io.*;

public class _1614_D1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] cnt = new int[5000005];
        int[] cnt_mult = new int[5000005];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            cnt[a[i]]++;
        }
        for(int i = 1; i < cnt.length; i++) {
            for(int j = i; j < cnt.length; j += i) {
                cnt_mult[i] += cnt[j];
            }
        }
        long[] dp = new long[5000005];
        for(int i = cnt.length - 1; i >= 1; i--) {
            dp[i] = (long)cnt_mult[i] * i;
            for(int j = i; j < cnt.length; j += i) {
                dp[i] = Math.max(dp[i], dp[j] + (long)(cnt_mult[i] - cnt_mult[j]) * i);
            }
        }
        out.println(dp[1]);
        in.close();
        out.close();
    }
}
