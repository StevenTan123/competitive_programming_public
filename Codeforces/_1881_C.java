import java.util.*;
import java.io.*;

public class _1881_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[][] mat = new int[n][n];
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                for (int j = 0; j < n; j++) {
                    mat[i][j] = (int) (line.charAt(j) - 97);
                }
            }

            long res = 0;
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    ArrayList<Integer> vals = new ArrayList<Integer>();
                    int r = i;
                    int c = j;
                    for (int k = 0; k < 4; k++) {
                        vals.add(mat[r][c]);
                        int temp = r;
                        r = c;
                        c = n - temp - 1;
                    }
                    Collections.sort(vals);
                    for (int k = 0; k < 3; k++) {
                        res += vals.get(3) - vals.get(k);
                    }
                }
            }
            out.println(res);
        }
        
        in.close();
        out.close();
    }
}
