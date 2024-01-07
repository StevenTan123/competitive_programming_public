import java.util.*;
import java.io.*;

public class _1556_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int c = Integer.parseInt(line.nextToken());
            int d = Integer.parseInt(line.nextToken());
            if(Math.abs(c - d) % 2 == 0) {
                if(c == 0 && d == 0) {
                    out.println(0);
                }else if(c == d) {
                    out.println(1);
                }else {
                    out.println(2);
                }
            }else {
                out.println(-1);
            }
        }
        in.close();
        out.close();
    }
}
