import java.util.*;
import java.io.*;

public class _1537_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int i = Integer.parseInt(line.nextToken());
            int j = Integer.parseInt(line.nextToken());
            out.println("1 1 " + n + " " + m);
        }
        in.close();
        out.close();
    }
}
