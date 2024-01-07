import java.util.*;
import java.io.*;

public class _1591_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            int[] ind = new int[n];
            HashSet<Integer> unique = new HashSet<Integer>();
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken()) - 1;
                ind[a[i]] = i;
                unique.add(a[i]);
            }
            if(unique.size() < n) {
                out.println("YES");
            }else {
                for(int i = n - 1; i >= 2; i--) {
                    int next_ind = ind[i];
                    if(next_ind == i) {
                        continue;
                    }else if(next_ind == i - 1) {
                        cycle(a, ind, i - 1, i, i - 2);
                    }else {
                        cycle(a, ind, next_ind, i, i - 1);
                    }
                }
                if(n == 1 || a[1] > a[0]) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }
        }
        in.close();
        out.close();
    }
    static void cycle(int[] a, int[] ind, int i, int j, int k) {
        int tempi = a[i];
        int tempj = a[j];
        int tempk = a[k];

        a[j] = a[i];
        a[k] = tempj;
        a[i] = tempk;

        ind[tempi] = j;
        ind[tempj] = k;
        ind[tempk] = i;
    }
}
