import java.util.*;
import java.io.*;

public class cheeseburger_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cheeseburger_corollary_2_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long A = Long.parseLong(line.nextToken());
            long B = Long.parseLong(line.nextToken());
            long C = Long.parseLong(line.nextToken());
            long all_singles = C / A;
            long all_doubles = C / B * 2;
            if (all_doubles > 0) {
                all_doubles--;
            }
            long one_single = 0;
            if (C >= A) {
                one_single = (C - A) / B * 2 + 1;
            }
            long two_singles = 0;
            if (C >= 2 * A) {
                two_singles = (C - 2 * A) / B * 2 + 2;
            }
            long ans = Math.max(Math.max(all_singles, all_doubles), Math.max(one_single, two_singles));
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
}
