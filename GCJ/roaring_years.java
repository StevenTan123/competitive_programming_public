import java.util.*;
import java.io.*;

public class roaring_years {
    static final int MAXY = 2000000;
    static long prev = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        long[] pows = new long[19];
        pows[0] = 1;
        for(int i = 1; i < 19; i++) {
            pows[i] = pows[i - 1] * 10;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            long y = Long.parseLong(in.readLine()) + 1;
            long min = Long.MAX_VALUE;
            for(int i = 2; i <= 18; i++) {
                long l = 1;
                long r = pows[10] - 1;
                long res = Long.MAX_VALUE;
                while(l <= r) {
                    long avg = (l + r) / 2;
                    if(over(avg, i, y)) {
                        res = prev;
                        r = avg - 1;
                    }else {
                        l = avg + 1;
                    }
                }
                min = Math.min(min, res);
            }
            String res = "Case #" + t + ": ";
            out.println(res + min);
        }
        in.close();
        out.close();
    }
    //Returns if roaring number concatenated starting from x with n concatenations is greater than or equal to y
    static boolean over(long x, int n, long y) {
        prev = Long.MAX_VALUE;
        String concat = "";
        for(int i = 0; i < n; i++) {
            concat += x;
            x++;
        }
        try {
            long lconcat = Long.parseLong(concat);
            prev = lconcat;
            return lconcat >= y;
        }catch(Exception e) {
            return true;
        }
    }
}
