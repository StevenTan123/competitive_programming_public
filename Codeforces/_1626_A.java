import java.util.*;
import java.io.*;

public class _1626_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                Integer count = freqs.get(c);
                if(count == null) {
                    count = 0;
                }
                freqs.put(c, count + 1);
            }
            StringBuilder two = new StringBuilder();
            StringBuilder one = new StringBuilder();
            for(char c : freqs.keySet()) {
                if(freqs.get(c) == 1) {
                    one.append(c);
                }else {
                    two.append(c);
                }
            }
            two.append(two);
            two.append(one);
            out.println(two.toString());
        }
        in.close();
        out.close();
    }
}
