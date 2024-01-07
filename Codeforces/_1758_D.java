import java.util.*;
import java.io.*;

public class _1758_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int lower = 3 * n;
            int upper = 5 * n;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n / 2; i++) {
                sb.append(lower + i);
                sb.append(' ');
                sb.append(upper - i);
                sb.append(' ');
            }
            if(n % 2 == 1) {
                sb.append(4 * n);
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
