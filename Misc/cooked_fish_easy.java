import java.util.*;
import java.io.*;

public class cooked_fish_easy {
    static long[] sum = new long[100000];
    public static void main(String[] args) throws IOException {
        for(int i = 1; i < 100000; i++) {
            sum[i] = sum[i - 1] + i;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            long a = -1;
            long b = -1;
            for(int i = 2; i < 100000; i++) {
                long RHS = n - sum[i - 1];
                if(RHS % i == 0) {
                    long l = RHS / i;
                    long r = l + i;
                    if(l >= 1 && r <= n) {
                        a = l;
                        b = r;
                        break;
                    }
                }
            }
            if(a == -1) {
                out.println("-1 -1");
            }else {
                out.println(a + " " + b);
            }
        }
        in.close();
        out.close();
    }
}
