import java.util.*;
import java.io.*;

public class procoD {
    public static void main(String[] args) throws IOException {
        int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] grid = new int[n][m];
        int[][] rsums = new int[n][m];
        int[][] csums = new int[n][m];
        for(int i = 0; i < n; i++) {
            String line2 = in.readLine();
            for(int j = 0; j < m; j++) {
                grid[i][j] = Character.getNumericValue(line2.charAt(j));
                rsums[i][j] = (j > 0 ? rsums[i][j - 1] : 0) + grid[i][j];
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                csums[j][i] = (j > 0 ? csums[j - 1][i] : 0) + grid[j][i];
            }
        }
        boolean[][][][] visited = new boolean[n][m][30][4];
        ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
        bfs.add(new BFS(0, 0, 0, 2, 0));
        int res = -1;
        while(bfs.size() > 0) {
            BFS cur = bfs.poll();
            visited[cur.r][cur.c][cur.s][cur.dir] = true;
            if(cur.r == n - 1 && cur.c == m - 1) {
                res = cur.d;
                break;
            }
            if(cur.s == 0) {
                for(int i = 0; i < 4; i++) {
                    if(!visited[cur.r][cur.c][cur.s][i]) {
                        visited[cur.r][cur.c][cur.s][i] = true;
                        bfs.add(new BFS(cur.r, cur.c, cur.s, i, cur.d + 1));
                    }
                }
            }
            int nr = cur.r + (cur.s + 1) * dirs[cur.dir][0];
            int nc = cur.c + (cur.s + 1) * dirs[cur.dir][1];
            if(legal(cur.r, cur.c, nr, nc, rsums, csums, n, m)) {
                if(!visited[nr][nc][cur.s + 1][cur.dir]) {
                    visited[nr][nc][cur.s + 1][cur.dir] = true;
                    bfs.add(new BFS(nr, nc, cur.s + 1, cur.dir, cur.d + 1));
                }
            }
            if(cur.s > 0) {
                nr = cur.r + (cur.s - 1) * dirs[cur.dir][0];
                nc = cur.c + (cur.s - 1) * dirs[cur.dir][1];
                if(legal(cur.r, cur.c, nr, nc, rsums, csums, n, m)) {
                    if(!visited[nr][nc][cur.s - 1][cur.dir]) {
                        visited[nr][nc][cur.s - 1][cur.dir] = true;
                        bfs.add(new BFS(nr, nc, cur.s - 1, cur.dir, cur.d + 1));
                    }
                }
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static class BFS {
        int r, c, s, dir, d;
        BFS(int rr, int cc, int ss, int ddir, int dd) {
            r = rr;
            c = cc;
            s = ss;
            dir = ddir;
            d = dd;
        }
    }
    static boolean legal(int r, int c, int nr, int nc, int[][] rsums, int[][] csums, int n, int m) {
        if(nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
        int maxr = Math.max(r, nr);
        int minr = Math.min(r, nr);
        int maxc = Math.max(c, nc);
        int minc = Math.min(c, nc);
        int sum = 0;
        if(maxr > minr) {
            sum = csums[maxr][maxc] - (minr > 0 ? csums[minr - 1][maxc] : 0);
        }else {
            sum = rsums[maxr][maxc] - (minc > 0 ? rsums[maxr][minc - 1] : 0);
        }
        return sum == 0;
    }
}
