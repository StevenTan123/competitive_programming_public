import java.util.*;
import java.io.*;

public class _1628_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            boolean[] map = new boolean[n + 5];
            Arrays.fill(map, true);
            int p = 0;
            int[] suf_mex = new int[n];
            for(int i = n - 1; i >= 0; i--) {
                map[a[i]] = false;
                while(map[p] == false) {
                    p++;
                }
                suf_mex[i] = p;
            }
            int goal = 0;
            Arrays.fill(map, true);
            p = 0;
            int count = 0;
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < n; i++) {
                map[a[i]] = false;
                while(map[p] == false) {
                    p++;
                }
                if(p == suf_mex[goal]) {
                    count++;
                    res.append(p);
                    res.append(' ');
                    p = 0;
                    for(int j = goal; j <= i; j++) {
                        map[a[j]] = true;
                    }
                    goal = i + 1;
                }
            }
            out.println(count);
            out.println(res.toString());
        }
        in.close();
        out.close();
    }
}
