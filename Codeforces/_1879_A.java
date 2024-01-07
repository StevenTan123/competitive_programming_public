import java.util.*;
import java.io.*;

public class _1879_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] s = new int[n];
            int[] e = new int[n];
            boolean possible = true;
            for (int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                s[i] = Integer.parseInt(line.nextToken());
                e[i] = Integer.parseInt(line.nextToken());
                if (i > 0 && s[i] >= s[0] && e[i] >= e[0]) {
                    possible = false;
                }
            }
            out.println(possible ? s[0] : -1);
        }
        in.close();
        out.close();
    }
}
