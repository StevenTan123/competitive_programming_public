import java.util.*;
import java.io.*;

public class _1536_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int numpos = 0;
            int numneg = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                int val = Integer.parseInt(line.nextToken());
                if(val > 0) numpos++;
                else if(val < 0) {
                    numneg++;
                }
            }
            if(numneg > 0) {
                out.println("NO");
            }else {
                out.println("YES");
                out.println(101);
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i <= 100; i++) {
                    sb.append(i);
                    sb.append(' ');
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
