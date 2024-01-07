import java.util.*;
import java.io.*;

public class _1615_B {
    static final int MAXN = 200005;
    static final int MAXL = 20;
    public static void main(String[] args) throws IOException {
        int[][] pre = new int[MAXN][MAXL];
        for(int i = 0; i < MAXN; i++) {
            int val = i;
            for(int j = 0; j < MAXL; j++) {
                pre[i][j] = (i > 0 ? pre[i - 1][j] : 0) + (val + 1) % 2;
                val /= 2;
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(line.nextToken());
            int r = Integer.parseInt(line.nextToken());
            int min = r - l + 1;
            for(int i = 0; i < MAXL; i++) {
                int cur = pre[r][i] - pre[l - 1][i];
                min = Math.min(min, cur);
            }
            out.println(min);
        }
        in.close();
        out.close();
    }
}
