import java.util.*;
import java.io.*;

public class _1508_B {
    static long[] pows = new long[63];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        pows[0] = 1;
        for(int i = 1; i < 63; i++) {
            pows[i] = pows[i - 1] * 2;
        }
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            long k = Long.parseLong(line.nextToken());
            if(n < 63 && k > pows[n - 1]) {
                out.println(-1);
                continue;
            }
            ArrayList<Integer> blocks = new ArrayList<Integer>();
            recurse(n, k, blocks);
            int[] perm = new int[n];
            for(int i = 0; i < n; i++) perm[i] = i + 1;
            int sum = 0;
            for(int i = 0; i < blocks.size(); i++) {
                reverse(perm, sum, sum + blocks.get(i) - 1);
                sum += blocks.get(i);
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(perm[i]);
                if(i < n - 1) sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static void recurse(int n, long k, ArrayList<Integer> perm) {
        if(n == 0) return;
        if(n == 1) {
            perm.add(1);
            return;
        }
        if(n - 2 >= 63) {
            perm.add(1);
            recurse(n - 1, k, perm);
            return;
        }
        long prev = 0;
        long sum = pows[n - 2];
        int count = 0;
        while(k > sum) {
            if(n - 3 - count < 0) {
                break;
            }
            prev = sum;
            count++;
            sum += pows[n - 2 - count];
        }
        if(k > sum) {
            perm.add(n);
            return;
        }
        perm.add(count + 1);
        recurse(n - count - 1, k - prev, perm);
    }
    static void reverse(int[] a, int l, int r) {
        for(int i = l; i <= (l + r) / 2; i++) {
            int temp = a[i];
            a[i] = a[r - (i - l)];
            a[r - (i - l)] = temp;
        }
    }
}
