import java.util.*;
import java.io.*;

public class _1848_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());

            long max_disc = 0;
            for (int i = 0; i < 10; i++) {
                if (k < 0) {
                    break;
                }
                if (s % 10 != 0 && s % 2 == 0) {
                    long x = Math.max((5 * (long) k - s) / 40, 0);
                    max_disc = Math.max(max_disc, (s + 20 * x) * (k - 4 * x));
                    max_disc = Math.max(max_disc, (s + 20 * (x + 1)) * (k - 4 * (x + 1)));
                } else {
                    max_disc = Math.max(max_disc, (long) s * k);
                }
                s += s % 10;
                k--;
            }
            out.println(max_disc);
        }

        in.close();
        out.close();
    }
}
