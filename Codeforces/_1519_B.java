import java.util.*;
import java.io.*;

public class _1519_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(i > 0) {
                        grid[i][j] = grid[i - 1][j] + j + 1;
                    }
                    if(j > 0) {
                        grid[i][j] = grid[i][j - 1] + i + 1;
                    }
                }
            }
            if(grid[n - 1][m - 1] == k) {
                out.println("YES");
            }else {
                out.println("NO");
            }
		}
		in.close();
		out.close();
	}
}
