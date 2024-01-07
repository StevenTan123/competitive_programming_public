import java.util.*;
import java.io.*;

public class _1260_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int max = Math.max(r, b);
            int min = Math.min(r, b);
            int gcd = gcd(max, min);
            max /= gcd;
            min /= gcd;
            int fit = (max - 1) / min;
            fit++;
            if((max - 1) % min == 0) fit--;
            out.println((fit < k) ? "OBEY" : "REBEL");
        }
        in.close();
        out.close();
    }
    static int gcd(int a, int b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }
}
