import java.util.*;
import java.io.*;

public class counting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int q = Integer.parseInt(in.readLine());
        for(int i = 0; i < q; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long d = Long.parseLong(line.nextToken());
            long x = Long.parseLong(line.nextToken());
            long y = Long.parseLong(line.nextToken());
            long max = Math.max(x, y);
            long min = Math.min(x, y);
            long[] counts = new long[40];
            long[] counts2 = new long[40];
            Arrays.fill(counts, -1);
            Arrays.fill(counts2, -1);
            long total = count2(min + d, max - min, counts);
            long prefix = count2(min - 1, max - min, counts2);
            out.println(total - prefix);
        }
        in.close();
        out.close();
    }
    static long count2(long d, long dif, long[] counts) {
        if(d < 0) return 0;
        long exp = 1;
        int log = 0;
        while(exp - 1 < d + dif) {
            exp *= 3;
            log++;
        }
        if(log == 0) return 1 - dif;
        long smaller = exp / 3;
        long res = 0;
        if(dif < smaller) {
            res += count(log - 1, dif, counts);
            if(d >= smaller * 2) {
                res += count(log - 1, dif, counts);
                long d2 = d - smaller * 2;
                if(d2 >= smaller - dif - 1) res += count(log - 1, dif, counts);
                else res += count2(d2, dif, counts);
            }else if(d >= smaller) {
                long d2 = d - smaller;
                if(d2 >= smaller - dif - 1) res += count(log - 1, dif, counts);
                else res += count2(d2, dif, counts);
            }
        }else if(dif < smaller * 2) {
            long dist = smaller * 2 - dif;
            long d2 = d - dist;
            if(d2 >= 0)  {
                if(d2 >= smaller - dist - 1) res += count(log - 1, dist, counts);
                else res += count2(d2, dist, counts);
            }
        }else {
            if(d >= smaller * 3 - dif - 1) res += count(log - 1, dif - smaller * 2, counts);
            else res += count2(d, dif - smaller * 2, counts);
        }
        return res;
    }
    static long count(int k, long dif, long[] counts) {
        if(k == 0) {
            counts[k] = 1 - dif;
            return counts[k];
        }
        if(counts[k] > -1) return counts[k];
        long smaller = 1;
        for(int i = 0; i < k - 1; i++) {
            smaller *= 3;
        }
        if(dif < smaller) {
            counts[k] = count(k - 1, dif, counts) * 3;
            return counts[k];
        }
        counts[k] = count(k - 1, Math.abs(smaller * 2 - dif), counts);
        return counts[k];
    }
}
