import java.util.*;
import java.io.*;

public class _1770_C {
    static int max = 105;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            long[] a = new long[n];
            HashSet<Long> unique = new HashSet<Long>();
            StringTokenizer line = new StringTokenizer(in.readLine());
            boolean possible = true;
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(line.nextToken());
                if(unique.contains(a[i])) {
                    possible = false;
                }
                unique.add(a[i]);
            }

            if (possible) {
                for (int i = 2; i < max; i++) {
                    int[] residues = new int[i];
                    for (int j = 0; j < n; j++) {
                        residues[(int)(a[j] % i)]++;
                    }
                    boolean above = true;
                    for (int j = 0; j < i; j++) {
                        if (residues[j] < 2) {
                            above = false;
                        }
                    }
                    if (above) {
                        possible = false;
                        break;
                    }
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
