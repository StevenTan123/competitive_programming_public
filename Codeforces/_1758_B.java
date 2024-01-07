import java.util.*;
import java.io.*;

public class _1758_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringBuilder sb = new StringBuilder();
            if(n % 2 == 0) {
                sb.append(1);
                sb.append(' ');
                sb.append(3);
                sb.append(' ');
                for(int i = 0; i < n - 2; i++) {
                    sb.append(2);
                    sb.append(' ');
                }
            }else {
                for(int i = 0; i < n; i++) {
                    sb.append(1);
                    sb.append(' ');
                }
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
