import java.util.*;
import java.io.*;

public class _1551_D1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int cap = n * (m / 2);
            if(k > cap) {
                out.println("NO");
                continue;
            }
            if(n % 2 == 0) {
                if(k % 2 == 0) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }else {
                if(m % 2 == 0) {
                    if(k >= m / 2) {
                        if((k - m / 2) % 2 == 0) {
                            out.println("YES");
                        }else {
                            out.println("NO");
                        }
                    }else {
                        out.println("NO");
                    }
                }else {
                    out.println("NO");
                }
            }
        }
        in.close();
        out.close();
    }
}
