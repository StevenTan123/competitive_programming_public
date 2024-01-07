import java.util.*;
import java.io.*;

public class _1593_G {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            int n = s.length();
            int[] a = new int[n];
            int[][] psum = new int[2][n];
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int add = 0;
                if(c == '(' || c == ')') {
                    a[i] = 1;
                }else {
                    add = 1;
                }
                psum[i % 2][i] = (i > 0 ? psum[i % 2][i - 1] : 0) + add;
                psum[(i + 1) % 2][i] = i > 0 ? psum[(i + 1) % 2][i - 1] : 0; 
            }
            int q = Integer.parseInt(in.readLine());
            for(int i = 0; i < q; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int l = Integer.parseInt(line.nextToken()) - 1;
                int r = Integer.parseInt(line.nextToken()) - 1;
                int even = psum[0][r] - (l > 0 ? psum[0][l - 1] : 0);
                int odd = psum[1][r] - (l > 0 ? psum[1][l - 1] : 0);
                out.println(Math.abs(even - odd));
            }
        }
        in.close();
        out.close();
    }
}
