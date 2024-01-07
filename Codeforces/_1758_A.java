import java.util.*;
import java.io.*;

public class _1758_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            String s = in.readLine();
            StringBuilder sb = new StringBuilder(s);
            StringBuilder sb2 = new StringBuilder(s);
            sb2.reverse();
            sb.append(sb2);
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
