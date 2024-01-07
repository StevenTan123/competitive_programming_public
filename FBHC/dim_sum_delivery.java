import java.util.*;
import java.io.*;

public class dim_sum_delivery {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dim_sum_delivery_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int R = Integer.parseInt(line.nextToken());
            int C = Integer.parseInt(line.nextToken());
            int A = Integer.parseInt(line.nextToken());
            int B = Integer.parseInt(line.nextToken());
            String res = "Case #" + t + ": ";
            out.println(res + (R > C ? "YES" : "NO"));
        }
        in.close();
        out.close();
    }
}
