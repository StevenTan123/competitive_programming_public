import java.util.*;
import java.io.*;

public class _1831_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(n + 1 - Integer.parseInt(line.nextToken()));
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
