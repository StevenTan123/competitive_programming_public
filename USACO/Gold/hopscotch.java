import java.util.*;
import java.io.*;

public class hopscotch {
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter out = new PrintWriter("hopscotch.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int r = Integer.parseInt(line.nextToken());
        int c = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[][] grid = new int[r][c];
        for(int i = 0; i < r; i++) {
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < c; j++) {
                grid[i][j] = Integer.parseInt(line.nextToken()) - 1;
            }
        }
        ArrayList<Integer>[] cols = new ArrayList[k];
        for(int j = 0; j < c; j++) {
            for(int i = 0; i < r; i++) {
                ArrayList<Integer> cur_cols = cols[grid[i][j]];
                if(cur_cols == null) {
                    cols[grid[i][j]] = new ArrayList<Integer>();
                    cols[grid[i][j]].add(j);
                }else if(cur_cols.get(cur_cols.size() - 1) != j) {
                    cols[grid[i][j]].add(j);
                }
            }
        }
        BIT[] bits = new BIT[k];
        for(int i = 0; i < k; i++) {
            if(cols[i] != null) {
                bits[i] = new BIT(new long[cols[i].size()]);
            }
        }
        BIT total = new BIT(new long[c]);
        long[][] dp = new long[r][c];
        dp[r - 1][c - 1] = 1;
        total.update(c - 1, 1);
        bits[grid[r - 1][c - 1]].update(cols[grid[r - 1][c - 1]].size() - 1, 1);
        for(int i = r - 1; i > 0; i--) {
            for(int j = c - 1; j > 0; j--) {
                if(i != r - 1) {
                    total.update(j, dp[i][j]);
                    bits[grid[i][j]].update(find_ind(cols[grid[i][j]], j), dp[i][j]);
                }
                int ind = find_ind(cols[grid[i - 1][j - 1]], j - 1);
                dp[i - 1][j - 1] = modadd(total.ssum(j), -bits[grid[i - 1][j - 1]].ssum(ind + 1));
            }
        }
        out.println(dp[0][0]);
        in.close();
        out.close();
    }
    static class BIT {
        long[] bit;
        BIT(long[] a) {
            bit = new long[a.length + 1];
            for (int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }
        void update(int index, long add) {
            index++;
            while(index < bit.length) {
                bit[index] = modadd(bit[index], add);
                index += index & -index;
            }
        }
        long ssum(int index) {
            if(index > bit.length - 2) {
                return 0;
            }
            return modadd(psum(bit.length - 2), -(index == 0 ? 0 : psum(index - 1)));
        }
        long psum(int index) {
            index++;
            long res = 0;
            while(index > 0) {
                res = modadd(res, bit[index]);
                index -= index & -index;
            }
            return res;
        }
    }
    static int find_ind(ArrayList<Integer> cols, int col) {
        int l = 0;
        int r = cols.size() - 1;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(cols.get(m) <= col) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return res;
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
}
