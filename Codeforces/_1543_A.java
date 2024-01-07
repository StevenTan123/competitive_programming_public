import java.util.*;
import java.io.*;

public class _1543_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long a = Long.parseLong(line.nextToken());
            long b = Long.parseLong(line.nextToken());
            long gcd = Math.max(a, b) - Math.min(a, b);
            if(gcd == 0) {
                out.println("0 0");
            }else {
                long dist = a % gcd;
                dist = Math.min(dist, gcd - dist);
                out.println(gcd + " " + dist);
            }
        }
        in.close();
        out.close();
    }
}
