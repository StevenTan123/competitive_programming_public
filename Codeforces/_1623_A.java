import java.util.*;
import java.io.*;

public class _1623_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int r1 = Integer.parseInt(line.nextToken());
            int c1 = Integer.parseInt(line.nextToken());
            int r2 = Integer.parseInt(line.nextToken());
            int c2 = Integer.parseInt(line.nextToken());
            int vdist = r2 - r1;
            if(vdist < 0) {
                vdist = n - r1 + n - r2;
            }
            int hdist = c2 - c1;
            if(hdist < 0) {
                hdist = m - c1 + m - c2;
            }
            out.println(Math.min(vdist, hdist));
        }
        in.close();
        out.close();
    }
}
