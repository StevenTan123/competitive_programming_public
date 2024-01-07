import java.util.*;
import java.io.*;

public class _1516_D {
    static final int MAXN = 100005;
    public static void main(String[] args) throws IOException {
        int[] spf = new int[MAXN];
        for(int i = 2; i < MAXN; i++) {
            if(spf[i] == 0) {
                for(int j = i; j < MAXN; j += i) {
                    if(spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }
        int[] pow = new int[18];
        pow[0] = 1;
        for(int i = 1; i < 18; i++) {
            pow[i] = pow[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        int[] next = new int[n];
        int[] seen = new int[MAXN];
        Arrays.fill(seen, -1);
        for(int i = n - 1; i >= 0; i--) {
            int min = n;
            int val = a[i];
            while(val > 1) {
                if(seen[spf[val]] != -1 && seen[spf[val]] != i) {
                    min = Math.min(min, seen[spf[val]]);
                }
                seen[spf[val]] = i;
                val /= spf[val];
            }
            next[i] = Math.min(min, i < n - 1 ? next[i + 1] : n);
        }
        int[][] par = new int[n][18];
        for(int i = n - 1; i >= 0; i--) {
            Arrays.fill(par[i], -1);
            for(int j = 0; j < 18; j++) {
                if(j == 0) {
                    par[i][j] = next[i];
                }else if(par[i][j - 1] != -1 && par[i][j - 1] < n) {
                    par[i][j] = par[par[i][j - 1]][j - 1];
                }
            }
        }
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(line.nextToken()) - 1;
            int r = Integer.parseInt(line.nextToken());
            int cur = l;
            int count = 0;
            while(true) {
                int jump = -1;
                int val = -1;
                for(int j = 17; j >= 0; j--) {
                    if(par[cur][j] != -1 && par[cur][j] < r) {
                        jump = par[cur][j];
                        val = j;
                        break;
                    }
                }
                if(jump == -1) {
                    out.println(count + 1);
                    break;
                }
                cur = jump;
                count += pow[val];
            }
        }
        in.close();
        out.close();
    }
}
