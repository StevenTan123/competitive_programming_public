import java.util.*;
import java.io.*;

public class _1591_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] x = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(x);
            long dist = 0;
            for(int i = 0; i < x.length; i += k) {
                if(x[i] >= 0) {
                    break;
                }
                dist += -x[i] * 2;
            }
            for(int i = x.length - 1; i >= 0; i -= k) {
                if(x[i] < 0) {
                    break;
                }
                dist += x[i] * 2;
            }
            long sub = Math.max(Math.abs(x[0]), Math.abs(x[n - 1]));
            out.println(dist - sub);
        }
        in.close();
        out.close();
    }
}
