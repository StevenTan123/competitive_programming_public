import java.util.*;
import java.io.*;

public class _1519_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int d = Integer.parseInt(line.nextToken());
            int min = Math.min(r, b);
            int max = Math.max(r, b);
            if(d == 0) {
                if(r == b) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }else {
                int ceil = (max - min) / d;
                if((max - min) % d != 0) ceil++;
                if(ceil <= min) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }
        }
        in.close();
        out.close();
    }
}
