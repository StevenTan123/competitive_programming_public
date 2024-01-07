import java.util.*;
import java.io.*;

public class _1869_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[][] mat = new int[n][m];
            for (int i = 0; i < m; i++) {
                mat[0][i] = (i + 1) % m;
            }
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i >= m - 1) {
                        mat[i][j] = mat[i - 1][j];
                    } else {
                        if (j == m - 1) {
                            mat[i][j] = mat[i - 1][0];
                        } else {
                            mat[i][j] = mat[i - 1][j + 1];
                        }
                    }
                }
            }
            if (m == 1) {
                out.println(0);
            } else if (m <= n) {
                out.println(m);
            } else {
                out.println(n + 1);
            }
            for (int i = 0; i < n; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    row.append(mat[i][j]);
                    row.append(' ');
                }
                out.println(row.toString());
            }
        }
        in.close();
        out.close();
    }
}
