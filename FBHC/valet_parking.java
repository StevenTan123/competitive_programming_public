import java.util.*;
import java.io.*;

public class valet_parking {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("valet_parking_chapter_1_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken()) - 1;
            int[][] grid = new int[r][c];
            int[][] psum = new int[r][c];
            for(int i = 0; i < r; i++) {
                String line2 = in.readLine();
                for(int j = 0; j < c; j++) {
                    if(line2.charAt(j) == 'X') {
                        grid[i][j] = 1;
                    }
                    if(i == 0) {
                        psum[i][j] = grid[i][j];
                    }else {
                        psum[i][j] = psum[i - 1][j] + grid[i][j];
                    }
                }
            }
            int ans = Integer.MAX_VALUE;
            for(int i = 0; i < r; i++) {
                int count = 0;
                for(int j = 0; j < c; j++) {
                    int l = Math.max(0, k - i);
                    int sum = sum(j, l, r - 1, psum);
                    if(sum >= r - k) {
                        count++;
                    }else if(k - i >= 0 && grid[k - i][j] == 1) {
                        count++;
                    }
                }
                ans = Math.min(ans, count + Math.abs(i));
            }
            for(int i = 0; i < r; i++) {
                int count = 0;
                for(int j = 0; j < c; j++) {
                    int rbound = Math.min(r - 1, k + i);
                    int sum = sum(j, 0, rbound, psum);
                    if(sum >= k + 1) {
                        count++;
                    }else if(k + i < r && grid[k + i][j] == 1) {
                        count++;
                    }
                }
                ans = Math.min(ans, count + Math.abs(i));
            }
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
    static int sum(int c, int l, int r, int[][] psum) {
        return psum[r][c] - (l > 0 ? psum[l - 1][c] : 0);
    }
}
