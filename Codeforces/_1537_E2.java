import java.util.*;
import java.io.*;

public class _1537_E2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        String s = in.readLine();
        int best = 0;
        for(int i = 1; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i % (best + 1));
            if(c1 < c2) {
                best = i;
            }else if(c1 > c2) {
                break;
            }
        }
        StringBuilder res = new StringBuilder();
        String sub = s.substring(0, best + 1);
        while(res.length() < k) {
            res.append(sub);
        }
        out.println(res.toString().substring(0, k));
        in.close();
        out.close();
    }
}
