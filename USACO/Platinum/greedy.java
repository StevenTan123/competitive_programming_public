import java.util.*;
import java.io.*;

public class greedy {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("greedy.in"));
        PrintWriter out = new PrintWriter("greedy.out");
        int n = Integer.parseInt(in.readLine());
        int[][] c = new int[n][2];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            c[i][0] = Integer.parseInt(line.nextToken());
            c[i][1] = i;
        }
        Arrays.sort(c, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        int res = 0;
        BIT bit = new BIT(new int[n]);
        for(int i = 0; i < n; i++) {
            bit.update(c[i][1], 1);
            int k = n - c[i][0];
            int ind = bsearch(bit, n, k);
            if(ind > -1) {
                int curres = n - ind - 1;
                res = Math.max(res, curres);
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static int bsearch(BIT bit, int n, int val) {
        int res = -1;
        int l = 0;
        int r = n - 1;
        while(l <= r) {
            int m = (l + r) / 2;
            int mval = bit.psum(m);
            if(mval >= val) {
                res = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        return res;
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
