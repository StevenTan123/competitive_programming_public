import java.util.*;
import java.io.*;

public class _1442_B {
    static final long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            int[] eind = new int[n + 1];
            line = new StringTokenizer(in.readLine());
            Arrays.fill(eind, -1);
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                eind[a[i]] = i;
            }
            int[] b = new int[k];
            HashSet<Integer> left = new HashSet<Integer>();
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < k; i++) {
                b[i] = Integer.parseInt(line.nextToken());
                left.add(b[i]);
            }
            long res = 1;
            for(int i = 0; i < k; i++) {
                int ind = eind[b[i]];
                int count = 0;
                if(ind > 0) {
                    if(!left.contains(a[ind - 1])) count++;
                }
                if(ind < n - 1) {
                    if(!left.contains(a[ind + 1])) count++;
                }
                res *= count;
                res %= MOD;
                left.remove(b[i]);
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
