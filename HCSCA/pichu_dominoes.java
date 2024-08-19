import java.util.*;
import java.io.*;

public class pichu_dominoes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());

        int num_rows = -1;
        for (int i = 0; i <= n; i += 2) {
            if (i * n / 2 > k) {
                break;
            }
            int left = k - i * n / 2;
            if (left % ((n - i) / 2) == 0) {
                num_rows = i;
                break;
            }
        }
        if (num_rows == -1) {
            out.println("NO");
        } else {
            out.println("YES");
            char[][] grid = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(grid[i], '.');
            }
            for (int i = 0; i < num_rows; i++) {
                for (int j = 0; j < n; j += 2) {
                    grid[i][j] = 'L';
                    grid[i][j + 1] = 'R';
                }
            }
            int left = k - num_rows * n / 2;
            int count = left / ((n - num_rows) / 2);
            for (int i = 0; i < count; i++) {
                for (int j = num_rows; j < n; j += 2) {
                    grid[j][i] = 'U';
                    grid[j + 1][i] = 'D';
                }
            }
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(grid[i][j]);
                }
                out.println(sb.toString());
            }
        }

        in.close();
        out.close();
    }
}
