import java.util.*;
import java.io.*;

public class weaktyping2 {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("weak_typing_chapter_2_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            String w = in.readLine();
            char prev = ' ';
            int prev_ind = -1;
            long ans = 0;
            for(int i = 0; i < n; i++) {
                char c = w.charAt(i);
                if(c == 'F') {
                    continue;
                }else if(prev != ' ' && c != prev) {
                    long add = (long)(prev_ind + 1) * (n - i) % MOD;
                    ans = (ans + add) % MOD;
                }
                prev = c;
                prev_ind = i;
            }
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
}
