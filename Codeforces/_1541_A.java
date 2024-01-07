import java.util.*;
import java.io.*;

public class _1541_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringBuilder sb = new StringBuilder();
            int cap = n - 2;
            if(n % 2 == 1) cap = n - 3;
            for(int i = 0; i < cap; i += 2) {
                sb.append(i + 2);
                sb.append(' ');
                sb.append(i + 1);
                sb.append(' ');
            }
            if(n % 2 == 0) {
                sb.append(n);
                sb.append(' ');
                sb.append(n - 1);
            }else {
                sb.append(n);
                sb.append(' ');
                sb.append(n - 2);
                sb.append(' ');
                sb.append(n - 1);
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
