import java.util.*;
import java.io.*;

public class _1654_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        int max = 0;
        int range = 1000000 * 320;
        int[] freqs = new int[range * 2 + 1];
        for(int i = -319; i < 320; i++) {
            for(int j = 0; j < a.length; j++) {
                int key = a[j] - j * i + range;
                freqs[key]++;
                max = Math.max(max, freqs[key]);
            }
            for(int j = 0; j < a.length; j++) {
                int key = a[j] - j * i + range;
                freqs[key]--;
            }
        }
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for(int i = 0; i < n; i++) {
            dp[i] = new HashMap<Integer, Integer>();
        }
        for(int i = 0; i < n; i++) {
            for(int j = i - 1; j >= Math.max(0, i - 320); j--) {
                if((a[i] - a[j]) % (i - j) == 0) {
                    int d = (a[i] - a[j]) / (i - j);
                    if(dp[i].containsKey(d)) {
                        continue;
                    }
                    Integer prev = dp[j].get(d);
                    if(prev == null) {
                        prev = 1;
                    }
                    dp[i].put(d, prev + 1);
                    max = Math.max(max, prev + 1);
                }
            }
        }
        out.println(n - max);
        in.close();
        out.close();
    }
}