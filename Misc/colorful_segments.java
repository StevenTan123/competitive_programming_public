import java.util.*;
import java.io.*;

public class colorful_segments {
    static long MOD = 998244353;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int T = Integer.parseInt(in.readLine());
		while (T-- > 0) {
            int n = Integer.parseInt(in.readLine());
            Seg[] segs = new Seg[n];
            int[] coords = new int[2 * n];
            for (int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                segs[i] = new Seg(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
                coords[i * 2] = segs[i].l;
                coords[i * 2 + 1] = segs[i].r;
            }
            Arrays.sort(coords);
            HashMap<Integer, Integer> compress = new HashMap<Integer, Integer>();
            int count = -1;
            for (int i = 0; i < 2 * n; i++) {
                if (i == 0 || coords[i] > coords[i - 1]) {
                    count++;
                    compress.put(coords[i], count);
                }
            }
            for (int i = 0; i < n; i++) {
                segs[i].l = compress.get(segs[i].l);
                segs[i].r = compress.get(segs[i].r);
            }
            Arrays.sort(segs);

            long[][] dp = new long[n][2];
            long[][] pre = new long[n][2];
            BIT[] bits = new BIT[] { new BIT(count + 1), new BIT(count + 1) };
        
            int[] prev_inds = new int[] {-1, -1};
            long res = 1;
            for (int i = 0; i < n; i++) {
                int t = segs[i].t;
                int o = 1 - segs[i].t;
                dp[i][t] = 1;
                if (prev_inds[t] > -1) {
                    dp[i][t] = (dp[i][t] + pre[prev_inds[t]][t]) % MOD;
                }
                if (segs[i].l > 0) {
                    dp[i][t] = (dp[i][t] + bits[o].psum(segs[i].l - 1)) % MOD;
                }
                
                bits[t].update(segs[i].r, dp[i][t]);
                pre[i][0] = ((i > 0 ? pre[i - 1][0] : 0) + dp[i][0]) % MOD;
                pre[i][1] = ((i > 0 ? pre[i - 1][1] : 0) + dp[i][1]) % MOD;

                prev_inds[t] = i;
                res = (res + dp[i][t]) % MOD;
            }
            out.println(res);
		}
		
        in.close();
		out.close();
	}
    static class Seg implements Comparable<Seg> {
        int l, r, t;
        Seg(int ll, int rr, int tt) {
            l = ll;
            r = rr;
            t = tt;
        }
        @Override
        public int compareTo(Seg o) {
            if (l == o.l) {
                return r - o.r;
            }
            return l - o.l;
        }
    }
    static class BIT {
        long[] bit;
        BIT(int len) {
            bit = new long[len + 1];
        }
        void update(int index, long add) {
            index++;
            while(index < bit.length) {
                bit[index] = (bit[index] + add) % MOD;
                index += index & -index;
            }
        }
        long psum(int index) {
            index++;
            long res = 0;
            while(index > 0) {
                res = (res + bit[index]) % MOD;
                index -= index & -index;
            }
            return res;
        }
    }
}
