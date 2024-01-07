import java.util.*;
import java.io.*;

public class _1620_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            int count = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == 'N') {
                    count++;
                }
            }
            out.println(count == 1 ? "NO" : "YES");
        }
        in.close();
        out.close();
    }
}
