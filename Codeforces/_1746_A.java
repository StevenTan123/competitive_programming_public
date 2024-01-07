import java.util.*;
import java.io.*;

public class _1746_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String a = in.readLine();
            out.println(1 + " " + n);
        }
        in.close();
        out.close();
    }
}
