import java.util.*;
import java.io.*;

public class _1562_D1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int q = Integer.parseInt(line.nextToken());
            String s = in.readLine();
            int[] psum = new int[n];
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if(c == '+') {
                    if(i % 2 == 0) {
                        psum[i] = (i > 0 ? psum[i - 1] : 0) + 1; 
                    }else {
                        psum[i] = (i > 0 ? psum[i - 1] : 0) - 1;
                    }
                }else {
                    if(i % 2 == 0) {
                        psum[i] = (i > 0 ? psum[i - 1] : 0) - 1;
                    }else {
                        psum[i] = (i > 0 ? psum[i - 1] : 0) + 1;
                    }
                }
            }
            for(int i = 0; i < q; i++) {
                line = new StringTokenizer(in.readLine());
                int l = Integer.parseInt(line.nextToken()) - 1;
                int r = Integer.parseInt(line.nextToken()) - 1;
                if((r - l + 1) % 2 == 1) {
                    out.println(1);
                }else {
                    if(psum[r] - (l > 0 ? psum[l - 1] : 0) == 0) {
                        out.println(0);
                    }else {
                        out.println(2);
                    }
                }
            }
        }
        in.close();
        out.close();
    }
}
