import java.util.*;
import java.io.*;

public class pichuFlute {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int minY = Integer.MAX_VALUE;
        int maxN = 0;
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int val = Integer.parseInt(line.nextToken());
            if(line.nextToken().equals("Y")) {
                minY = Math.min(minY, val);
            }else {
                maxN = Math.max(maxN, val);
            }
        }
        out.println((maxN + 1) + " " + minY);
        in.close();
        out.close();
    }
}
