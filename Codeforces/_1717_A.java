import java.util.*;
import java.io.*;

public class _1717_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int res = n + (int)(n / 2) * 2 + (int)(n / 3) * 2;
            out.println(res);
        }
        in.close();
        out.close();
    }
}
