import java.util.*;
import java.io.*;

public class _1705_E {
    static final int MAXLEN = 200100;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        int[] counts = new int[MAXLEN];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            counts[a[i]]++;
        }
        for(int i = 0; i < MAXLEN - 1; i++) {
            a[i + 1] += a[i] / 2;
            a[i] = a[i] % 2;
        }
        TreeSet<Range> ranges = new TreeSet<Range>();
        int l = -1;
        for(int i = 0; i < n; i++) {
            if(l == -1 && a[i] == 1) {
                l = i;
            }
            if(a[i] == 1 && (i == n - 1 || a[i + 1] == 0)) {
                ranges.add(new Range(l, i));
                l = -1;
            }
        }
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int ind = Integer.parseInt(line.nextToken()) - 1;
            int val = Integer.parseInt(line.nextToken());
            sub(ranges, a[ind]);
            add(ranges, val);
            a[ind] = val;
            out.println(ranges.last().r);
        }
        in.close();
        out.close();
    }
    static void sub(TreeSet<Range> ranges, int val) {
        Range higher = ranges.higher(new Range(val - 1, val - 1));
    }
    static void add(TreeSet<Range> ranges, int val) {
        Range higher = ranges.higher(new Range(val - 1, val - 1));
        if(val >= higher.l) {

        }else if(val == higher.l - 1) {
            
        }else {

        }
    }
    static class Range implements Comparable<Range> {
        int l, r;
        Range(int ll, int rr) {
            l = ll;
            r = rr;
        }
        @Override
        public int compareTo(Range o) {
            return r - o.r;
        }
    }
}
