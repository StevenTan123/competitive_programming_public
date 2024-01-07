import java.util.*;
import java.io.*;

public class _1615_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int sum = 0;
            for(int i = 0; i < n; i++) {
                sum += Integer.parseInt(line.nextToken());
            }
            if(sum % n == 0) {
                out.println(0);
            }else {
                out.println(1);
            }
        }
        in.close();
        out.close();
    }
}
