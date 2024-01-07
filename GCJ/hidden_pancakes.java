import java.util.*;
import java.io.*;

public class hidden_pancakes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[] v = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                v[i] = Integer.parseInt(line.nextToken());
            }
            
            String res = "Case #" + t + ": ";
        }
        in.close();
        out.close();
    }
}
