import java.util.*;
import java.io.*;

public class _1411_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String s = in.readLine();
        int n = s.length();
        StringTokenizer line = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(line.nextToken());
        int y = Integer.parseInt(line.nextToken());
        if(x < y) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if(c == '1') {
                    c = '0';
                }else if(c == '0') {
                    c = '1';
                }
                sb.append(c);
            }
            s = sb.toString();
            int temp = x;
            x = y;
            y = temp;
        }
        int total1 = 0;
        int totalu = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '1') {
                total1++;
            }else if(s.charAt(i) == '?') {
                totalu++;
            }
        }
        long[] pre = new long[n];
        int cur1 = 0;
        int curu = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '1') {
                cur1++;
            }else if(s.charAt(i) == '?') {
                curu++;
            }
            int left1 = cur1 + curu;
            int left0 = i + 1 - left1;
            int right1 = total1 - cur1;
            int right0 = n - i - 1 - right1;
            if(s.charAt(i) == '1') {
                pre[i] = (i > 0 ? pre[i - 1] : 0) + (long)right0 * y;
            }else if(s.charAt(i) == '?') {
                pre[i] = (i > 0 ? pre[i - 1] : 0) + (long)right0 * y;
                pre[i] += (long)left0 * x;
                pre[i] -= (long)(left1 - 1) * y;
            }else {
                pre[i] = (i > 0 ? pre[i - 1] : 0) + (long)right1 * x;
            }
        }
        long[] suf = new long[n];
        cur1 = 0;
        curu = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == '1') {
                cur1++;
            }else if (s.charAt(i) == '?') {
                curu++;
            }
            int right1 = cur1;
            int right0 = n - i - right1;
            int left1 = total1 - cur1 + totalu - curu;
            int left0 = i - left1;
            if(s.charAt(i) == '1') {
                suf[i] = (i < n - 1 ? suf[i + 1] : 0) + (long)right0 * y;
            }else if(s.charAt(i) == '?') {
                suf[i] = (i < n - 1 ? suf[i + 1] : 0) + (long)right1 * x;
            }else {
                suf[i] = (i < n - 1 ? suf[i + 1] : 0) + (long)right1 * x;
            }
        }
        long min = Math.min(pre[n - 1], suf[0]);
        for(int i = 0; i < n - 1; i++) {
            min = Math.min(min, pre[i] + suf[i + 1]);
        }
        out.println(min);
        in.close();
        out.close();
    }
}
