import java.util.*;
import java.io.*;

public class traffic_control {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("traffic_control_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            String res = "Case #" + t + ": ";
            if(a < n + m - 1 || b < n + m - 1) {
                out.println(res + "Impossible");
            }else {
                out.println(res + "Possible");
                int[][] grid = new int[n][m];
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        if(j == 0 || j == m - 1 || i == n - 1) {
                            grid[i][j] = 1;
                        }else {
                            grid[i][j] = 1000;
                        }
                    }
                }
                grid[0][0] = a - (n + m - 2);
                grid[0][m - 1] = b - (n + m - 2);
                for(int i = 0; i < n; i++) {
                    StringBuilder sb = new StringBuilder();
                    for(int j = 0; j < m; j++) {
                        sb.append(grid[i][j]);
                        sb.append(' ');
                    }
                    out.println(sb.toString());
                }
            }
        }
        in.close();
        out.close();
    }
}
