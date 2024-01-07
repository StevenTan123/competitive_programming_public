import java.util.*;
import java.io.*;

public class _1644_D {
    static final long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int q = Integer.parseInt(line.nextToken());
            int[][] ops = new int[q][2];
            for(int i = 0; i < q; i++) {
                line = new StringTokenizer(in.readLine());
                ops[i][0] = Integer.parseInt(line.nextToken());
                ops[i][1] = Integer.parseInt(line.nextToken());
            }
            HashSet<Integer> x = new HashSet<Integer>();
            HashSet<Integer> y = new HashSet<Integer>();
            long res = 1;
            for(int i = q - 1; i >= 0; i--) {
                if(x.size() == n || y.size() == m) {
                    break;
                }
                boolean visible = false;
                if(!x.contains(ops[i][0])) {
                    x.add(ops[i][0]);
                    visible = true;
                }  
                if(!y.contains(ops[i][1])) {
                    y.add(ops[i][1]);
                    visible = true;
                }
                if(visible) {
                    res = modmult(res, k);
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
