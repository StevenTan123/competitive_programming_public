import java.util.*;
import java.io.*;

public class tc9 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            int prev = 0;
            ArrayList<Integer> segs = new ArrayList<Integer>();
            for(int i = 1; i < s.length(); i++) {
                if(s.charAt(i) != s.charAt(prev)) {
                    segs.add(i - prev);
                    prev = i;
                }
            }
            segs.add(s.length() - prev);
            int res = 0;
            int r = 0;
            int c = 0;
            int p = 0;
            for(int i = 0; i < s.length(); i++) {
                if(i > 0 && s.charAt(i) != s.charAt(i - 1)) {
                    int bound;
                    if(s.charAt(i) == 'R') {
                        bound = Math.min(r, n - c - 1);
                    }else {
                        bound = Math.min(c, n - r - 1);
                    }
                    res += Math.min(segs.get(p), bound);
                    p++;
                }
                if(s.charAt(i) == 'R') {
                    c++;
                }else {
                    r++;
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
