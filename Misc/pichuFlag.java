import java.util.*;
import java.io.*;

public class pichuFlag {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] flag = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                flag[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        boolean good = true;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int i2 = n - i - 1;
                int j2 = n - j - 1;
                if(!(flag[i][j] == flag[i2][j] &&
                    flag[i][j] == flag[i][j2] && 
                    flag[i][j] == flag[i2][j2] && 
                    flag[i][j] == flag[j][i] &&
                    flag[i][j] == flag[j2][i] &&
                    flag[i][j] == flag[j][i2] && 
                    flag[i][j] == flag[j2][i2])) {
                    good = false;
                }
            }
        }
        out.println(good ? "YES" : "NO");
        in.close();
        out.close();
    }
}
