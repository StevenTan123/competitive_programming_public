import java.util.*;
import java.io.*;

public class _1536_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            String res = null;
            for(int i = 1; i <= 3; i++) {
                res = MEX(s, n, i);
                if(res != null) break;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static String MEX(String s, int n, int len) {
        HashSet<String> subs = new HashSet<String>();
        for(int i = 0; i < n - len + 1; i++) {
            subs.add(s.substring(i, i + len));
        }
        TreeSet<String> strs = new TreeSet<String>();
        genStrings(len, 0, "", strs);
        for(String cur : strs) {
            if(!subs.contains(cur)) {
                return cur;
            }
        }
        return null;
    }
    static void genStrings(int len, int ind, String curstr, TreeSet<String> strs) {
        if(ind >= len) {
            strs.add(curstr);
            return;
        }
        for(int i = 0; i < 26; i++) {
            char c = (char)(i + 97);
            genStrings(len, ind + 1, curstr + c, strs);
        }
    }
}
