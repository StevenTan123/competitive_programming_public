import java.util.*;
import java.io.*;

public class _1535_D {
    public static void main(String[] args) throws IOException {
        int[] pow = new int[20];
        pow[0] = 1;
        for(int i = 1; i < 20; i++) {
            pow[i] = pow[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int k = Integer.parseInt(in.readLine());
        int n = pow[k];
        int[] par = new int[n];
        int[][] children = new int[n][2];
        int next = 0;
        for(int i = k - 1; i > 0; i--) {
            int cur = next;
            next += pow[i];
            int count = 1;
            for(int j = 1; j <= pow[i]; j += 2) {
                par[cur + j] = next + count;
                par[cur + j + 1] = next + count;
                children[next + count][0] = cur + j;
                children[next + count][1] = cur + j + 1;
                count++; 
            }
        }
        int[] type = new int[n];
        String s = in.readLine();
        for(int i = 1; i < n; i++) {
            char c = s.charAt(i - 1);
            if(c == '0') {
                type[i] = 1;
            }else if(c == '1') {
                type[i] = 2;
            }
        }
        int[] dp = new int[n];
        for(int i = 1; i < n; i++) {
            if(i <= pow[k - 1]) {
                if(type[i] == 0) {
                    dp[i] = 2;
                }else {
                    dp[i] = 1;
                }
            }else {
                if(type[i] != 2) {
                    dp[i] += dp[children[i][0]];
                }
                if(type[i] != 1) {
                    dp[i] += dp[children[i][1]];
                }
            }
        }
        int q = Integer.parseInt(in.readLine());
        for(int i = 0; i < q; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int node = Integer.parseInt(line.nextToken());
            char c = line.nextToken().charAt(0);
            int val = 0;
            if(c == '0') {
                val = 1;
            }else if(c == '1') {
                val = 2;
            }
            type[node] = val;
            while(node > 0) {
                dp[node] = 0;
                if(node <= pow[k - 1]) {
                    if(type[node] == 0) {
                        dp[node] = 2;
                    }else {
                        dp[node] = 1;
                    }
                }else {
                    if(type[node] != 2) {
                        dp[node] += dp[children[node][0]];
                    }
                    if(type[node] != 1) {
                        dp[node] += dp[children[node][1]];
                    }
                }
                node = par[node];
            }
            out.println(dp[n - 1]);
        }
        in.close();
        out.close();
    }
}
