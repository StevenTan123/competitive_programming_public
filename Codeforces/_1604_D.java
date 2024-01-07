import java.util.*;
import java.io.*;

public class _1604_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            if(x < y) {
                int mid = (x + y) / 2;
                out.println(y - mid % x);
            }else if(x == y) {
                out.println(x);
            }else {
                out.println(x + y);
            }
        }
        in.close();
        out.close();
    }
}
