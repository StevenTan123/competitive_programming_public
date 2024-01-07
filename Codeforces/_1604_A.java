import java.util.*;
import java.io.*;

public class _1604_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int max = 0;
            for(int i = 1; i <= n; i++) {
                int ai = Integer.parseInt(line.nextToken());
                max = Math.max(max, ai - i);
            }
            out.println(max);
        }
        in.close();
        out.close();
    }
}
