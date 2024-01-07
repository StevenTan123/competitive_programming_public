import java.util.*;
import java.io.*;

public class _1557_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            int[] sorted = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                sorted[i] = a[i];
            }
            Arrays.sort(sorted);
            HashMap<Integer, Integer> higher = new HashMap<Integer, Integer>();
            for(int i = 0; i < n - 1; i++) {
                higher.put(sorted[i], sorted[i + 1]);
            }
            higher.put(sorted[n - 1], Integer.MAX_VALUE);
            int split = 0;
            for(int i = 0; i < n; i++) {
                if(i < n - 1) {
                    if(higher.get(a[i]) != a[i + 1]) {
                        split++;
                    }
                }else {
                    split++;
                }
            }
            out.println(split <= k ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
