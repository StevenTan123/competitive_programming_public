import java.util.*;
import java.io.*;

public class _1521_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long a = Integer.parseInt(line.nextToken());
            long b = Integer.parseInt(line.nextToken());
            if(b == 1) {
                out.println("NO");
                continue;
            }
            long n1 = a * b;
            if(b == 2) n1 = a * b * 2;
            long n2 = a;
            long n3 = n1 - n2;
            out.println("YES");
            out.println(n2 + " " + n3 + " " + n1); 
        }
        in.close();
        out.close();
    }
}
