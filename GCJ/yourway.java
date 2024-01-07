import java.util.*;
import java.io.*;

public class yourway {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            String path = in.readLine();
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < n * 2 - 2; i++) {
                res.append(path.charAt(i) == 'E' ? 'S' : 'E');
            }
            String ans = "Case #" + t + ": " + res.toString();
            out.println(ans);
        }
        in.close();
        out.close();
    }
}
