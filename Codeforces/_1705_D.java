import java.util.*;
import java.io.*;

public class _1705_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int q = Integer.parseInt(in.readLine());
        while(q-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            String t = in.readLine();
            ArrayList<Blob> sblob = new ArrayList<Blob>();
            ArrayList<Blob> tblob = new ArrayList<Blob>();
            int sl = -1;
            int tl = -1;
            for(int i = 0; i < n; i++) {
                if(sl == -1 && s.charAt(i) == '1') {
                    sl = i;
                }
                if(s.charAt(i) == '1' && (i == n - 1 || s.charAt(i + 1) == '0')) {
                    sblob.add(new Blob(sl, i));
                    sl = -1;
                }
                if(tl == -1 && t.charAt(i) == '1') {
                    tl = i;
                }
                if(t.charAt(i) == '1' && (i == n - 1 || t.charAt(i + 1) == '0')) {
                    tblob.add(new Blob(tl, i));
                    tl = -1;
                }
            }
            if(s.charAt(0) == t.charAt(0) && s.charAt(n - 1) == t.charAt(n - 1) && sblob.size() == tblob.size()) {
                long res = 0;
                for(int i = 0; i < sblob.size(); i++) {
                    Blob sb = sblob.get(i);
                    Blob tb = tblob.get(i);
                    res += Math.abs(sb.l - tb.l) + Math.abs(sb.r - tb.r);
                }
                out.println(res);
            }else {
                out.println(-1);
            }
        }
        in.close();
        out.close();
    }
    static class Blob {
        int l, r;
        Blob(int ll, int rr) {
            l = ll;
            r = rr;
        }
    }
}
