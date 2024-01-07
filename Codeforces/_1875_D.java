import java.util.*;
import java.io.*;

public class _1875_D {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int[] freq = new int[5005];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if (a[i] < freq.length) {
                    freq[a[i]]++;
                }
            }
            for (int i = 0; i < n; i++) {
                int ind = rand.nextInt(n);
                int temp = a[i];
                a[i] = a[ind];
                a[ind] = temp;
            }
            Arrays.sort(a);

            int mex = 0;
            for (int i = 0; i < n; i++) {
                if (mex == a[i]) {
                    mex++;
                }
            }

            int[] dp = new int[mex + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[mex] = 0;
            for (int i = mex - 1; i >= 0; i--) {
                for (int j = mex; j > i; j--) {
                    dp[i] = Math.min(dp[i], dp[j] + j * (freq[i] - 1) + i);
                }
            }
            out.println(dp[0]);
        }
        
        in.close();
        out.close();
    }
}
