import java.util.*;
import java.io.*;

public class _1881_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            int min = Math.min(Math.min(a, b), c);
            long sum = (long) a + b + c;
            boolean possible = false;
            for (int i = 3; i <= 6; i++) {
                long len = sum / i;
                if (sum % i == 0 && len <= min && a % len == 0 && b % len == 0 && c % len == 0) {
                    possible = true;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        
        in.close();
        out.close();
    }
}
