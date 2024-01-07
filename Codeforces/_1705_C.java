import java.util.*;
import java.io.*;

public class _1705_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            int q = Integer.parseInt(line.nextToken());
            String s = in.readLine();
            long[][] cs = new long[c][2];
            long[] lens = new long[c + 1];
            lens[0] = n;
            for(int i = 0; i < c; i++) {
                line = new StringTokenizer(in.readLine());
                cs[i][0] = Long.parseLong(line.nextToken());
                cs[i][1] = Long.parseLong(line.nextToken());
                lens[i + 1] = lens[i] + cs[i][1] - cs[i][0] + 1;
            }
            for(int i = 0; i < q; i++) {
                out.println(findK(s, Long.parseLong(in.readLine()), cs, lens));
            }
        }
        in.close();
        out.close();
    }
    static char findK(String s, long k, long[][] cs, long[] lens) {
        if(k <= s.length()) {
            return s.charAt((int)k - 1);
        }
        for(int i = 0; i < lens.length - 1; i++) {
            if(k > lens[i] && k <= lens[i + 1]) {
                long prev = k - lens[i] - 1 + cs[i][0];
                return findK(s, prev, cs, lens);
            }
        }
        return ' ';
    }
}
