import java.util.*;
import java.io.*;

public class _1616_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            char prev = s.charAt(0);
            int ind = n - 1;
            for(int i = 1; i < n; i++) {
                char cur = s.charAt(i);
                if(cur > prev) {
                    ind = i - 1;
                    break;
                }else if(cur == prev) {
                    if(i == 1) {
                        ind = i - 1;
                        break;
                    }
                }
                prev = cur;
            }
            String sub = s.substring(0, ind + 1);
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(0, ind + 1));
            for(int i = sub.length() - 1; i >= 0; i--) {
                sb.append(sub.charAt(i));
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
