import java.util.*;
import java.io.*;

public class subtransmutation {
    static final int MAX = 5000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int[] u = new int[MAX];
            line = new StringTokenizer(in.readLine());
            int last = 0;
            for(int i = 0; i < n; i++) {
                u[i + 1] = Integer.parseInt(line.nextToken());
                if(u[i + 1] > 0) last = i + 1;
            }
            int ans = -1;
            for(int i = 1; i < MAX; i++) {
                if(formable(i, u, a, b, last)) {
                    ans = i;
                    break;
                }
            }
            String res = "Case #" + t + ": ";
            if(ans == -1) {
                out.println(res + "IMPOSSIBLE");
            }else {
                out.println(res + ans);
            }
        }
        in.close();
        out.close();
    }
    static boolean formable(int x, int[] u, int a, int b, int last) {
        if(x < last) {
            return false;
        }
        int[] formed = new int[x + 1];
        formed[x] = 1;
        for(int i = x; i >= 1; i--) {
            if(formed[i] < u[i]) return false;
            int leftover = formed[i] - u[i];
            if(i - a >= 1) {
                formed[i - a] += leftover;
            }
            if(i - b >= 1) {
                formed[i - b] += leftover;
            }
        }
        return true;
    }
}
