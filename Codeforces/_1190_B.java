import java.util.*;
import java.io.*;

public class _1190_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(a);
        int dupecount = 0;
        boolean escape = false;
        for(int i = 1; i < n; i++) {
            if(a[i] == a[i - 1]) {
                dupecount++;
                if(a[i] > 0 && (i < 2 || a[i - 2] + 1 < a[i])) {
                    escape = true;
                }
            }
        }
        if(dupecount > 1 || (dupecount == 1 && !escape)) {
            out.println("cslnb");
        }else {
            long dif = 0;
            for(int i = 0; i < n; i++) {
                dif += a[i] - i;
            }
            if(dif % 2 == 0) {
                out.println("cslnb");
            }else {
                out.println("sjfnb");
            }
        }
        in.close();
        out.close();
    }
}
