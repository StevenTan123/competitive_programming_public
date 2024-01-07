import java.util.*;
import java.io.*;

public class _1194_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int q = Integer.parseInt(in.readLine());
        while(q-- > 0) {
            String s = in.readLine();
            String t = in.readLine();
            String p = in.readLine();
            int sp = 0;
            int tp = 0;
            HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
            while(tp < t.length()) {
                char tc = t.charAt(tp);
                if(sp >= s.length()) {
                    Integer freq = freqs.get(tc);
                    if(freq == null) freq = 0;
                    freqs.put(tc, freq + 1);
                    tp++;
                    continue;
                }
                char sc = s.charAt(sp);
                if(sc == tc) {
                    sp++;
                    tp++;
                }else {
                    Integer freq = freqs.get(tc);
                    if(freq == null) freq = 0;
                    freqs.put(tc, freq + 1);
                    tp++;
                }
            }
            if(sp < s.length()) {
                out.println("NO");
                continue;
            }
            HashMap<Character, Integer> freqs2 = new HashMap<Character, Integer>();
            for(int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);
                Integer freq = freqs2.get(c);
                if(freq == null) freq = 0;
                freqs2.put(c, freq + 1);
            }
            boolean possible = true;
            for(char c : freqs.keySet()) {
                Integer freq = freqs2.get(c);
                if(freq == null || freqs.get(c) > freq) {
                    possible = false;
                    break;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
