import java.util.*;
import java.io.*;

public class _1628_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String[] s = new String[n];
            for(int i = 0; i < n; i++) {
                s[i] = in.readLine();
            }
            boolean possible = false;
            HashSet<String> seen = new HashSet<String>();
            for(int i = n - 1; i >= 0; i--) {
                String cur = s[i];
                boolean pal = true;
                for(int j = 0; j < cur.length() - 1; j++) {
                    if(cur.charAt(j) != cur.charAt(cur.length() - j - 1)) {
                        pal = false;
                    }
                }
                if(pal) {
                    possible = true;
                    break;
                }
                String reverse = "";
                for(int j = cur.length() - 1; j >= 0; j--) {
                    reverse += cur.substring(j, j + 1);
                }
                String shorter = reverse.substring(1);
                if(seen.contains(reverse) || seen.contains(shorter)) {
                    possible = true;
                    break;
                }
                for(int j = 0; j < 26; j++) {
                    char letter = (char)(j + 97);
                    String longer = letter + reverse;
                    if(seen.contains(longer)) {
                        possible = true;
                        break;
                    }
                }
                seen.add(cur);
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
