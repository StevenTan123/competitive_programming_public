import java.util.*;
import java.io.*;

public class _1542_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            if(a == 1) {
                if(n % b == 1 || b == 1) {
                    out.println("Yes");
                }else {
                    out.println("No");
                }
                continue;
            }
            boolean possible = false;
            long pow = 1;
            while(pow <= n) {
                if((n - pow) % b == 0) {
                    possible = true;
                    break;
                }
                pow *= a;
            }
            out.println(possible ? "Yes" : "No");
        }
        in.close();
        out.close();
    }
}
