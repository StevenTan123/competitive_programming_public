import java.util.*;
import java.io.*;

public class double_or_one {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            String s = in.readLine();
            StringBuilder ans = new StringBuilder();
            int prev = -1;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(prev == -1) {
                    prev = i;
                }else if(s.charAt(i) != s.charAt(prev)) {
                    char p = s.charAt(prev);
                    if((int)c > (int)p) {
                        for(int j = 0; j < (i - prev) * 2; j++) {
                            ans.append(p);
                        }
                    }else {
                        for(int j = 0; j < i - prev; j++) {
                            ans.append(p);
                        } 
                    }
                    prev = i;
                }
            }
            char p = s.charAt(prev);
            for(int i = 0; i < s.length() - prev; i++) {
                ans.append(p);
            }
            String res = "Case #" + t + ": ";
            out.println(res + ans.toString());
        }
        in.close();
        out.close();
    }
}
