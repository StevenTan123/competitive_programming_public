import java.util.*;
import java.io.*;

public class robot {
    static int[] pow2;
    public static void main(String[] args) throws IOException {
        pow2 = new int[25];
        pow2[0] = 1;
        for(int i = 1; i < 25; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] target = new int[]{Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
        int half = n / 2;
        int[][] lower = new int[half][2];
        int[][] upper = new int[n - half][2];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            if(i < half) {
                lower[i] = new int[]{Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
            }else {
                upper[i - half] = new int[]{Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
            }
        }
        ArrayList<Pair>[] sortl = construct(lower);
        ArrayList<Pair>[] sortr = construct(upper);
        long[] res = new long[n + 1];
        for(int i = 0; i <= half; i++) {
            for(int j = 0; j <= n - half; j++) {
                ArrayList<Pair> l = sortl[i];
                ArrayList<Pair> r = sortr[j];
                int p = r.size() - 1;
                for(int k = 0; k < l.size(); k++) {
                    Pair cur = l.get(k);
                    while(p >= 0 && (r.get(p).a + cur.a > target[0] || (r.get(p).a + cur.a == target[0] && r.get(p).b + cur.b > target[1]))) {
                        p--;
                    }
                    if(p >= 0 && cur.a + r.get(p).a == target[0] && cur.b + r.get(p).b == target[1]) {
                        res[i + j] += (long)cur.c * r.get(p).c;
                    }
                }
            }
        }
        for(int i = 1; i <= n; i++) {
            out.println(res[i]);
        }
        in.close();
        out.close();
    }
    static ArrayList<Pair>[] construct(int[][] pairs) {
        int n = pairs.length;
        ArrayList<Pair>[] res = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            res[i] = new ArrayList<Pair>();
        }
        for(int i = 0; i < pow2[n]; i++) {
            long xsum = 0;
            long ysum = 0;
            int count = 0;
            int val = i;
            for(int j = 0; j < n; j++) {
                if(val % 2 == 1) {
                    xsum += pairs[j][0];
                    ysum += pairs[j][1];
                    count++;
                }
                val /= 2;
            }
            res[count].add(new Pair(xsum, ysum));
        }
        for(int i = 0; i <= n; i++) {
            ArrayList<Pair> cur = res[i];
            ArrayList<Pair> next = new ArrayList<Pair>();
            Collections.sort(cur);
            int prev = 0;
            for(int j = 1; j <= cur.size(); j++) {
                if(j == cur.size() || cur.get(j).compareTo(cur.get(prev)) != 0) {
                    Pair add = cur.get(prev);
                    add.c = j - prev;
                    next.add(add);
                    prev = j;
                }
            }
            res[i] = next;
        }
        return res;
    }
    static class Pair implements Comparable<Pair> {
        long a, b;
        int c;
        Pair(long aa, long bb) {
            a = aa;
            b = bb;
        }
        @Override
        public int compareTo(Pair o) {
            if(a == o.a) {
                return Long.compare(b, o.b);
            }
            return Long.compare(a, o.a);
        }
    }
}
