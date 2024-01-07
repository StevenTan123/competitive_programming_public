import java.util.*;
import java.io.*;

public class _1530_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int h = Integer.parseInt(line.nextToken());
            int w = Integer.parseInt(line.nextToken());
            int[][] res = new int[h][w];
            res[0][0] = 1;
            int dist = 1;
            for(int i = 1; i < 2 * h + 2 * w - 4; i++) {
                int r = 0;
                int c = 0;
                if(i <= w - 1) {
                    r = 0;
                    c = i;
                }else if(i <= w + h - 2) {
                    r = i - (w - 1);
                    c = w - 1;
                }else if(i <= 2 * w + h - 3) {
                    r = h - 1;
                    c = w - (i - (w + h - 2)) - 1;
                }else {
                    r = h - (i - (2 * w + h - 3)) - 1;
                    c = 0;
                }
                if(dist >= 2 && i < 2 * h + 2 * w - 5) {
                    res[r][c] = 1;
                    dist = 0;
                }
                if(i == w - 1 || i == w + h - 2 || i == 2 * w + h - 3) {
                    if(dist == 0) {
                        dist++;
                    }
                }else {
                    dist++;
                }
            }
            for(int i = 0; i < h; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < w; j++) {
                    sb.append(res[i][j]);
                }
                out.println(sb.toString());
            }
            out.println();
        }
        in.close();
        out.close();
    }
}
