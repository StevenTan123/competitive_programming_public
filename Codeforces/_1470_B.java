import java.util.*;
import java.io.*;

public class _1470_B {
    public static void main(String[] args) throws IOException {
        int[] spf = new int[1000005];
        for(int i = 2; i < 1000005; i++) {
            if(spf[i] == 0) {
                for(int j = i; j < 1000005; j += i) {
                    if(spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            HashMap<Integer, Integer> freqs = new HashMap<Integer, Integer>();
            for(int i = 0; i < n; i++) {
                int num = Integer.parseInt(line.nextToken());
                HashMap<Integer, Integer> factors = new HashMap<Integer, Integer>();
                while(num > 1) {
                    Integer freq = factors.get(spf[num]);
                    if(freq == null) freq = 0;
                    factors.put(spf[num], freq + 1);
                    num /= spf[num];
                }
                a[i] = 1;
                for(int factor : factors.keySet()) {
                    if(factors.get(factor) % 2 == 1) {
                        a[i] *= factor;
                    }
                }
                Integer freq = freqs.get(a[i]);
                if(freq == null) freq = 0;
                freqs.put(a[i], freq + 1);
            }
            int b0 = 0;
            int b1 = 0;
            int bo = 0;
            for(int val : freqs.keySet()) {
                int freq = freqs.get(val);
                b0 = Math.max(b0, freq);
                if(val == 1) {
                    b1 += freq;
                }else {
                    if(freq % 2 == 1) bo = Math.max(bo, freq);
                    else b1 += freq;
                }
            }
            b1 = Math.max(b1, bo);
            int q = Integer.parseInt(in.readLine());
            for(int i = 0; i < q; i++) {
                long qi = Long.parseLong(in.readLine());
                if(qi == 0) {
                    out.println(b0);
                }else {
                    out.println(b1);
                }
            }
        }
        in.close();
        out.close();
    }
}
