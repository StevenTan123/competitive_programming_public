import java.util.*;
import java.io.*;

public class _1530_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int max = 1;
            while(n > 0) {
                max = Math.max(max, n % 10);
                n /= 10;
            }
            out.println(max);
        }
        in.close();
        out.close();
    }
}
