import java.util.*;
import java.io.*;

public class _1626_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line1 = new StringTokenizer(in.readLine());
            StringTokenizer line2 = new StringTokenizer(in.readLine());
            int[] k = new int[n];
            int[] h = new int[n];
            for(int i = 0; i < n; i++) {
                k[i] = Integer.parseInt(line1.nextToken());
                h[i] = Integer.parseInt(line2.nextToken());
            }
            long res = 0;
            int damage = 0;
            int start = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                start = Math.min(start, k[i] - h[i] + 1);
            }
            damage = k[0] - start + 1;
            res += (long)(damage + 1) * damage / 2;
            for(int i = 0; i < n - 1; i++) {
                start = Integer.MAX_VALUE;
                for(int j = i + 1; j < n; j++) {
                    start = Math.min(start, k[j] - h[j] + 1);
                }
                if(start <= k[i]) {
                    int dif = k[i + 1] - k[i];
                    res += ((long)2 * damage + dif + 1) * dif / 2;
                    damage += dif;
                }else {
                    damage = k[i + 1] - start + 1;
                    res += (long)(damage + 1) * damage / 2;
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
