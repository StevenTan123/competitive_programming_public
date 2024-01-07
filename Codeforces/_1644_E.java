import java.util.*;
import java.io.*;

public class _1644_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            int down = n - 1;
            int right = n - 1;
            int change = s.length();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == 'D') {
                    down--;
                }else {
                    right--;
                }
                if(change == s.length() && i > 0 && c != s.charAt(i - 1)) {
                    change = i;
                }
            }
            long res = change + 1;
            if(s.charAt(0) == 'D') {
                res += down;
            }else {
                res += right;
            }
            for(int i = change; i < s.length(); i++) {
                if(i == s.length() - 1) {
                    res += (long)(down + 1) * (right + 1);
                }else {
                    char next = s.charAt(i + 1);
                    if(next == 'D') {
                        res += right + 1;
                    }else {
                        res += down + 1;
                    }
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
