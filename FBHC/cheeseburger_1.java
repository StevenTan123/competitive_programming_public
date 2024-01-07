import java.util.*;
import java.io.*;

public class cheeseburger_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cheeseburger_corollary_1_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int S = Integer.parseInt(line.nextToken());
            int D = Integer.parseInt(line.nextToken());
            int K = Integer.parseInt(line.nextToken());
            long max = Math.min(S * 2 + D * 2 - 1, S + D * 2);
            String res = "Case #" + t + ": ";
            out.println(res + (max >= K ? "YES" : "NO"));
        }
        in.close();
        out.close();
    }
}
