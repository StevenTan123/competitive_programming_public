import java.util.*;
import java.io.*;

public class _1622_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] l = new int[3];
            for(int i = 0; i < 3; i++) {
                l[i] = Integer.parseInt(line.nextToken());
            }
            boolean possible = false;
            for(int i = 0; i < 3; i++) {
                int cur = l[i];
                int o1 = l[(i + 1) % 3];
                int o2 = l[(i + 2) % 3];
                if(cur == o1 + o2) {
                    possible = true;
                }
                if(o1 == o2 && cur % 2 == 0) {
                    possible = true;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
