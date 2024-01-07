import java.util.*;
import java.io.*;

public class _1514_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        ArrayList<Integer> res = new ArrayList<Integer>();
        long mult = 1;
        for(int i = 1; i < n; i++) {
            if(gcd(i, n) == 1) {
                res.add(i);
                mult *= i;
                mult %= n;
            }
        }
        if(mult > 1) {
            for(int i = 0; i < res.size(); i++) {
                if(res.get(i) == mult) {
                    res.remove(i);
                    break;
                }
            }
        }
        out.println(res.size());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.size(); i++) {
            sb.append(res.get(i));
            if(i < res.size() - 1) sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
