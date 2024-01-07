import java.util.*;
import java.io.*;

public class _1543_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            long sum = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                sum += Integer.parseInt(line.nextToken());
            }
            long left = sum % n;
            long other = n - left;
            out.println(left * other);
        }
        in.close();
        out.close();
    }
}
