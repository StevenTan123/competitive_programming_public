import java.util.*;
import java.io.*;

public class spiraling {
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[][] a = new int[n][n];
            int r = 0;
            int c = 0;
            int dir = 0;
            int count = 1;
            boolean[][][] dp = new boolean[n][n][n * n];
            int[][][][] prev = new int[n][n][n * n][2];
            dp[0][0][0] = true;
            while(count <= n * n) {
                a[r][c] = count;
                for(int i = 0; i < 4; i++) {
                    int prevr = r + dirs[i][0];
                    int prevc = c + dirs[i][1];
                    if(in_bound(a, prevr, prevc) && a[prevr][prevc] > 0) {
                        for(int j = 1; j < count; j++) {
                            if(dp[prevr][prevc][j - 1]) {
                                dp[r][c][j] = true;
                                prev[r][c][j] = new int[] {prevr, prevc};
                            }
                        }
                    }
                }
                if(in_bound(a, r + dirs[dir][0], c + dirs[dir][1]) && a[r + dirs[dir][0]][c + dirs[dir][1]] == 0) {
                    r += dirs[dir][0];
                    c += dirs[dir][1];
                }else {
                    dir = (dir + 1) % 4;
                    r += dirs[dir][0];
                    c += dirs[dir][1];
                }
                count++;
            }
            int half = (n - 1) / 2;
            String res = "Case #" + t + ": ";
            if(dp[half][half][k]) {
                int rr = half;
                int cc = half;
                int val = k;
                int ans = 0;
                ArrayList<int[]> pairs = new ArrayList<int[]>();
                while(rr > 0 || cc > 0) {
                    int[] next = prev[rr][cc][val];
                    if(a[rr][cc] > a[next[0]][next[1]] + 1) {
                        ans++;
                        pairs.add(new int[] {a[next[0]][next[1]], a[rr][cc]});
                    }
                    rr = next[0];
                    cc = next[1];
                    val--;
                }
                out.println(res + ans);
                for(int i = pairs.size() - 1; i >= 0; i--) {
                    out.println(pairs.get(i)[0] + " " + pairs.get(i)[1]);
                }
            }else {
                out.println(res + "IMPOSSIBLE");
            }
        }
        in.close();
        out.close();
    }
    static boolean in_bound(int[][] a, int r, int c) {
        if(r < 0 || r >= a.length || c < 0 || c >= a.length) {
            return false;
        }
        return true;
    }
}
