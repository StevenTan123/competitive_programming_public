import java.util.*;
import java.io.*;

public class _1556_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken()) % 2;
            }
            int res = Math.min(fillEven(a), fillOdd(a));
            if(res == Integer.MAX_VALUE) {
                out.println(-1);
            }else {
                out.println(res);
            }
        }
        in.close();
        out.close();
    }
    static int fillEven(int[] a) {
        int even = 0;
        int odd = 1;
        int cost = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] == 0) {
                if(even >= a.length) {
                    return Integer.MAX_VALUE;
                }
                cost += Math.abs(i - even);
                even += 2;
            }else {
                if(odd >= a.length) {
                    return Integer.MAX_VALUE;
                }
                odd += 2;
            }
        }
        return cost;
    }
    static int fillOdd(int[] a) {
        int even = 1;
        int odd = 0;
        int cost = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] == 0) {
                if(even >= a.length) {
                    return Integer.MAX_VALUE;
                }
                cost += Math.abs(i - even);
                even += 2;
            }else {
                if(odd >= a.length) {
                    return Integer.MAX_VALUE;
                }
                odd += 2;
            }
        }
        return cost;
    }
}
