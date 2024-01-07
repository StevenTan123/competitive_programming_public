import java.util.*;
import java.io.*;

public class _1517_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[][] paths = new int[n][m];
            Triple[] sorted = new Triple[n * m];
            int counter = 0;
            for(int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                for(int j = 0; j < m; j++) {
                    paths[i][j] = Integer.parseInt(line.nextToken());
                    sorted[counter] = new Triple(paths[i][j], i, j);
                    counter++;
                }
            }
            Arrays.sort(sorted);
            boolean[][] used = new boolean[n][m];
            for(int i = 0; i < m; i++) {
                used[sorted[i].b][sorted[i].c] = true;
            }
            //res[i][j] stores length of the path runner j took from i - 1 to i
            int[][] res = new int[n][m];
            for(int i = 0; i < m; i++) {
                choose_path(sorted, i, res, paths, used);
            }
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < m; j++) {
                    sb.append(res[i][j]);
                    if(j < m - 1) sb.append(' ');
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
    static void choose_path(Triple[] sorted, int ind, int[][] res, int[][] paths, boolean[][] used) {
        for(int i = 0; i < paths.length; i++) {
            for(int j = 0; j < paths[i].length; j++) {
                if(sorted[ind].b == i && sorted[ind].c == j) {
                    res[i][ind] = paths[i][j];
                    break;
                }else {
                    if(used[i][j] || sorted[ind].b == i) continue;
                    res[i][ind] = paths[i][j];
                    used[i][j] = true;
                    break;
                }
            }
        }
    }
    static class Triple implements Comparable<Triple> {
        int a, b, c;
        Triple(int aa, int bb, int cc) {
            a = aa;
            b = bb;
            c = cc;
        }
        @Override
        public int compareTo(Triple o) {
            return a - o.a;
        }
    }
}
