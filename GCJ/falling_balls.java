import java.util.*;
import java.io.*;

public class falling_balls {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int c = Integer.parseInt(in.readLine());
            int[] b = new int[c];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < c; i++) {
                b[i] = Integer.parseInt(line.nextToken());
            }
            String res = "Case #" + t + ": ";
            if(b[0] == 0 || b[c - 1] == 0) {
                out.println(res + "IMPOSSIBLE");
                continue;
            }
            int[][] ans = new int[c][c];
            int p = 0;
            int max = 1;
            for(int i = 0; i < c; i++) {
                if(b[i] >= 1) max = Math.max(max, fill(ans, p, p + b[i] - 1, i));
                p += b[i];
            }
            int ind = c - max;
            out.println(res + max);
            for(int i = ind; i < c; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < c; j++) {
                    if(ans[i][j] == 0) {
                        sb.append('.');
                    }else if(ans[i][j] == 1) {
                        sb.append('\\');
                    }else {
                        sb.append('/');
                    }
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
    static int fill(int[][] ans, int l, int r, int m) {
        int p = ans.length - 2;
        for(int i = m - 1; i >= l; i--) {
            ans[p][i] = 1;
            p--;
        }
        p = ans.length - 2;
        for(int i = m + 1; i <= r; i++) {
            ans[p][i] = 2;
            p--;
        }
        int max = Math.max(m - l + 1, r - m + 1);
        return max;
    }
}
