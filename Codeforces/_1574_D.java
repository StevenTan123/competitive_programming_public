import java.util.*;
import java.io.*;

public class _1574_D {
    static final long MOD = 1000000007;
    static final int P = 200009;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] a = new int[n][200005];
        int[] c = new int[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int ci = Integer.parseInt(line.nextToken());
            for(int j = 0; j < ci; j++) {
                a[i][j] = Integer.parseInt(line.nextToken());
            }
            c[i] = ci - 1;
        }
        int m = Integer.parseInt(in.readLine());
        int[][] banned = new int[m][n];
        long[] sum = new long[m];
        HashSet<Long> hashed = new HashSet<Long>();
        for(int i = 0; i < m; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                banned[i][j] = Integer.parseInt(line.nextToken()) - 1;
                sum[i] += a[j][banned[i][j]];
            }
            hashed.add(hash(banned, i));
        }
        long max = 0;
        int[] build = new int[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(banned[i][j] == 0) {
                    continue;
                }
                banned[i][j] -= 1;
                long hash = hash(banned, i);
                if(!hashed.contains(hash)) {
                    long val = sum[i] - a[j][banned[i][j] + 1] + a[j][banned[i][j]];
                    if(val > max) {
                        max = val;
                        for(int k = 0; k < n; k++) {
                            build[k] = banned[i][k];
                        }
                    }
                }
                banned[i][j] += 1;
            }
        }
        long hash = 0;
        long s = 0;
        for(int i = 0; i < n; i++) {
            hash = modadd(modmult(hash, P), c[i]);
            s += a[i][c[i]];
        }
        if(!hashed.contains(hash)) {
            max = s;
            for(int i = 0; i < n; i++) {
                build[i] = c[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(build[i] + 1);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static long hash(int[][] banned, int i) {
        long res = 0;
        for(int j = 0; j < banned[i].length; j++) {
            res = modadd(modmult(res, P), banned[i][j]);
        }
        return res;
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
