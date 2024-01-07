import java.util.*;
import java.io.*;

public class timedry {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        int seg_count = 0;
        int[] seg = new int[n];
        ArrayDeque<Segment> stack = new ArrayDeque<Segment>();
        for(int i = 0; i < n; i++) {
            Segment top = stack.peek();
            while(top != null && a[i] < top.val) {
                stack.pop();
                top = stack.peek();
            }
            if(top != null && a[i] == top.val) {
                seg[i] = top.id;
            }else {
                seg[i] = seg_count;
                stack.push(new Segment(a[i], seg_count));
                seg_count++;
            }
        }
        int[][] sorted = new int[n][2];
        int[] occ = new int[n];
        Arrays.fill(occ, Integer.MAX_VALUE);
        for(int i = n - 1; i >= 0; i--) {
            sorted[i][0] = occ[seg[i]];
            sorted[i][1] = i;
            occ[seg[i]] = i;
        }
        Arrays.sort(sorted, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int[][] qs = new int[q][3];
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            qs[i][0] = Integer.parseInt(line.nextToken()) - 1;
            qs[i][1] = Integer.parseInt(line.nextToken()) - 1;
            qs[i][2] = i;
        }
        Arrays.sort(qs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });
        int p = n - 1;
        BIT bit = new BIT(new int[n]);
        int[] res = new int[q];
        for(int i = 0; i < q; i++) {
            int lower = qs[i][1];
            while(p >= 0 && sorted[p][0] > lower) {
                bit.update(sorted[p][1], 1);
                p--;
            }
            res[qs[i][2]] = bit.sum(qs[i][0], qs[i][1]);
        }
        for(int i = 0; i < q; i++) {
            out.println(res[i]);
        }
        in.close();
        out.close();
    }
    static class Segment {
        int val, id;
        Segment(int v, int i) {
            val = v;
            id = i;
        }
    }
    static class BIT {
        int[] bit;
        BIT(int[] a) {
            bit = new int[a.length + 1];
            for(int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }
        void update(int index, int add) {
            index++;
            while(index < bit.length) {
                bit[index] += add;
                index += index & -index;
            }
        }
        int sum(int l, int r) {
            return psum(r) - (l == 0 ? 0 : psum(l - 1));
        }
        int psum(int index) {
            index++;
            int res = 0;
            while(index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
