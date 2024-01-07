import java.util.*;
import java.io.*;

public class reverse {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = Integer.parseInt(line.nextToken());
            int cost = 0;
            for(int i = 0; i < n - 1; i++) {
                int min = i;
                for(int j = i; j < n; j++) {
                    if(a[j] < a[min]) min = j;
                }
                reverse(a, i, min);
                cost += min - i + 1;
            }
            String res = "Case #" + t + ": " + cost;
            out.println(res);
        }
        in.close();
        out.close();
    }
    static void reverse(int[] a, int l, int r) {
        for(int i = l; i <= r; i++) {
            int opp = r - (i - l);
            if(i >= opp) continue;
            int temp = a[i];
            a[i] = a[opp];
            a[opp] = temp;
        }
    }
}