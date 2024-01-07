import java.util.*;
import java.io.*;

public class _1795_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] h = new int[n];
            int[] rev = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            long sum = 0;
            for (int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(line.nextToken());
                rev[n - i - 1] = h[i];
                sum += h[i];
            }
            long[] dp1 = dp(h);
            long[] dp2 = dp(rev);
            long ans = sum;
            for (int i = 0; i < n; i++) {
                long cur = sum - dp1[i] - dp2[n - i - 1] + 2 * h[i];
                ans = Math.min(ans, cur);
            }
            out.println(ans);
        }
        in.close();
        out.close();
    }
    static long[] dp(int[] a) {
        long[] dp = new long[a.length];
        Stack<State> st = new Stack<State>();
        st.push(new State(-1, Integer.MIN_VALUE));
        for (int i = 0; i < a.length; i++) {
            while (st.size() > 0) {
                State prev = st.peek();
                if (i - prev.ind >= a[i]) {
                    dp[i] += (long)(a[i] + 1) * a[i] / 2;
                    break;
                } else if (prev.val > a[i] - i) {
                    st.pop();
                } else {
                    dp[i] += prev.ind >= 0 ? dp[prev.ind] : 0;
                    dp[i] += (2 * (long)a[i] - (i - prev.ind - 1)) * (i - prev.ind) / 2;
                    break;
                }
            }
            st.push(new State(i, a[i] - i));
        }        
        return dp;
    }
    static class State {
        int ind, val;
        State(int i, int v) {
            ind = i;
            val = v;
        }
    }
}
