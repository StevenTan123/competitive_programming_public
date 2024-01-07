import java.util.*;
import java.io.*;

public class _1635_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int or_total = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                or_total = or_total | Integer.parseInt(line.nextToken());
            }
            out.println(or_total);
        }
        in.close();
        out.close();
    }
}
