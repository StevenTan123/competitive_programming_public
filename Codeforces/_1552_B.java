import java.util.*;
import java.io.*;

public class _1552_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[][] r = new int[n][5];
            for(int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                for(int j = 0; j < 5; j++) {
                    r[i][j] = Integer.parseInt(line.nextToken());
                }
            }
            int best = 0;
            for(int i = 1; i < n; i++) {
                if(beats(i, best, r)) {
                    best = i;
                }
            }
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                if(i != best) {
                    if(!beats(best, i, r)) {
                        possible = false;
                        break;
                    }
                }
            }
            if(possible) {
                out.println(best + 1);
            }else {
                out.println(-1);
            }
        }
        in.close();
        out.close();
    }
    static boolean beats(int a, int b, int[][] r) {
        int count = 0;
        for(int i = 0; i < 5; i++) {
            if(r[a][i] < r[b][i]) {
                count++;
            }
        }
        return count >= 3;
    }
}
