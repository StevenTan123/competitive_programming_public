import java.util.*;
import java.io.*;

public class _1530_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int[] b = new int[n];
            int ascore = 0;
            int sumb = 0;
            StringTokenizer line1 = new StringTokenizer(in.readLine());
            StringTokenizer line2 = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line1.nextToken());
                b[i] = Integer.parseInt(line2.nextToken());
                ascore += a[i];
                sumb += b[i];
            }
            Arrays.sort(a);
            Arrays.sort(b);
            int[] preb = new int[n];
            for(int i = 0; i < n; i++) {
                preb[i] = (i > 0 ? preb[i - 1] : 0) + b[i];
            }
            int remove = n / 4;
            int p = remove;
            for(int i = 0; i < remove; i++) {
                ascore -= a[i];
            }
            if(ascore >= sumb - (remove > 0 ? preb[remove - 1] : 0)) {
                out.println(0);
                continue;
            }
            int res = 0;
            for(int i = 1; i <= n; i++) {
                int num = (n + i) / 4;
                int bscore = sumb;
                if(i < num) {
                    bscore -= preb[num - i - 1];
                }
                if((n + i) % 4 == 0) {
                    ascore -= a[p];
                    p++;
                }
                ascore += 100;
                if(ascore >= bscore) {
                    res = i;
                    break;
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
