import java.util.*;
import java.io.*;

public class _1620_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int w = Integer.parseInt(line.nextToken());
            int h = Integer.parseInt(line.nextToken());
            int max_horz = 0;
            int max_vert = 0;
            for(int i = 0; i < 4; i++) {
                line = new StringTokenizer(in.readLine());
                int k = Integer.parseInt(line.nextToken());
                int range = 0;
                for(int j = 0; j < k; j++) {
                    int cur = Integer.parseInt(line.nextToken());
                    if(j == 0) range -= cur;
                    else if(j == k - 1) range += cur;
                }
                if(i < 2) {
                    max_horz = Math.max(max_horz, range);
                }else {
                    max_vert = Math.max(max_vert, range);
                }
            }
            long max = Math.max((long)max_horz * h, (long)max_vert * w);
            out.println(max);
        }
        in.close();
        out.close();
    }
}
