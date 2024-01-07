import java.util.*;
import java.io.*;

public class _1517_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            long n = Long.parseLong(in.readLine());
            if(n % 2050 != 0) {
                out.println(-1);
                continue;
            }
            long count = 0;
            long val = 2050;
            while(val <= n) {
                val *= 10;
            }
            val /= 10;
            while(n > 0) {
                count += n / val;
                n = n % val;
                val /= 10;
            }
            out.println(count);
        }
        in.close();
        out.close();
    }
}
