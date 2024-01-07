import java.util.*;
import java.io.*;

public class _1866_I {
    static final int MAXD = 200005;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());
        int K = Integer.parseInt(line.nextToken());
        int[] max_row = new int[MAXD];
        int[] max_col = new int[MAXD];
        for (int i = 0; i < K; i++) {
            line = new StringTokenizer(in.readLine());
            int X = Integer.parseInt(line.nextToken());
            int Y = Integer.parseInt(line.nextToken());
            max_row[Y] = Math.max(max_row[Y], X);
            max_col[X] = Math.max(max_col[X], Y);
        }

        int r = M;
        int c = N;
        int res = 0;
        while (r >= 1 || c >= 1) {
            if (max_row[r] >= c) {
                if (r == 1) {
                    res = 1;
                }
                r--;
            } else if (max_col[c] >= r) {
                if (c == 1) {
                    res = 1;
                }
                c--;
            } else {
                if (r == 1 ^ c == 1) {
                    res = 1;
                }
                r--;
                c--;
            }
        }
        out.println(res == 1 ? "Chaneka" : "Bhinneka");

        in.close();
        out.close();
    }
}
