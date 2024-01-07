import java.util.*;
import java.io.*;

public class d1000000 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[] s = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(s);
            int prev = 0;
            for(int i = 0; i < n; i++) {
                if(prev + 1 <= s[i]) {
                    prev++;
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + prev);
        }
        in.close();
        out.close();
    }
}
