import java.util.*;
import java.io.*;

public class _1881_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            String x = in.readLine();
            String s = in.readLine();
            int count = 0;
            for (int i = 0; i < 10; i++) {
                if (n >= m) {
                    if (substring_of(s, x)) {
                        break;
                    }
                }
                if (i > 2 && n / 2 >= m) {
                    break;
                }
                n *= 2;
                x = x + x;
                count++;
            }
            if (substring_of(s, x)) {
                out.println(count);
            } else {
                out.println(-1);
            }
        }
        
        in.close();
        out.close();
    }
    
    static boolean substring_of(String a, String b) {
        for (int i = 0; i < b.length() - a.length() + 1; i++) {
            boolean match = true;
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) != b.charAt(i + j)) {
                    match = false;
                }
            }    
            if (match) {
                return true;
            }
        }
        return false;
    }
}
