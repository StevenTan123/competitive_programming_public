import java.util.*;
import java.io.*;

public class _1547_D {
    static int[] pow2 = new int[31];
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for(int i = 1; i < 31; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            if(n == 0) {
                out.println(0);
                continue;
            }
            int[] x = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(line.nextToken());
            }
            int[] y = new int[n];
            for(int i = 1; i < n; i++) {
                y[i] = find_xor(x[i - 1], x[i]);
                x[i] ^= y[i];
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(y[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    //finds value x such all 1's in a are in b ^ x 
    static int find_xor(int a, int b) {
        int val = 0;
        int count = 0;
        while(a > 0) {
            if(a % 2 == 1 && b % 2 == 0) {
                val += pow2[count];
            }
            a /= 2;
            b /= 2;
            count++;
        }
        return val;
    }
}
