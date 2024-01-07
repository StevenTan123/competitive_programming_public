import java.util.*;
import java.io.*;

public class _1537_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] h = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(h);
            int min = Integer.MAX_VALUE;
            int ind = -1;
            for(int i = 1; i < n; i++) {
                int diff = h[i] - h[i - 1];
                if(diff < min) {
                    min = diff;
                    ind = i;
                }
            }
            StringBuilder sb = new StringBuilder();
            if(n == 2) {
                sb.append(h[0]);
                sb.append(' ');
                sb.append(h[1]);
            }else {
                for(int i = ind; i < n; i++) {
                    sb.append(h[i]);
                    sb.append(' ');
                }
                for(int i = 0; i < ind; i++) {
                    sb.append(h[i]);
                    sb.append(' ');
                }
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
