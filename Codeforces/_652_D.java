import java.util.*;
import java.io.*;

public class _652_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        Triple[] segs = new Triple[2 * n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            segs[i * 2] = new Triple(Integer.parseInt(line.nextToken()), 0, i);
            segs[i * 2 + 1] = new Triple(Integer.parseInt(line.nextToken()), 1, i);
            segs[i * 2 + 1].prev = segs[i * 2];
        }
        Arrays.sort(segs);
        int[] pre = new int[2 * n];
        int c = 0;
        for(int i = 0; i < 2 * n; i++) {
            segs[i].ind = i;
            if(segs[i].b == 0) {
                c++;
            }
            pre[i] = c;
        }
        TreeSet<Triple> active = new TreeSet<Triple>();
        int[] res = new int[n];
        BIT bit = new BIT(new int[2 * n]);
        for(int i = 0; i < 2 * n; i++) {
            if(segs[i].b == 0) {
                bit.update(i, 1);
                active.add(segs[i]);
            }else {
                bit.update(segs[i].prev.ind, -1);
                active.remove(segs[i].prev);
                res[segs[i].c] = pre[i] - pre[segs[i].prev.ind] - bit.sum(segs[i].prev.ind, i);
            }
        }
        for(int i = 0; i < n; i++) {
            out.println(res[i]);
        }
        in.close();
        out.close();
    }
    static class Triple implements Comparable<Triple> {
        int a, b, c, ind;
        Triple prev;
        Triple(int aa, int bb, int cc) {
            a = aa;
            b = bb;
            c = cc;
        }
        @Override
        public int compareTo(Triple o) {
            return a - o.a;
        }
    }
    static class BIT {
        int[] bit;

        BIT(int[] a) {
            bit = new int[a.length + 1];
            for (int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }

        void update(int index, int add) {
            index++;
            while (index < bit.length) {
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
            while (index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}