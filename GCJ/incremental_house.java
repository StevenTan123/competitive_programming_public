import java.util.*;
import java.io.*;

public class incremental_house {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long[][] piles = {{Long.parseLong(line.nextToken()), 0}, {Long.parseLong(line.nextToken()), 1}};
            if(piles[0][0] < piles[1][0]) swap(piles, 0, 1);
            long[] gap = bsearch(1, 1, piles[0][0] - piles[1][0]);
            piles[0][0] -= gap[1];
            if(piles[0][0] == piles[1][0] && piles[0][1] == 1) {
                swap(piles, 0, 1);
            }
            long[] maxn = bsearch(gap[0] + 1, 2, piles[0][0]);
            long[] minn = bsearch(gap[0] + 2, 2, piles[1][0]);
            long i;
            if(maxn[2] < minn[2]) {
                piles[0][0] -= maxn[1];
                long sub = ((gap[0] + 2) * 2 + 2 * (maxn[0] - 1)) * maxn[0] / 2;
                piles[1][0] -= sub;
                i = maxn[2] + 1;
            }else {
                piles[1][0] -= minn[1];
                long sub = ((gap[0] + 1) * 2 + 2 * minn[0]) * (minn[0] + 1) / 2;
                piles[0][0] -= sub;
                i = minn[2] + 1;
            }
            long[] leftmax = bsearch(i + 1, 1, piles[0][0]);
            long[] leftmin = bsearch(i + 1, 1, piles[1][0]);
            piles[0][0] -= leftmax[1];
            piles[1][0] -= leftmin[1];
            long n = Math.max(leftmax[2], leftmin[2]);
            String res = "Case #" + t + ": ";
            if(piles[0][1] == 1) swap(piles, 0, 1);
            out.println(res + n + " " + piles[0][0] + " " + piles[1][0]);
        }
        in.close();
        out.close();
    }

    //returns largest n such that a + (a + d) + (a + 2d) + ... + (a + (n - 1)d) <=
    //goal
    static long[] bsearch(long a, long d, long goal) {
        long l = 0;
        long r = 2000000000;
        long[] res = new long[3];
        while(l <= r) {
            long avg = (l + r) / 2;
            long sum = (2 * a + (avg - 1) * d) * avg / 2;
            if(sum <= goal) {
                res[0] = avg;
                res[1] = sum;
                res[2] = a + (avg - 1) * d;
                l = avg + 1;
            }else {
                r = avg - 1;
            }
        }
        return res;
    }
    static void swap(long[][] a, int i, int j) {
        long[] temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}