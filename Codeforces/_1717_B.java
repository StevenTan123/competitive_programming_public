import java.util.*;
import java.io.*;

public class _1717_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int r = Integer.parseInt(line.nextToken()) - 1;
            int c = Integer.parseInt(line.nextToken()) - 1;
            int[][] grid = new int[n][n];
            for(int i = 0; i < n; i += k) {
                int curc = (c + i) % n;
                grid[r][curc] = 1;

                int curr = r;
                for(int j = 0; j < n; j++) {
                    curc = (curc + 1) % n;
                    curr = (curr - 1 + n) % n;
                    grid[curr][curc] = 1;
                }
            }
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n; j++) {
                    sb.append(grid[i][j] == 0 ? '.' : 'X');
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
