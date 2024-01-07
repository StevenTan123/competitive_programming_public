import java.util.*;
import java.io.*;

public class _1875_C {
    public static void main(String[] args) throws IOException {
        int[] pow2 = new int[31];
        pow2[0] = 1;
        for (int i = 1; i < pow2.length; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            n = n % m;

            int gcd = gcd(n, m);
            int n_red = n / gcd;
            int m_red = m / gcd;

            int exp = -1;
            for (int i = 0; i < pow2.length; i++) {
                if (m_red == pow2[i]) {
                    exp = i;
                    break;
                }
            }

            if (exp != -1 || n_red == 0) {
                ArrayList<Integer> bits = new ArrayList<Integer>();
                int val = n_red;
                int pos = 0;
                while (val > 0) {
                    if (val % 2 == 1) {
                        bits.add(exp - pos);
                    }
                    val /= 2;
                    pos++;
                }
                long res = 0;
                long num = n;
                int prev = 0;
                for (int i = bits.size() - 1; i >= 0; i--) {
                    int cur = bits.get(i) - prev;
                    res += num * (pow2[cur] - 1);
                    num = num * pow2[cur] - m;
                    prev = bits.get(i);
                }
                out.println(res);
            } else {
                out.println(-1);
            }
        }
        
        in.close();
        out.close();
    }
    static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
