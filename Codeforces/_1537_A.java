import java.util.*;
import java.io.*;

public class _1537_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int sum = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                sum += a[i];
            }
            if(sum == n) {
                out.println(0);
            }else if(sum < n) {
                out.println(1);
            }else {
                out.println(sum - n);
            }
        }
        in.close();
        out.close();
    }
}
