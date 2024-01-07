import java.util.*;
import java.io.*;

public class _1542_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int zerocount = 0;
            int onecount = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < 2 * n; i++) {
                int num = Integer.parseInt(line.nextToken());
                if(num % 2 == 0) zerocount++;
                else onecount++;
            }
            if(onecount == zerocount) {
                out.println("Yes");
            }else {
                out.println("No");
            }
        }
        in.close();
        out.close();
    }
}
